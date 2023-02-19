package com.jproject.my_cars.web;

import com.jproject.my_cars.domain.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ReplyController {
//
//    private final MemberService memberService;
////    private final BoardService boardService;
//    private final ReplyService replyService;

//    @PostMapping("/reply/writeAction")
//    public String reply_write_action(@RequestParam("loginId") String loginId,
//                                     @RequestParam("boardId") String boardId,
//                                     @RequestParam("content") String content){
//        Member m = memberService.getMemberByLoginId(loginId);
//        Board b = boardService.getBoardById(Long.parseLong(boardId));
//        Reply reply = Reply.createReply(m, b, content);
//        reply.setBoard(b);
//        replyService.saveReply(reply);
//        return "redirect:/board/boardOne/"+boardId;
//    }
}
