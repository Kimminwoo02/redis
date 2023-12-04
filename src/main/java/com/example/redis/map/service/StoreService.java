package com.example.redis.map.service;

import com.example.redis.map.dto.StoreResponseDTO;


import java.util.List;

public interface StoreService {
	// 디비에서 데이터 조회
    List<StoreResponseDTO> getStoreList();
	// 반경 2km안의 데이터 조회
    List<StoreResponseDTO> getStoreFilter(double latitude, double longitude) ;
	// Redis에서 조회하기
    List<StoreResponseDTO> getRedisStoreFilter();
}
