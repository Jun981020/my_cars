package com.jproject.my_cars.web;

import com.jproject.my_cars.domain.board.member_board.MemberBoard;
import com.jproject.my_cars.domain.board.member_board.MemberBoardService;
import com.jproject.my_cars.domain.likes.Likes;
import com.jproject.my_cars.domain.likes.LikesService;
import com.jproject.my_cars.domain.member.Grade;
import com.jproject.my_cars.domain.member.Member;
import com.jproject.my_cars.domain.member.MemberService;
import com.jproject.my_cars.dto.MemberJoinDto;
import com.jproject.my_cars.dto.MemberLoginDto;
import com.jproject.my_cars.web.session.SessionManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final SessionManager sessionManager;
    private final MemberBoardService memberBoardService;
    private final LikesService likesService;

    @GetMapping("/member/login")
    public String loginForm(){
        log.info("/member/login");
        return "member/login";
    }
    @GetMapping("/member/join")
    public String joinForm(){
        log.info("/member/join");
        return "member/join";
    }
    @GetMapping("/member/checkLoginIdDuplicate")
    @ResponseBody
    public boolean check_login_id_duplicate(String login_id){
        log.info("/member/checkLoginIdDuplicate");
        return memberService.check_login_id(login_id);
    }
    @PostMapping("/member/joinAction")
    public String join_action(@ModelAttribute MemberJoinDto memberJoinDto){
        log.info("/member/joinAction");
        Member member = Member.createMember(memberJoinDto.getLogin_id(), memberJoinDto.getPassword(), memberJoinDto.getName(), memberJoinDto.getEmail(), memberJoinDto.getPhone(), Grade.SILVER);
        memberService.joinMember(member);
        return "redirect:/member/login";
    }

    @GetMapping("/member/check_IDPW")
    @ResponseBody
    public boolean check_login_id_pw(@RequestParam("id") String id,
                                     @RequestParam("password") String password){
        log.info("/member/check_IDPW");
        return memberService.check_IDPW(id, password);
    }

    @PostMapping("/member/loginAction")
    public String login_action(@ModelAttribute MemberLoginDto dto , HttpServletResponse response,HttpServletRequest request){
        log.info("/member/loginAction");
        Member member = memberService.getMemberByLoginId(dto.getId());
        HttpSession session = request.getSession();
        session.setAttribute("mode","member");
        sessionManager.createSession(member,response);

        return "redirect:/main";
    }
    @GetMapping("/member/mypage")
    public String my_page(HttpServletRequest request, Model model){
        log.info("/member/mypage");
        Member member = (Member) sessionManager.getSession(request);
        if(member == null){
            return "redirect:/session/empty";
        }
        List<MemberBoard> memberBoardList = memberBoardService.getMemberBoardList();
        List<Likes> likesList = likesService.getLikesByMemberId(member.getId());
        model.addAttribute("likesList",likesList);
        model.addAttribute("member",member);
        model.addAttribute("boardList",memberBoardList);
        return "member/mypage";
    }
    @GetMapping("/session/empty")
    @ResponseBody
    public String session_empty(){
        log.info("/session/empty");
        return "<script>alert('세션만료됨 로그인페이지로 이동합니다');location.href = '/member/login' </script>";
    }
    @GetMapping("/member/logout")
    public String member_logout(HttpServletRequest request){
        log.info("/member/logout");
        sessionManager.expire(request);
        request.getSession().invalidate();
        return "redirect:/main";
    }
    @GetMapping("/member/removeLikes/{num}")
    public String member_remove_likes(@PathVariable(name = "num")int num,HttpServletRequest request){
        log.info("/member/removeLikes/{num}");
        Member member = (Member) sessionManager.getSession(request);
        likesService.removeLikes(member.getId(),(long)num);
        return "redirect:/member/mypage";
    }



}
