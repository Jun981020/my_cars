package com.jproject.my_cars.web;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class MainController {

    @GetMapping("/hello")
    public String test(){
        return "여전히 뻔한 헬로우월드";
    }

    @GetMapping("main")
    public String main(){
        return "main";
    }
}
