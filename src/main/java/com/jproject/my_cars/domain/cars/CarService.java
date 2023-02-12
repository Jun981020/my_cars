package com.jproject.my_cars.domain.cars;

import com.jproject.my_cars.domain.cars.option.OptionRepository;
import com.jproject.my_cars.domain.cars.option.Options;
import com.jproject.my_cars.domain.dealer.Dealer;
import com.jproject.my_cars.dto.CarPostsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class CarService {
    private final CarRepository carRepository;
    private final OptionRepository optionRepository;

    public List<Car> getAll(){
        return carRepository.findAll();
    }
    public Car getOne(Long id){
        return carRepository.findById(id).get();
    }
    @Transactional
    public void carUpPoint(Car car){
        car.upPoint();
    }

    @Transactional
    public Car registration(Dealer dealer, CarPostsDto dto,String[] options) {
        Car car = Car.registrationCar(
                dto.getName(),
                dto.getPrice(),
                dto.getYear(),
                dto.getDistance_driven(),
                Boolean.parseBoolean(dto.getAccident_history()),
                dto.getArea(),
                dto.getFuel(),
                dto.getManufacture());
        car.setDealer(dealer);
        for (String name : options) {
            Options findOption = optionRepository.findByName(name);
            log.info("findOption : " + findOption);
            car.addOption(findOption);
        }
        carRepository.save(car);
        return car;
    }
}
