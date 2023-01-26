package com.jproject.my_cars.domain.cars;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;

    public List<Car> getAll(){
        return carRepository.findAll();
    }
    public Car getOne(Long id){
        return carRepository.findById(id).get();
    }

}
