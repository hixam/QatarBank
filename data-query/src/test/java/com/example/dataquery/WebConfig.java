//package com.example.dataquery;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.web.multipart.MultipartResolver;
//import org.springframework.web.multipart.commons.CommonsMultipartResolver;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//
//import static org.springframework.test.web.servlet.htmlunit.webdriver.MockMvcHtmlUnitDriverBuilder.webAppContextSetup;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//public class WebConfig extends AbstractContextControllerTests {
//
//    @Test
//    public void readString() throws Exception {
//
//        MockMultipartFile file = new MockMultipartFile("file", "orig", null, "bar".getBytes());
//
//        webAppContextSetup(this.wac).build()
//                .perform(fileUpload("/fileupload").file(file))
//                .andExpect(model().attribute("message", "File 'orig' uploaded successfully"));
//    }
//
//}
