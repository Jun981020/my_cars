package com.jproject.my_cars.web;

import com.jproject.my_cars.domain.dealer.DealerService;
import com.jproject.my_cars.dto.DealerLoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
public class DealerController {

    private final DealerService dealerService;

    @GetMapping("/dealer/login")
    public String dealer_login(@ModelAttribute DealerLoginDto dto){




        return "dealer_login";
    }

}
