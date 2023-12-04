package com.example.redis.map.controller;

import com.example.redis.map.dto.StoreResponseDTO;
import com.example.redis.map.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ViewController {
    private final StoreService service;
    @GetMapping("/showpage")
    public String showmap(){
        return "map/test2";
    }

    @GetMapping("/getStoreList")
    public String getStoreList(Model model, double latitude, double longitude){
        List<StoreResponseDTO> storeList = service.getStoreFilter(latitude,longitude);
        System.out.println(storeList);
        model.addAttribute("storelist",storeList);
        return "map/test2";
    }

}
