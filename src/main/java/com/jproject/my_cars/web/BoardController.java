package com.jproject.my_cars.web;

import com.jproject.my_cars.domain.board.dealer_board.DealerBoard;
import com.jproject.my_cars.domain.board.dealer_board.DealerBoardService;
import com.jproject.my_cars.domain.board.member_board.MemberBoard;
import com.jproject.my_cars.domain.board.member_board.MemberBoardService;
import com.jproject.my_cars.dto.*;
import com.jproject.my_cars.web.session.SessionManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final DealerBoardService dealerBoardService;
    private final MemberBoardService memberBoardService;
    private final SessionManager sessionManager;
    @GetMapping("/board/list/member")
    public String board_list(@RequestParam(value = "page",defaultValue = "0")int pageNum, Model model){
        log.info("/board/list/member");
        Page<MemberBoard> page = memberBoardService.memberBoardList(PageRequest.of(pageNum,10));
        model.addAttribute("page",page);
        model.addAttribute("cat","member");
        return "board/board_member";
    }
    @GetMapping("/board/list/dealer")
    public String board_list_dealer(@RequestParam(value = "page",defaultValue = "0")int pageNum, Model model){
        log.info("/board/list/dealer");
        Page<DealerBoard> page = dealerBoardService.dealerBoardList(PageRequest.of(pageNum,10));
        model.addAttribute("page",page);
        model.addAttribute("cat","dealer");
        return "board/board_dealer";
    }
    @GetMapping("/board/write/{data}/{num}")
    public String board_write(@PathVariable("data")String data,@PathVariable(value = "num")String num,Model model){
        log.info("/board/write/{data}/{num}");
        model.addAttribute("data",data);
        model.addAttribute("num",num);
        return "board/board_write";
    }
    @PostMapping("/board/writeAction/member")
    public String board_write_action(@ModelAttribute MemberWriteBoardDto dto){
        log.info("/board/writeAction/member");
        memberBoardService.save(dto);
        return "redirect:/board/list/member";
    }
    @PostMapping("/board/writeAction/dealer")
    public String board_write_action(@ModelAttribute DealerWriteBoardDto dto){
        log.info("/board/writeAction/dealer");
        dealerBoardService.save(dto);
        return "redirect:/board/list/dealer";
    }
    @GetMapping("/board/memberBoard/{num}")
    public String member_board_one(@PathVariable("num") int num, Model model, HttpServletRequest request){
        log.info("/board/memberBoard/{num}");
        MemberBoard board = memberBoardService.findByNum((long)num);
        Object session = sessionManager.getSession(request);
        String id = board.getMember().getLoginId();
        if (session != null){
            String sessionClazzName = session.getClass().getSimpleName().toUpperCase();
            String idAndSession = id+sessionClazzName;
            model.addAttribute("idAndSession",idAndSession);
        }
        model.addAttribute("board",board);
        model.addAttribute("cat","member");
        model.addAttribute("writer",id);
        model.addAttribute("boardNum",num);
        return "board/board_one";
    }
    @GetMapping("/board/dealerBoard/{num}")
    public String dealer_board_one(@PathVariable("num") int num,Model model,HttpServletRequest request){
        log.info("/board/dealerBoard/{num}");
        DealerBoard board = dealerBoardService.findByNum((long)num);
        Object session = sessionManager.getSession(request);
        String id = board.getDealer().getLoginId();
        if(session != null){
            String sessionClazzName = session.getClass().getSimpleName().toUpperCase();
            String idAndSession = id+sessionClazzName;
            model.addAttribute("idAndSession",idAndSession);
        }
        model.addAttribute("board",board);
        model.addAttribute("cat","dealer");
        model.addAttribute("writer",id);
        model.addAttribute("boardNum",num);
        return "board/board_one";
    }
    @GetMapping("/board/checkPrivateContent/member")
    @ResponseBody
    public boolean check_password_member(@RequestParam Map<String,Object> params, Model model){
        log.info("/board/checkPrivateContent/member");
        String password = params.get("password").toString();
        Long board_id = Long.valueOf(params.get("board").toString());
        Long result = memberBoardService.checkPrivateContentPassword(board_id, password);
        if(result == 1){
            return true;
        }
        return false;
    }
    @GetMapping("/board/checkPrivateContent/dealer")
    @ResponseBody
    public boolean check_password_dealer(@RequestParam Map<String,Object> params, Model model){
        log.info("/board/checkPrivateContent/dealer");
        String password = params.get("password").toString();
        Long board_id = Long.valueOf(params.get("board").toString());
        Long result = dealerBoardService.checkPrivateContentPassword(board_id, password);
        if(result == 1){
            return true;
        }
        return false;
    }
    @GetMapping("/board/modify/member/{num}")
    public String board_member_modify(@PathVariable("num")int num,Model model){
        log.info("/board/modify/member/{num}");
        MemberBoard board = memberBoardService.findByNum(num);
        model.addAttribute("board",board);
        model.addAttribute("loginId",board.getMember().getLoginId());
        model.addAttribute("cat","member");
        return "board/board_modify";
    }
    @GetMapping("/board/modify/dealer/{num}")
    public String board_dealer_modify(@PathVariable("num")int num,Model model){
        log.info("/board/modify/dealer/{num}");
        DealerBoard board = dealerBoardService.findByNum(num);
        model.addAttribute("board",board);
        model.addAttribute("loginId",board.getDealer().getLoginId());
        model.addAttribute("cat","dealer");
        return "board/board_modify";
    }
    @PutMapping("/board/modifyAction/member/{num}")
    public String member_board_modify_action(@PathVariable("num")int num,@ModelAttribute MemberBoardModifyDto dto){
        log.info("/board/modifyAction/member/{num}");
        memberBoardService.modifyBoard(num,dto);
        return "redirect:/board/memberBoard/"+num;
    }
    @DeleteMapping("/board/deleteAction/member/{num}")
    public String member_board_delete_action(@PathVariable("num")int num){
        log.info("/board/deleteAction/member/{num}");
        memberBoardService.deleteBoard(num);
        return "redirect:/board/list/member";
    }
    @PutMapping("/board/modifyAction/dealer/{num}")
    public String dealer_board_modify_action(@PathVariable("num")int num,@ModelAttribute DealerBoardModifyDto dto){
        log.info("/board/modifyAction/dealer/{num}");
        dealerBoardService.modifyBoard(num,dto);
        return "redirect:/board/dealerBoard/"+num;
    }
    @DeleteMapping("/board/deleteAction/dealer/{num}")
    public String dealer_board_delete_action(@PathVariable("num")int num){
        log.info("/board/deleteAction/dealer/{num}");
        dealerBoardService.deleteBoard(num);
        return "redirect:/board/list/dealer";
    }
    @GetMapping("/board/search/member")
    public String member_board_search_bar(@RequestParam(value = "page",defaultValue = "0")int pageNum,@RequestParam("str") String str,Model model){
        log.info("/board/search/member");
        String title = "%"+str+"%";
        Page<MemberBoard> page = memberBoardService.getLikesTitleList(title,PageRequest.of(pageNum,10));
        model.addAttribute("cat","member");
        model.addAttribute("page",page);
        return "board/board_member";
    }
    @GetMapping("/board/search/dealer")
    public String dealer_board_search_bar(@RequestParam(value = "page",defaultValue = "0")int pageNum,@RequestParam("str") String str,Model model){
        log.info("/board/search/dealer");
        String title = "%"+str+"%";
        Page<DealerBoard> page = dealerBoardService.getLikesTitleList(title,PageRequest.of(pageNum,10));
        model.addAttribute("cat","dealer");
        model.addAttribute("page",page);
        return "board/board_dealer";
    }

}
