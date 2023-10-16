package com.example.dataquery.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document
public class Product {
    @Id
    private String id;
    private String productName;
    private String shortDescription;
    private String detailedDescription;
    private CategoryEnum category;
    private Long startingPrice;
    private LocalDate bidEndDate;
    private String sellerId;
}
