package com.example.redis.service;


import com.example.redis.vo.Keyword;
import com.example.redis.vo.Product;
import com.example.redis.vo.ProductGrp;

import java.util.Set;

public interface LowestPriceService {

    Set getZsetValue(String key);
    int setNewProduct(Product newProduct);

    int setNewProductGrp(ProductGrp newProductGrp);

    int setNewProductGrpToKeyword(String keyword, String proGrpId, double score);

    Keyword getLowestPriceProductByKeyword(String keyword);

    int getZsetValueWithStatus(String key);

    Set<String> getZsetValueWithSpecificException(String key);
}
