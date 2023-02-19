package com.jproject.my_cars.web;

import com.jproject.my_cars.domain.board.dealer_board.DealerBoard;
import com.jproject.my_cars.domain.board.dealer_board.DealerBoardService;
import com.jproject.my_cars.domain.board.member_board.MemberBoard;
import com.jproject.my_cars.domain.board.member_board.MemberBoardService;
import com.jproject.my_cars.domain.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final DealerBoardService dealerBoardService;
    private final MemberBoardService memberBoardService;
    private final MemberService memberService;
    @GetMapping("/board/list")
    public String board_list(Model model){
        List<MemberBoard> memberBoards = memberBoardService.memberBoardList();
        model.addAttribute("list",memberBoards);
        return "board/board";
    }
    @GetMapping("/board/list/{code}")
    public String board_list_code(@PathVariable("code")String code,Model model){
        log.info("호출주우우우웅₩");
        if(code.equals("dealer")){
            List<DealerBoard> dealerBoards = dealerBoardService.dealerBoardList();
            model.addAttribute("list",dealerBoards);
            return "board/board :: #codeList";
        }
        return null;
    }
    @GetMapping("/board/write/{data}")
    public String board_write(){
        return "board/board_write";
    }
//    @PostMapping("/board/writeAction")
//    public String board_write_action(@ModelAttribute BoardWriteDto dto){
//        boardService.saveBoard(dto);
//        return "redirect:/board/list";
//    }
//    @GetMapping("/board/boardOne/{num}")
//    public String board_one(@PathVariable("num") int num,Model model){
//        //이곳에서는 두개의 액션으로 받을수가 있음
//        Board board = boardService.findBoardByBoardId((long) num).get();
//        model.addAttribute("board",board);
//        model.addAttribute("boardId",board.getId());
//        return "board/board_one";
//    }
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
