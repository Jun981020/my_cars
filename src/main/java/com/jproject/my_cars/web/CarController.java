package com.jproject.my_cars.web;

import com.jproject.my_cars.domain.cars.Car;
import com.jproject.my_cars.domain.cars.CarService;
import com.jproject.my_cars.domain.cars.img.ImgService;
import com.jproject.my_cars.dto.CarPostsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;
    private final ImgService imgService;

    @GetMapping(value = "/cars")
    @ResponseBody
    public ResponseEntity<List<Car>> cars(Model model){
        List<Car> car = carService.getAll();
        model.addAttribute("car",car);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }
    @GetMapping("/cars/carOne/{num}")
    public String car_one(@PathVariable("num")int num,Model model){
        Car car = carService.getOne((long) num);
        System.out.println("car.getImages() = " + car.getImages());
        model.addAttribute("car",car);
        return "cars_one";
    }
    @PostMapping("/cars/posts")
    public String car_posts(){

        return "";
    }
    @PostMapping("/dto")
    @ResponseBody
    public ResponseEntity<CarPostsDto> dtoTest(@ModelAttribute() CarPostsDto dto){
        return ResponseEntity.ok(dto);
    }
}
