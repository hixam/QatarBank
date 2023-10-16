package com.example.dataquery.mongo;

import com.example.dataquery.domain.ProductBidList;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductBidListRepository extends MongoRepository<ProductBidList, String> {
}
