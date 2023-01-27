package com.jproject.my_cars.web;

import com.jproject.my_cars.domain.member.Member;
import com.jproject.my_cars.web.session.SessionManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final SessionManager sessionManager;

    @GetMapping("/hello")
    public String test(){
        return "여전히 뻔한 헬로우월드";
    }

    @GetMapping("/main")
    public String main(HttpServletRequest request, Model model){
        Member member = (Member) sessionManager.getSession(request);
        model.addAttribute("member",member);
        return "main";
    }
}
