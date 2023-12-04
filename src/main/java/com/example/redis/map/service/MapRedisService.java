package com.example.redis.map.service;

import com.example.redis.map.dto.StoreResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MapRedisService {
    private final RedisTemplate<String,String> redisTemplate;
    //store에 저장된 레코드를 json문자열로 변환하고 json문자열로 정의된 store 데이터를
    // dto 객체로 변환하기 위해 ObjectMapper를 사용 -> 싱글톤으로 관리
    private final ObjectMapper objectMapper;
    private HashOperations<String, String, String>  hashOperations;
    // service가 실행되기 전에 초기화작업 진행
    // HashOperations이 매번 초기화되지 않고 빈의 라이프사이클에서 한 번만 실행해서
    // 초기화할 수 있도록 작업



    @PostConstruct // Was가 실행되면서 빈의 라이프사이클에 맞춰서 한번만 실행되도록 한다.
    public void init (){
        this.hashOperations = redisTemplate.opsForHash();

    }
    // 디비데이터를 조회해서 레디스에 저장하기
    // store 데이블에 있는 데이터를 redis에 동기화하기 위해 저장
    public void save(StoreResponseDTO storedto) {
        try {
            hashOperations.put("store",storedto.getId().toString(), // 서브 키
                                serializeStoreDto(storedto)  // DTO를 -> JSON 문자열로 변환
                    );
            log.info("=----=-=-=-=-=-=-" + storedto.getId());
            log.info("=----=-=-=-=-=-=-" + storedto.getStoreAddress());
        } catch (JsonProcessingException e) {
            System.out.println("저장 오류 ㅠ " + e.getMessage());

        }
    }
    // DTO를  JSON 문자열로 변환
    private String serializeStoreDto(StoreResponseDTO storedto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(storedto);
    }


    // json문자열을 DTO로 변홚

    private StoreResponseDTO deserializeStoreDto(String value) throws JsonProcessingException {
        return objectMapper.readValue(value, StoreResponseDTO.class);
    }



    // redis에 저장된 데이터를 조회
    public List<StoreResponseDTO> redisFindAll(){
        List<StoreResponseDTO> list = new ArrayList<>();
        // store 저장된 모든 서브키밸류를 조회해서 values만 빼서 반복작업
        // = value하나를 dto로 변경

            // hash에 저장된 한 개의 아이템을 꺼내서 dto로 변환
            try {
                for (String value : hashOperations.entries("store").values()) {
                    StoreResponseDTO dto = deserializeStoreDto(value);
                    // 변환된 dto를 list에 저장
                    list.add(dto);
                }
                return list;
            } catch (JsonProcessingException e) {
                //redis에서 데이터를 조회하는데 에러 발생된 상황 - 문제가 발생되면
                return Collections.emptyList();// 빈 리스트를 리턴해서 문제가 있음을 알려준다.

            }


    }


}
