package com.example.redis.map.controller;

import com.example.redis.map.dto.StoreResponseDTO;
import com.example.redis.map.service.MapRedisService;
import com.example.redis.map.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StoreController {
    private final StoreService service;
    private final MapRedisService redisService;

    @GetMapping("/getRestStoreList")
    public  List<StoreResponseDTO> getStores(Model model, double latitude, double longitude){
        List<StoreResponseDTO> storeList = service.getStoreFilter(latitude,longitude);
        System.out.println(storeList);
        return storeList;

    }

    @GetMapping("/getRedisSelect")
    public List<StoreResponseDTO> save(){
        List<StoreResponseDTO> storeList = service.getStoreList();
        System.out.println("디비조회");
        return storeList;
    }

    @GetMapping("/getRedisSelect2")
    public List<StoreResponseDTO> save2(){
        List<StoreResponseDTO> storeList = service.getRedisStoreFilter();
        System.out.println(storeList);
        System.out.println("디비조회");
        return storeList;
    }
}
