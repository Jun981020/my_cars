package com.jproject.my_cars.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jproject.my_cars.domain.cars.Car;
import com.jproject.my_cars.domain.cars.CarService;
import com.jproject.my_cars.domain.dealer.Dealer;
import com.jproject.my_cars.domain.member.Member;
import com.jproject.my_cars.web.session.SessionManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {

    private final SessionManager sessionManager;
    private final CarService carService;

    @GetMapping("/hello")
    public String test(){
        return "test";
    }

    @GetMapping("/main")
    public String main(HttpServletRequest request,Model model){
        System.out.println("request.getSession().getServletContext().getRealPath(\"/\") = " + request.getSession().getServletContext().getRealPath("/"));
        List<Car> topTwoCar = carService.getTopTwoCar();
        model.addAttribute("top2car",topTwoCar);
        return "main";
    }
    @GetMapping("/main/getSessionTypeName")
    @ResponseBody
    public HashMap<String,Object> get_session_type_name(HttpServletRequest request){
        HashMap<String, Object> map = new HashMap<>();
        String typeName = sessionManager.getSession(request).getClass().getSimpleName();
        System.out.println("typeName = " + typeName);
        map.put(typeName,sessionManager.getSession(request));
        System.out.println("map = " + map);
        return map;
    }
    @GetMapping("/main/getSessionMemberId")
    @ResponseBody
    public Long get_session_member_id(HttpServletRequest request){
        Member m = (Member) sessionManager.getSession(request);
        if(m == null){
            return null;
        }else{
            return m.getId();
        }
    }
    @GetMapping("/main/getSessionEmpty")
    @ResponseBody
    public boolean get_session_empty(HttpServletRequest request){
        boolean result;
        Object session = sessionManager.getSession(request);
        if(session != null){
            result = true;
            System.out.println("result = " + result);
            return true;
        }else{
            result = false;
            System.out.println("result = " + result);
            return false;
        }
    }
    @GetMapping("/main/getSessionDealerId")
    @ResponseBody
    public Long get_session_dealer_id(HttpServletRequest request){
        Dealer d = (Dealer) sessionManager.getSession(request);
        if(d == null){
            return null;
        }else{
            return d.getId();
        }
    }
    @GetMapping("/main/getSessionTypeLoginId")
    @ResponseBody
    public String get_session_type_login_id(HttpServletRequest request) throws JsonProcessingException {
        HashMap<String, String> map = new HashMap<>();
        Object session = sessionManager.getSession(request);
        //로그인 하지 않았을때
        if(session == null){
            map.put("data",null);
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(map);
            return jsonString;

        }
        String typeName = sessionManager.getSession(request).getClass().getSimpleName();
        if(typeName.equals("Member")){
            Member member = (Member) sessionManager.getSession(request);
            map.put("session","member");
            map.put("data",member.getLoginId());
        }else{
            Dealer dealer = (Dealer) sessionManager.getSession(request);
            map.put("session","dealer");
            map.put("data",dealer.getLoginId());
        }
        //Hashmap 을 JSON으로 변환
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(map);
        return jsonString;
    }
}
