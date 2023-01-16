package com.jproject.my_cars.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CarController {

    @GetMapping("cars")
    public String cars(){



        return "cars";
    }
}
