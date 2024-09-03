package com.example.redis.service;

import com.example.redis.vo.Keyword;
import com.example.redis.vo.Product;
import com.example.redis.vo.ProductGrp;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public int setNewProductGrpToKeyword(String productGrpId, String proGrpId, double score){
        redisTemplate.opsForZSet().add(productGrpId,proGrpId, score);
        int rank = redisTemplate.opsForZSet().rank(productGrpId, proGrpId).intValue();
        return rank;

    }

    @Override
    public Keyword getLowestPriceProductByKeyword(String keyword) {
        Keyword inputKeyword = new Keyword();
        List<ProductGrp> tempProductGrp = new ArrayList<>();


        //1. Keyword를 통해 ProductGroup 가져오기 (10개)
        tempProductGrp = GetProductGrpUsingKeyword(keyword);

        //2. 가져온 정보들을 Return할 Object에 넣기
        //3. Object를 리턴
        return inputKeyword;
    }

    public List<ProductGrp> GetProductGrpUsingKeyword(String keyword){
        List<ProductGrp> returnInfo = new ArrayList<>();


        // input 받은 Keyword로 ProductGrpId를 조회
        List<String> productGrpIdList = new ArrayList<>();
        productGrpIdList = List.copyOf(redisTemplate.opsForZSet().reverseRange(keyword, 0, 9)); // 큰 스코어 -> 작은 스코어 순
        List<Product> tempProdList = new ArrayList<>();

        for(final String productGrpId : productGrpIdList){
            // Loop 타면서  ProductGroup으로 Product: price 가져오기 (10개)

            ProductGrp tempProductGrp = new ProductGrp();

            Set ProductAndPriceList = redisTemplate.opsForZSet().rangeWithScores(productGrpId, 0, 9);
            ProductAndPriceList = redisTemplate.opsForZSet().rangeWithScores(productGrpId, 0, 9);
            Product tempProduct = new Product();
            // loop 타면서 Product obj에 bind
            Iterator<String> prodPriceObj = productGrpIdList.iterator();

            while(prodPriceObj.hasNext()){
                ObjectMapper objectMapper = new ObjectMapper();
                // { "value" : 00-11111 }, {"socre" : 10000 }
                Map<String, Object>  productPriceMap = objectMapper.convertValue(prodPriceObj.next(), Map.class);

                // Product Obj bind
                tempProduct.setProductId(productPriceMap.get("value").toString()); // prod_id
                tempProduct.setPrice(Integer.parseInt(productPriceMap.get("score").toString())); // es에서 검색된 score
                tempProdList.add(tempProduct);
            }
            // 10개 Product price 입력
            tempProductGrp.setProdGrpId(productGrpId);
            tempProductGrp.setProductList(tempProdList);
            returnInfo.add(tempProductGrp);
        }

        return returnInfo;
    }


}
