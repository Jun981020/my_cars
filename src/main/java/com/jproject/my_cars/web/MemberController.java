package com.jproject.my_cars.web;

import com.jproject.my_cars.domain.member.Member;
import com.jproject.my_cars.domain.member.MemberService;
import com.jproject.my_cars.domain.member.Role;
import com.jproject.my_cars.dto.MemberJoinDto;
import com.jproject.my_cars.dto.MemberLoginDto;
import com.jproject.my_cars.dto.TestDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/login")
    public String loginForm(){
        return "login";
    }
    @GetMapping("/member/join")
    public String joinForm(){
        return "join";
    }
    @GetMapping("/member/checkLoginIdDuplicate")
    @ResponseBody
    public boolean check_login_id_duplicate(String login_id){
        return memberService.check_login_id(login_id);
    }
    @PostMapping("/member/joinAction")
    public String join_action(@ModelAttribute MemberJoinDto memberJoinDto){
        Member member = Member.createMember(memberJoinDto.getLogin_id(), memberJoinDto.getPassword(), memberJoinDto.getName(), memberJoinDto.getEmail(), memberJoinDto.getPhone(), Role.SILVER);
        memberService.joinMember(member);
        return "redirect:/member/login";
    }

    @GetMapping("/member/check_IDPW")
    @ResponseBody
    public boolean check_login_id_pw(@RequestParam("id") String id,
                                     @RequestParam("password") String password){
        return memberService.check_IDPW(id, password);
    }

    @PostMapping("/member/loginAction")
    public String login_action(@ModelAttribute MemberLoginDto dto ,HttpServletRequest request){
        Member member = memberService.getMember(dto.getId());
        HttpSession session = request.getSession();
        session.setAttribute("member",member);

        Member member1 = (Member) session.getAttribute("member");
        System.out.println("member = " + member1);
        return "redirect:/main";
    }
    @GetMapping("/member/mypage")
    public String my_page(){
        return "mypage";
    }

}
