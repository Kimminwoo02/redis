package com.example.redis.map.service;

import com.example.redis.map.dao.StoreDAO;
import com.example.redis.map.dto.StoreResponseDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {
    private final StoreDAO storeDAO;
    private final MapRedisService redisService;
    @Override
    public List<StoreResponseDTO> getStoreList() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        return storeDAO.getStoreList().stream().map(
                        entity -> mapper.map(entity, StoreResponseDTO.class))
                .collect(Collectors.toList());

    }

    @Override
    public List<StoreResponseDTO> getStoreFilter(double latitude, double longitude) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        List<StoreResponseDTO> storelist = redisService.redisFindAll();

        // 오류가 발생하면 빈 리스트를 리턴하도록 했기 때문에 리스트가 empty가 아니라는 것은 정상 처리
        if( !storelist.isEmpty()){
            return storelist;
        }
        System.out.println("-==============="+storelist);
        return storeDAO.getStoreList()
                .stream().map(store->mapper.map(store, StoreResponseDTO.class))
                        .collect(Collectors.toList());


//
//        List<StoreResponseDTO> collect = storeDAO.getStoreList().stream().map(
//                        entity ->
//                        {
//                            entity.setDistance(calculateDistance(latitude, longitude, entity.getLatitude(), entity.getLongitude()));
//                            return mapper.map(entity, StoreResponseDTO.class);
//                        })
//                .filter(entity -> entity.getDistance() < 2)
//                .collect(Collectors.toList());


//        System.out.println(collect);


    }
    // redis에서 데이터를 조회하나 redis에서 문제가 발생한 경우 데이터베이스에서 데이터를 조회

    @Override
    public List<StoreResponseDTO> getRedisStoreFilter() {
        return null;
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        lat1 = Math.toRadians(lat1);
        lon1 = Math.toRadians(lon1);
        lat2 = Math.toRadians(lat2);
        lon2 = Math.toRadians(lon2);

        double earthRadius = 6371; //Kilometers - 지도의 반지름
        return earthRadius * Math.acos(Math.sin(lat1) * Math.sin(lat2)
                + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));

    }
}
