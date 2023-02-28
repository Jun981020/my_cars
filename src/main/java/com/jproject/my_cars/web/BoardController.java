package com.jproject.my_cars.web;

import com.jproject.my_cars.domain.board.dealer_board.DealerBoard;
import com.jproject.my_cars.domain.board.dealer_board.DealerBoardService;
import com.jproject.my_cars.domain.board.member_board.MemberBoard;
import com.jproject.my_cars.domain.board.member_board.MemberBoardService;
import com.jproject.my_cars.domain.board.reply.member_board_reply.MemberBoardReplyService;
import com.jproject.my_cars.domain.dealer.Dealer;
import com.jproject.my_cars.domain.member.MemberService;
import com.jproject.my_cars.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final DealerBoardService dealerBoardService;
    private final MemberBoardService memberBoardService;
    private final MemberService memberService;
    private final MemberBoardReplyService memberBoardReplyService;
    @GetMapping("/board/list/member")
    public String board_list(Model model){
        List<MemberBoard> memberBoards = memberBoardService.memberBoardList();
        model.addAttribute("list",memberBoards);
        model.addAttribute("cat","member");
        return "board/board_member";
    }
    @GetMapping("/board/list/dealer")
    public String board_list_dealer(Model model){
        List<DealerBoard> dealerBoards = dealerBoardService.dealerBoardList();
        model.addAttribute("list",dealerBoards);
        model.addAttribute("cat","dealer");
        return "board/board_dealer";
    }
    @GetMapping("/board/write/{data}/{num}")
    public String board_write(@PathVariable("data")String data,@PathVariable(value = "num")String num,Model model){
        if(data == null){
            throw new NoSuchBoardWriteData("회원또는 딜러로 로그인하지 않았습니다.");
        }
        model.addAttribute("data",data);
        model.addAttribute("num",num);
        return "board/board_write";
    }
//    @ExceptionHandler(NoSuchBoardWriteData.class)
//    public String noData(RedirectAttributes rds){
//        String message = "회원또는 딜러로 로그인하지 않았습니다";
//        rds.addFlashAttribute("fail.",message);
//        return "redirect:/main";
//    }
    @PostMapping("/board/writeAction/member")
    public String board_write_action(@ModelAttribute MemberWriteBoardDto dto){
        System.out.println("dto = " + dto);
        memberBoardService.save(dto);
        return "redirect:/board/list/member";
    }
    @PostMapping("/board/writeAction/dealer")
    public String board_write_action(@ModelAttribute DealerWriteBoardDto dto){
        dealerBoardService.save(dto);
        return "redirect:/board/list/dealer";
    }
    @GetMapping("/board/memberBoard/{num}")
    public String member_board_one(@PathVariable("num") int num,Model model){
        MemberBoard board = memberBoardService.findByNum((long)num);
        model.addAttribute("board",board);
        model.addAttribute("cat","member");
        model.addAttribute("writer",board.getMember().getLoginId());
        return "board/board_one";
    }
    @GetMapping("/board/dealerBoard/{num}")
    public String dealer_board_one(@PathVariable("num") int num,Model model){
        DealerBoard board = dealerBoardService.findByNum((long)num);
        model.addAttribute("board",board);
        model.addAttribute("cat","dealer");
        model.addAttribute("writer",board.getDealer().getLoginId());
        return "board/board_one";
    }
    @GetMapping("/board/checkPrivateContent/member")
    @ResponseBody
    public boolean check_password_member(@RequestParam Map<String,Object> params, Model model){
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
        MemberBoard board = memberBoardService.findByNum(num);
        model.addAttribute("board",board);
        model.addAttribute("loginId",board.getMember().getLoginId());
        model.addAttribute("cat","member");
        return "board/board_modify";
    }
    @GetMapping("/board/modify/dealer/{num}")
    public String board_dealer_modify(@PathVariable("num")int num,Model model){
        DealerBoard board = dealerBoardService.findByNum(num);
        model.addAttribute("board",board);
        model.addAttribute("loginId",board.getDealer().getLoginId());
        model.addAttribute("cat","dealer");
        return "board/board_modify";
    }
    @PutMapping("/board/modifyAction/member/{num}")
    public String member_board_modify_action(@PathVariable("num")int num,@ModelAttribute MemberBoardModifyDto dto){
        log.info("들어옵니다");
        memberBoardService.modifyBoard(num,dto);
        return "redirect:/board/memberBoard/"+num;
    }
    @DeleteMapping("/board/deleteAction/member/{num}")
    public String member_board_delete_action(@PathVariable("num")int num){
        memberBoardService.deleteBoard(num);
        return "redirect:/board/list/member";
    }
    @PutMapping("/board/modifyAction/dealer/{num}")
    public String dealer_board_modify_action(@PathVariable("num")int num,@ModelAttribute DealerBoardModifyDto dto){
        dealerBoardService.modifyBoard(num,dto);
        return "redirect:/board/dealerBoard/"+num;
    }
    @DeleteMapping("/board/deleteAction/dealer/{num}")
    public String dealer_board_delete_action(@PathVariable("num")int num){
        dealerBoardService.deleteBoard(num);
        return "redirect:/board/list/dealer";
    }
    @GetMapping("/board/search/member")
    public String member_board_search_bar(@RequestParam("str") String str,Model model){
        String title = "%"+str+"%";
        List<MemberBoard> likesTitleList = memberBoardService.getLikesTitleList(title);
        model.addAttribute("cat","member");
        model.addAttribute("list",likesTitleList);
        return "board/board_member";
    }
    @GetMapping("/board/search/dealer")
    public String dealer_board_search_bar(@RequestParam("str") String str,Model model){
        String title = "%"+str+"%";
        List<DealerBoard> likesTitleList = dealerBoardService.getLikesTitleList(title);
        model.addAttribute("cat","dealer");
        model.addAttribute("list",likesTitleList);
        return "board/board_dealer";
    }

}
