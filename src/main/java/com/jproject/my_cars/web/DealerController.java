package com.jproject.my_cars.web;

import com.jproject.my_cars.configuration.WebMvcConfig;
import com.jproject.my_cars.domain.board.dealer_board.DealerBoard;
import com.jproject.my_cars.domain.board.dealer_board.DealerBoardService;
import com.jproject.my_cars.domain.cars.Car;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Controller
@RequiredArgsConstructor
@Slf4j
public class DealerController {
    private final DealerService dealerService;
    private final SessionManager sessionManager;
    private final DealerBoardService dealerBoardService;

    @GetMapping("/dealer/login")
    public String dealer_login(){
        log.info("/dealer/login");
        return "dealer/dealer_login";
    }
    @GetMapping("/dealer/join")
    public String dealer_join(){
        log.info("/dealer/join");
        return "dealer/dealer_join";
    }
    @PostMapping("/dealer/loginAction")
    public String dealer_login_action(@ModelAttribute DealerLoginDto dto, HttpServletResponse response, HttpServletRequest request){
        log.info("/dealer/loginAction");
        log.info("dto"+dto.toString());
        Dealer dealer = dealerService.findOneByLoginId(dto.getId());
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
        log.info("/dealer/checkLoginIdDuplicate");
        return dealerService.check_join_id(login_id);
    }
    @PostMapping("/dealer/joinAction")
    public String dealer_join(@ModelAttribute DealerJoinDto dto){
        log.info("/dealer/joinAction");
        LocalDate date = LocalDate.parse(dto.getAcquisition_date());
        Card card = new Card(dto.getEmployee_number(),dto.getCompany(),date);
        Dealer dealer = Dealer.joinDealer(dto, card);
        dealerService.saveDealer(dealer);
        return "redirect:/main";
    }
    @GetMapping("/dealer/check_IDPWNU")
    @ResponseBody
    public boolean dealer_check_id_pw_nu(@RequestParam("id")String id,
                                         @RequestParam("password")String password,
                                         @RequestParam("number")String number){
        log.info("/dealer/check_IDPWNU");
        System.out.println("id = " + id);
        System.out.println("password = " + password);
        System.out.println("number = " + number);
        return dealerService.check_login_id_pw_num(id,password,number);
    }
    @GetMapping("/dealer/dealerPage")
    public String dealer_page(HttpServletRequest request, Model model){
        log.info("/dealer/dealerPage");
        Dealer data = (Dealer) sessionManager.getSession(request);
        if(data == null){
            return "redirect:/session/empty";
        }
        Dealer dealer = dealerService.findOneById(data.getId());
        List<DealerBoard>  dealerBoardList= dealerBoardService.getDealerBoardList();
        List<Car> carList = dealerService.findOneOfDealerPage(data.getId());
        model.addAttribute("dealer",dealer);
        model.addAttribute("boardList",dealerBoardList);
        model.addAttribute("list",carList);
        return "dealer/dealer_page";
    }
    @GetMapping("/dealer/logout")
    public String dealer_logout(HttpServletRequest request){
        log.info("/dealer/logout");
        sessionManager.expire(request);
        request.getSession().invalidate();
        return "redirect:/main";
    }

}
