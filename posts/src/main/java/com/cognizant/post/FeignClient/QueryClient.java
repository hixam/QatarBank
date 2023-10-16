package com.cognizant.post.FeignClient;

import com.cognizant.post.domain.PdfDocument;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "query")
public interface QueryClient {

    @RequestMapping(method = RequestMethod.GET, value = "/pdf/info")
    List<PdfDocument> getAllPdfsInfo();

    @RequestMapping(method = RequestMethod.GET, value = "/pdf/info/id")
    List<PdfDocument> getAllPdfsIds();

    @RequestMapping(method = RequestMethod.GET, value = "/pdf/info/{id}")
    Optional<PdfDocument> getPdfInfo(@PathVariable String id);


}

