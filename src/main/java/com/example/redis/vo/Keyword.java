package com.example.redis.vo;

import lombok.Data;

import java.util.List;

@Data
public class Keyword {

    private String keyword; // 유아용품 - 기저귀, 딸랑이

    private List<ProductGrp> productGrpList; // {"FPG0001", "FPG0002"}


}
