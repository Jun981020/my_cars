package com.jproject.my_cars.web;

import com.jproject.my_cars.domain.cars.Car;
import com.jproject.my_cars.domain.cars.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping("/cars")
    public String cars(Model model){
        List<Car> car = carService.getAll();
        model.addAttribute("car",car);
        return "cars";
    }
}
