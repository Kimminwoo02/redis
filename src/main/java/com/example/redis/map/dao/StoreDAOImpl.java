package com.example.redis.map.dao;

import com.example.redis.map.entity.StoreEntity;
import com.example.redis.map.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StoreDAOImpl implements StoreDAO{
    private final StoreRepository repository;
    @Override
    public List<StoreEntity> getStoreList() {
        return repository.findAll();
    }
}
