package com.jproject.my_cars.web;

import com.jproject.my_cars.domain.member.Member;
import com.jproject.my_cars.web.session.SessionManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {

    private final SessionManager sessionManager;

    @GetMapping("/hello")
    public String test(){
        return "여전히 뻔한 헬로우월드";
    }

    @GetMapping("/main")
    public String main(){
        return "main";
    }
    @GetMapping("/main/getSessionTypeName")
    @ResponseBody
    public HashMap<String,Object> get_session_type_name(HttpServletRequest request){
        HashMap<String, Object> map = new HashMap<>();
        String typeName = sessionManager.getSession(request).getClass().getSimpleName();
        map.put(typeName,sessionManager.getSession(request));
        return map;
    }
    @GetMapping("/main/getSessionMemberId")
    @ResponseBody
    public Long get_session_member_id(HttpServletRequest request){
        Member m = (Member) sessionManager.getSession(request);
        return m.getId();
    }
}
