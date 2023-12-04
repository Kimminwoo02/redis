package com.example.redis.map.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreResponseDTO {
    private Long id;
    private String storeName;
    private String storeAddress;
    private double latitude;//위도
    private double longitude;//경도
    private double distance;//기준위치와 거리계산
}
