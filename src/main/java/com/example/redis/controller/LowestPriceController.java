package com.example.redis.controller;

import com.example.redis.service.LowestPriceService;
import com.example.redis.vo.Keyword;
import com.example.redis.vo.Product;
import com.example.redis.vo.ProductGrp;
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

    @PutMapping("/productGroup")
    public int SetNewProductGroup(@RequestBody ProductGrp newProductGrp){
        return lowestPriceService.setNewProductGrp(newProductGrp);
    }

    @PutMapping("/productGroupToKeyword")
    public int setNewProductGrpToKeyword(String keyword, String proGrpId, double score){
        return lowestPriceService.setNewProductGrpToKeyword(keyword,proGrpId,score);
    }

    @GetMapping("/productPrice/lowest")
    public Keyword getLowestPriceProductByKeyword (String keyword){
        
    }

}
