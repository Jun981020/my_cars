package com.jproject.my_cars.web;

import com.jproject.my_cars.domain.dealer.Dealer;
import com.jproject.my_cars.domain.dealer.DealerService;
import com.jproject.my_cars.domain.dealer.employee.Card;
import com.jproject.my_cars.dto.DealerJoinDto;
import com.jproject.my_cars.dto.DealerLoginDto;
import com.jproject.my_cars.web.session.SessionManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@Slf4j
public class DealerController {
    private final DealerService dealerService;
    private final SessionManager sessionManager;

    @GetMapping("/dealer/login")
    public String dealer_login(){
        return "dealer/dealer_login";
    }
    @GetMapping("/dealer/join")
    public String dealer_join(){
        return "dealer/dealer_join";
    }
    @PostMapping("/dealer/loginAction")
    public String dealer_login_action(@ModelAttribute DealerLoginDto dto, HttpServletResponse response, HttpServletRequest request){
        log.info("여기까지 들어옴 dto value:"+dto);
        Dealer dealer = dealerService.findOne(dto.getId());
        if(sessionManager.getSession(request) != null){
            sessionManager.expire(request);
        }
        if(request.getSession().getAttribute("mode") != null){
            request.getSession().invalidate();
        }
        sessionManager.createSession(dealer,response);
        request.getSession().setAttribute("mode","dealer");
        return "redirect:/main";
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
    @ResponseBody
    public boolean dealer_check_id_pw_nu(@RequestParam("id")String id,
                                         @RequestParam("password")String password,
                                         @RequestParam("number")String number){
        log.info("check_idpwnu");
        return dealerService.check_login_id_pw_num(id,password,number);
    }
    @GetMapping("/dealer/dealerPage")
    public String dealer_page(HttpServletRequest request, Model model){
        Dealer entity = (Dealer) sessionManager.getSession(request);
        System.out.println("entity = " + entity);
        model.addAttribute("dealer",entity);
        return "dealer/dealer_page";
    }
    @GetMapping("/dealer/logout")
    public String dealer_logout(HttpServletRequest request){
        sessionManager.expire(request);
        request.getSession().invalidate();
        return "redirect:/main";
    }

}
