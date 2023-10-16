

package com.example.dataquery.mongo;

import com.example.dataquery.domain.Bid;
import com.example.dataquery.domain.ProductBidList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomAggregationOperation {

    @Autowired
    MongoTemplate mongoTemplate;

    public List<ProductBidList> getJoinedProductBids(String sellerId) {

        LookupOperation lookupOperation = LookupOperation.newLookup()
                .from("bid")
                .localField("_id")
                .foreignField("productId._id")
                .as("bidsList");

        Aggregation aggregation = Aggregation.newAggregation(Aggregation.match(Criteria.where("sellerId").is(sellerId)), lookupOperation);

        List<ProductBidList> results = mongoTemplate.aggregate(aggregation, "product", ProductBidList.class).getMappedResults();

        results.parallelStream().forEach(productBidList ->
                        productBidList.setBidsList(productBidList.getBidsList().stream().sorted(Comparator.comparingLong(Bid::getBidAmount).reversed())
                                .collect(Collectors.toList()))
                );

        return results;
    }

}