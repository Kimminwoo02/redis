package com.example.redis.service;

import com.example.redis.vo.Product;
import com.example.redis.vo.ProductGrp;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class LowestPriceServiceImpl implements LowestPriceService {
    private final RedisTemplate redisTemplate;

    @Override
    public Set getZsetValue(String key) {
        Set myTempSet = new HashSet<>();
        myTempSet = redisTemplate.opsForZSet().rangeWithScores(key,0,9);

        return myTempSet;
    }

    @Override
    public int setNewProduct(Product newProduct) {
        int rank;
        redisTemplate.opsForZSet().add(newProduct.getProductId(),newProduct.getProductId(),newProduct.getPrice());
        rank = Objects.requireNonNull(redisTemplate.opsForZSet().rank(newProduct.getProductId(), newProduct.getProductId())).intValue();
        return rank;
    }

    @Override
    public int setNewProductGrp(ProductGrp newProductGrp) {
        List<Product> productList = newProductGrp.getProductList();
        String productId = productList.get(0).getProductId();
        int price = productList.get(0).getPrice();
        redisTemplate.opsForZSet().add(newProductGrp.getProdGrpId(),productId,price);
        int prodCount = redisTemplate.opsForZSet().zCard(newProductGrp.getProdGrpId()).intValue();
        return prodCount;
    }

    public int setNewProductGrpToKeyword(String productGrpId, String proGrpId){
        
    }


}
