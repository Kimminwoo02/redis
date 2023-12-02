package com.example.redis.sortedset.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private String categoryId; // key
    private String productId; // member(구분키)
    private int  price;// score


}
