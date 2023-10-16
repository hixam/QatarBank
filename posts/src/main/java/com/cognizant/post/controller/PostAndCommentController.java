package com.cognizant.post.controller;

import com.cognizant.post.FeignClient.QueryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/seller")
public class PostAndCommentController {


    @Autowired
    QueryClient queryClient;
//
//    @PostMapping(path = "/add-product",consumes = {MediaType.APPLICATION_JSON_VALUE})
//    public void addNewProduct(@RequestBody Product product) throws ParseException {
//        producerService.sendEvent(DtoTransformer.productToAvro(product));
//    }
//
//    @GetMapping(path = "/show-bids/{sellerId}")
//    public List<ProductBidList> getBidsOnProduct(@PathVariable String sellerId) {
//        return queryClient.getBidsList(sellerId);
//    }
//
//    @CrossOrigin
//    @GetMapping(path = "/test")
//    public int test() {
//        return 21;
//    }
//
//    @DeleteMapping(path = "/delete/{productId}")
//    public boolean deleteProduct(@PathVariable String productId) throws ProductException {
//        if(commandClient.existBidsOnProduct(productId)){
//            throw new ProductException("Product has existing bids, cannot be deleted !");
//        }
//        return commandClient.deleteProduct(productId);
//    }
//
//    @GetMapping(path = "/all")
//    public List<Product> getAllProducts(){
//        return commandClient.getAllProducts();
//    }
//
//    @GetMapping(path = "/bids/{productId}")
//    public ProductBidList getProductBids(@PathVariable String productId){
//        return queryClient.getProductBids(productId);
//    }

}
