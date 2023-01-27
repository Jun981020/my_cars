package com.jproject.my_cars.web;

import com.jproject.my_cars.domain.dealer.Dealer;
import com.jproject.my_cars.domain.dealer.DealerService;
import com.jproject.my_cars.domain.dealer.employee.Card;
import com.jproject.my_cars.dto.DealerJoinDto;
import com.jproject.my_cars.dto.DealerLoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class DealerController {

    private final DealerService dealerService;

    @GetMapping("/dealer/login")
    public String dealer_login(@ModelAttribute DealerLoginDto dto){




        return "dealer_login";
    }
    @GetMapping("/dealer/join")
    public String dealer_join(){
        return "dealer_join";
    }
    @GetMapping("/dealer/checkLoginIdDuplicate")
    @ResponseBody
    public boolean check_login_id_duplicate(String login_id){
        return dealerService.check_join_id(login_id);
    }
    @PostMapping("/dealer/joinAction")
    public String dealer_join(DealerJoinDto dto){
        Card card = new Card(dto.getEmployee_number(),dto.getCompany(),dto.getAcquisition_date());
        Dealer dealer = Dealer.joinDealer(dto, card);
        dealerService.saveDealer(dealer);
        return "redirect:/main";
    }
    @GetMapping("/dealer/check_IDPWNU")
    public boolean dealer_check_id_pw_nu(@RequestParam("id")String id,
                                         @RequestParam("password")String password,
                                         @RequestParam("number")String number){
        return dealerService.check_login_id_pw_num(id,password,number);
    }

}
