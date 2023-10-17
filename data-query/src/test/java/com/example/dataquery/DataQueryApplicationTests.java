package com.example.dataquery;

import com.example.dataquery.domain.PdfDocument;
import com.example.dataquery.mongo.PdfDocumentRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class DataQueryApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private PdfDocumentRepository pdfDocumentRepository;


//	@Test
//	void createPdfDocumentTest() throws Exception {
//		String uuid = UUID.randomUUID().toString();
//		String title = "test title";
//
//		MockMultipartFile file
//				= new MockMultipartFile(
//				title,
//				"hello.pdf",
//				MediaType.APPLICATION_PDF_VALUE,
//				"Hello, World!".getBytes()
//		);
//
//		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//		mockMvc.perform(MockMvcRequestBuilders.multipart("/pdf/add")
//				.file(file)
//				.param("some-random", "4"))
//				.andExpect(status().is(200));
//
//		Optional<PdfDocument> pdfEntity = pdfDocumentRepository.findById(uuid);
//		assertThat(pdfEntity.isPresent());
//	}

	@Test
	void updatePdfDocumentTest() throws Exception {
		String newTitle = "new title" + Math.random();
		List<PdfDocument> list = pdfDocumentRepository.findAll();
		String id = list.get(0).getId();

		mockMvc.perform(put("/pdf/edit/{id}", id)
				.contentType("application/json")
				.param("title", newTitle)
				)
				.andExpect(status().isOk());

		Optional<PdfDocument> pdfEntity = pdfDocumentRepository.findById(id);
		assertTrue(pdfEntity.isPresent());
		assertEquals(pdfEntity.get().getTitle(), newTitle);
		assertEquals(pdfEntity.get().getId(), id);
	}

	@Test
	void getPdfTest() throws Exception {
		List<PdfDocument> list = pdfDocumentRepository.findAll();
		String id = list.get(0).getId();

		mockMvc.perform(get("/pdf/{id}", id)
				.contentType("application/json")
				)
				.andExpect(status().isOk());

		Optional<PdfDocument> pdfEntity = pdfDocumentRepository.findById(id);
		assertEquals(pdfEntity.get().getId(), id);
	}

	@Test
	void getAllPdfIdsTest() throws Exception {
		List<PdfDocument> list = pdfDocumentRepository.findAll();

		MvcResult result = mockMvc.perform(get("/pdf/info/id")
				.contentType("application/json")
				)
				.andExpect(status().isOk())
				.andReturn();
		ObjectMapper mapper = new ObjectMapper();

		List<String> actual = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<String>>() {});

		assertEquals(actual.size(), list.size());
	}



	@Test
	void getAllPdfsInfoTest() throws Exception {
		List<PdfDocument> list = pdfDocumentRepository.findAll();
		String id = list.get(0).getId();

		MvcResult result = mockMvc.perform(get("/pdf/info/{id}", id)
				.contentType("application/json")
				)
				.andExpect(status().isOk())
				.andReturn();
		ObjectMapper mapper = new ObjectMapper();

//		Optional<PdfDocument> actual = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<Optional<PdfDocument>>() {});
//
//		assertEquals(actual.get().getId(), id);
	}

	@Test
	void InsertAndDeletePdfDocumentTest() throws Exception {

		String id = UUID.randomUUID().toString();
		String title = "test title";
		MockMultipartFile file
				= new MockMultipartFile(
				title,
				"hello.pdf",
				MediaType.APPLICATION_PDF_VALUE,
				"Hello, World!".getBytes()
		);
		PdfDocument pdf =PdfDocument.builder()
				.id(id)
				.data(file.getBytes())
				.title(title)
				.contentType(file.getContentType())
				.build();

		PdfDocument pdfEntity = pdfDocumentRepository.save(pdf);


		mockMvc.perform(delete("/pdf/delete/{id}", pdfEntity.getId())
				.contentType("application/json")
				)
				.andExpect(status().isOk());

		Optional<PdfDocument> pdfEntity2Remove = pdfDocumentRepository.findById(pdfEntity.getId());
		assertFalse(pdfEntity2Remove.isPresent());
	}

}
