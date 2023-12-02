package com.example.redis.sortedset.controller;

import com.example.redis.sortedset.dto.BasicDto;
import com.example.redis.sortedset.dto.Product;
import com.example.redis.sortedset.service.SortedSetServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SortedSetController {
    private final SortedSetServiceImpl service;



    @GetMapping("/sortedset")
    public Set CreateSortedSetValue(@RequestParam(name = "key") String key) {
        return service.getZSetValues(key);

    }

    @GetMapping("/product")
    public Set<ZSetOperations.TypedTuple<String>> createSortedSetValue(String key) {
        return service.getZSetValues(key);

    }

    @GetMapping("/sortedset1")
    public String getSortedSetValue(Product newProduct) {
        service.createMember(newProduct);
        return "ok";

    }



}
