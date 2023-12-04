package com.example.redis.map.repository;

import com.example.redis.map.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<StoreEntity,Long> {
}
