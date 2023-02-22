package com.jproject.my_cars.web;

import com.jproject.my_cars.domain.board.dealer_board.DealerBoard;
import com.jproject.my_cars.domain.board.dealer_board.DealerBoardService;
import com.jproject.my_cars.domain.board.member_board.MemberBoard;
import com.jproject.my_cars.domain.board.member_board.MemberBoardService;
import com.jproject.my_cars.domain.member.MemberService;
import com.jproject.my_cars.dto.BoardWriteDto;
import com.jproject.my_cars.dto.DealerWriteBoardDto;
import com.jproject.my_cars.dto.MemberWriteBoardDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final DealerBoardService dealerBoardService;
    private final MemberBoardService memberBoardService;
    private final MemberService memberService;
    @GetMapping("/board/list/member")
    public String board_list(Model model){
        List<MemberBoard> memberBoards = memberBoardService.memberBoardList();
        model.addAttribute("list",memberBoards);
        return "board/board_member";
    }
    @GetMapping("/board/list/dealer")
    public String board_list_dealer(Model model){
        List<DealerBoard> dealerBoards = dealerBoardService.dealerBoardList();
        model.addAttribute("list",dealerBoards);
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
        System.out.println("dto = " + dto);
        dealerBoardService.save(dto);
        return "redirect:/board/list/dealer";
    }
    @GetMapping("/board/memberBoard/{num}")
    public String member_board_one(@PathVariable("num") int num,Model model){
        MemberBoard board = memberBoardService.findByNum((long)num);
        model.addAttribute("board",board);
        model.addAttribute("cat","member");
        return "board/board_one";
    }
    @GetMapping("/board/dealerBoard/{num}")
    public String dealer_board_one(@PathVariable("num") int num,Model model){
        DealerBoard board = dealerBoardService.findByNum((long)num);
        model.addAttribute("board",board);
        model.addAttribute("cat","dealer");
        return "board/board_one";
    }
//    @GetMapping("/board/checkPrivateContent")
//    @ResponseBody
//    public boolean checkPassword(@RequestParam Map<String,Object> params, Model model){
//        String password = params.get("password").toString();
//        Long board_id = Long.valueOf(params.get("board").toString());
//        int result = boardService.checkPassword(params.get("password").toString(), board_id);
//        if(result == 0){
//            return true;
//        }else{
//            return false;
//        }
//    }
//    @GetMapping("/board/modify/{num}")
//    public String board_modify(@PathVariable("num")int num,Model model){
//        Board board = boardService.findBoardByBoardId((long)num).get();
//        model.addAttribute("board",board);
//        return "board/board_modify";
//    }
//    @PutMapping("/board/modifyAction/{num}")
//    public String board_modify_action(@PathVariable("num")int num,@ModelAttribute BoardWriteDto dto){
//        log.info("들어옵니다");
//        boardService.modifyBoard((long)num,dto);
//        return "redirect:/board/boardOne/"+num;
//    }
//    @DeleteMapping("/board/deleteAction/{num}")
//    public String board_delete_action(@PathVariable("num")int num){
//        boardService.deleteBoard((long)num);
//        return "redirect:/board/list";
//    }
//    @GetMapping("/board/search")
//    public String board_search_bar(@RequestParam("str") String str,Model model){
//        String s = "%"+str+"%";
//        List<Board> boards = boardService.searchStringWith(s);
//        model.addAttribute("list",boards);
//        return "board/board";
//    }

}
