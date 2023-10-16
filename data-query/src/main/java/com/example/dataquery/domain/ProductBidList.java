package com.example.dataquery.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@Document
public class ProductBidList {

    @Id
    private String id;
    private List<Bid> bidsList;
    private String shortDescription;
    private String detailedDescription;
    private CategoryEnum category;
    private Long startingPrice;
    private LocalDate bidEndDate;

}
