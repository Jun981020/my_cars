package com.jproject.my_cars.web;

import com.jproject.my_cars.domain.board.reply.member_board_reply.MemberBoardReplyService;
import com.jproject.my_cars.domain.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ReplyController {

    private final MemberService memberService;
    private final MemberBoardReplyService memberBoardReplyService;

    @PostMapping("/reply/writeAction/member")
    public String reply_write_action_member(@RequestParam("loginId") String loginId,
                                            @RequestParam("boardId") String boardId,
                                            @RequestParam("content") String content){

        return "redirect:/board/boardOne/"+boardId;
    }
    @PostMapping("/reply/writeAction/dealer")
    public String reply_write_action_dealer(@RequestParam("loginId") String loginId,
                                     @RequestParam("boardId") String boardId,
                                     @RequestParam("content") String content){
        return "redirect:/board/boardOne/"+boardId;
    }
}
