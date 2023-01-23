package com.jproject.my_cars.domain.cars.img;

import com.jproject.my_cars.domain.cars.Car;
import com.jproject.my_cars.domain.cars.CarRepository;
import com.jproject.my_cars.domain.cars.Fuel;
import com.jproject.my_cars.domain.cars.Manufacture;
import com.jproject.my_cars.domain.cars.option.Options;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ImgServiceTest {

    @Autowired
    private ImgRepository imgRepository;
    @Autowired
    private CarRepository carRepository;
    @Test
    public void imageTest(){
        Options options1 = Options.putOption("sunroof", "태양열 막는 기능");
        Options options2 = Options.putOption("nav", "길안내 서비스");
        Img img = Img.addImg("bmw-320-main", "img/bmw/320_i/bmw_320i_main.webp");
        Car cars = Car.registrationCar("bmw-320i", 3000, "2022", "130000", false,"대구", Fuel.ELECTRIC,"BMW", Manufacture.FOREIGN);
        cars.addOption(options1);
        cars.addOption(options2);
        img.setCar(cars);
        carRepository.save(cars);
        carRepository.flush();
        Car findCar = carRepository.findByName("bmw-320i");


//        Img i = imgRepository.findById(1L).get();
//        assertThat(i.getCar().getName()).isEqualTo("test1");
    }
    @Test
    public void imgList(){
        Car car = carRepository.findById(3L).get();
        List<Img> images = car.getImages();
        for (Img image : images) {
            System.out.println("image = " + image);
        }
    }
    @Test
    @DisplayName("메인 이미지만 가져오기")
    public void mainImageGet(){

    }

}