package com.jproject.my_cars.domain.cars.car_options;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CarOptionsService {

    private final CarOptionsRepository carOptionsRepository;

    public List<CarOptions> getCarOptionsListByCarId(Long car_id){
        return carOptionsRepository.findByCarId(car_id);
    }
}
