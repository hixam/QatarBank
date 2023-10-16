package com.example.dataquery.mongo;

import com.example.dataquery.domain.Seller;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SellerRepository extends MongoRepository <Seller, String > {
    List<Seller> findByFirstName(String name);
}
