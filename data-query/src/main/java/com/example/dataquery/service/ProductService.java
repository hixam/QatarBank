package com.example.dataquery.service;

import com.example.dataquery.domain.Bid;
import com.example.dataquery.domain.Product;
import com.example.dataquery.domain.ProductBidList;
import com.example.dataquery.mongo.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Comparator;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Transactional
    public Product addNewProduct(Object event) throws ParseException {
        Product saved = saveProduct(event);
        log.info("Product with id {} and name {} saved correctly", saved.getId(), saved.getProductName());
        return saved;
    }

    @Transactional
    public boolean removeProduct(String productId) {
        productRepository.deleteById(productId);
        log.info("Product with id {} deleted correctly",productId);
        return true;
    }

    @Transactional
    private Product saveProduct(Object event) throws ParseException {
        return null;// productRepository.save(DtoTransformer.productToDto(event));
    }

    @Autowired
    MongoTemplate mongoTemplate;

    public ProductBidList getProductBids(String id){
        ProductBidList res = mongoTemplate.findById(id, ProductBidList.class, "ProductBidList");
        assert res != null;
        res.setBidsList(
                res.getBidsList().stream().sorted(Comparator.comparingLong(Bid::getBidAmount).reversed())
                        .collect(Collectors.toList())
        );

        return res;
    }

}
