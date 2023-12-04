package com.example.redis.map.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="store")
public class StoreEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String storeName;
    private String storeAddress;
    private double latitude;//위도
    private double longitude;//경도
    private double distance;//거리
}
