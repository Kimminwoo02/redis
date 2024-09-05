package com.example.redis.controller;

import com.example.redis.service.LowestPriceService;
import com.example.redis.vo.Keyword;
import com.example.redis.vo.NotFoundException;
import com.example.redis.vo.Product;
import com.example.redis.vo.ProductGrp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpHeaders;
import java.util.HashSet;
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

    @PutMapping("/product2")
    public int GetZsetValueUsingExController(String key) throws Exception{
        try{
            return lowestPriceService.getZsetValueWithStatus(key);
        }catch (Exception ex){
            throw new Exception(ex);
        }

    }
    @PutMapping("/product3")
    public int GetZSetValueUsingExControllerWithSpecificException(String key) throws Exception{
        Set<String> mySet = new HashSet<>();
        try{
            mySet = lowestPriceService.getZsetValueWithSpecificException(key);
        }catch (Exception ex){
            throw new NotFoundException(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
        HttpHeaders responseHeaders = new HttpHeaders();

        return new ResponseEntity<Set>(mySet, responseHeaders, HttpStatus.OK);


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
        return lowestPriceService.getLowestPriceProductByKeyword(keyword);
    }

}
