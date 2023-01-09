package com.jproject.my_cars.domain.cars;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CarsTest {

    @Autowired
    private CarRepository carsRepository;
    @Autowired
    private OptionRepository optionRepository;

    @Test
    public void save(){
        Options options1 = Options.putOption("sunroof", "태양열 막는 기능");
        Options options2 = Options.putOption("nav", "길안내 서비스");
        Car cars = Car.registrationCar("bmw-320", 2200, "2022", "130000", false,"대구",Fuel.ELECTRIC,"hyundai",Manufacture.FOREIGN);
        cars.addOption(options1);
        cars.addOption(options2);
        carsRepository.save(cars);
        carsRepository.flush();
        Car cars1 = carsRepository.findAll().get(0);
        Assertions.assertThat(cars.getName()).isEqualTo(cars1.getName());
    }

}