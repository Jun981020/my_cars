package com.jproject.my_cars.web;

import com.jproject.my_cars.domain.board.reply.ReplyRole;
import com.jproject.my_cars.domain.board.reply.dealer_board_reply.DealerBoardReplyService;
import com.jproject.my_cars.domain.board.reply.member_board_reply.MemberBoardReplyService;
import com.jproject.my_cars.dto.DealerBoardReplWriteDto;
import com.jproject.my_cars.dto.MemberBoardReplyWriteDto;
import com.jproject.my_cars.web.session.SessionManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ReplyController {

    private final MemberBoardReplyService memberBoardReplyService;
    private final DealerBoardReplyService dealerBoardReplyService;
    private final SessionManager sessionManager;

    @PostMapping("/reply/writeAction/member")
    public String reply_write_action_member(@RequestParam("loginId") String loginId,
                                            @RequestParam("boardId") String boardId,
                                            @RequestParam("content") String content,
                                            HttpServletRequest request){
        log.info("/reply/writeAction/member");
        String clazzName = sessionManager.getSession(request).getClass().getSimpleName();
        MemberBoardReplyWriteDto dto = new MemberBoardReplyWriteDto();
        dto.setContent(content);
        dto.setLoginId(loginId);
        dto.setBoardId(boardId);
        if (clazzName.equals("Member")){
            dto.setReplyRole(ReplyRole.MEMBER);
        }else{
            dto.setReplyRole(ReplyRole.DEALER);
        }
        memberBoardReplyService.saveReply(dto);
        return "redirect:/board/memberBoard/"+boardId;
    }
    @PostMapping("/reply/writeAction/dealer")
    public String reply_write_action_dealer(@RequestParam("loginId") String loginId,
                                            @RequestParam("boardId") String boardId,
                                            @RequestParam("content") String content,
                                            HttpServletRequest request){
        log.info("/reply/writeAction/dealer");
        String clazzName = sessionManager.getSession(request).getClass().getSimpleName();
        DealerBoardReplWriteDto dto = new DealerBoardReplWriteDto();
        dto.setContent(content);
        dto.setLoginId(loginId);
        dto.setBoardId(boardId);
        if (clazzName.equals("Member")){
            dto.setReplyRole(ReplyRole.MEMBER);
        }else{
            dto.setReplyRole(ReplyRole.DEALER);
        }
        dealerBoardReplyService.saveReply(dto);
        return "redirect:/board/dealerBoard/"+boardId;
    }
    @GetMapping("/reply/remove/member")
    public String reply_remove_action_member(@RequestParam("id")int id,@RequestParam("num")int num){
        log.info("/reply/remove/member");
        Long replyId = (long)id;
        memberBoardReplyService.removeReply(replyId);
        return "redirect:/board/memberBoard/"+num;
    }
    @GetMapping("/reply/remove/dealer")
    public String reply_remove_action_dealer(@RequestParam("id")int id,@RequestParam("num")int num){
        log.info("/reply/remove/dealer");
        Long replyId = (long)id;
        dealerBoardReplyService.removeReply(replyId);
        return "redirect:/board/dealerBoard/"+num;
    }
    @PutMapping("/reply/modify/{cat}")
    public String reply_modify_member(@PathVariable("cat")String cat,
                                      @RequestParam("id")int id,
                                      @RequestParam("content")String content,
                                      @RequestParam("num")int num){
        log.info("/reply/modify/{cat}");
        if(cat.equals("member")){
            memberBoardReplyService.modifyReply(id,content);
        }else{
            dealerBoardReplyService.modifyReply(id,content);
        }
        return "redirect:/board/"+cat+"Board/"+num;
    }

}
