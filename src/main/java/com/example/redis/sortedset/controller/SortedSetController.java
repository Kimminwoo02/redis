package com.example.redis.sortedset.controller;

import com.example.redis.sortedset.dto.BasicDto;
import com.example.redis.sortedset.dto.Product;
import com.example.redis.sortedset.service.SortedSetServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @PostMapping("/sortedset")
    public String createSortedSetValue(@RequestBody Product newProduct) {

        log.info(newProduct.getProductId());
        log.info(String.valueOf(newProduct.getPrice()));
        log.info(newProduct.getCategoryId());
        service.createMember(newProduct);
        return "ok";

    }

    @GetMapping("/sortedset1")
    public String getSortedSetValue(Product newProduct) {
        service.createMember(newProduct);
        return "ok";

    }



}
