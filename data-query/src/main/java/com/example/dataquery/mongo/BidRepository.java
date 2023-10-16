package com.example.dataquery.mongo;

import com.example.dataquery.domain.Bid;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BidRepository extends MongoRepository <Bid, String> {

    Bid findByProductIdAndEmail(String productId, String email);
}
