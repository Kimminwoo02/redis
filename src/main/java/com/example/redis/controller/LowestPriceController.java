package com.example.redis.controller;

import com.example.redis.service.LowestPriceService;
import com.example.redis.vo.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class LowestPriceController {
    private final LowestPriceService lowestPriceService;

    @GetMapping("/getZSETValue")
    public Set getZsetValue(String key){
        return lowestPriceService.getZsetValue(key);

    }

    @PutMapping("/product")
    public int SetNewProduct(@RequestBody Product newProduct){
        return lowestPriceService.setNewProduct(newProduct);
    }

}
