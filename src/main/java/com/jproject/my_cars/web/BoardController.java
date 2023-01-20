package com.jproject.my_cars.web;

import com.jproject.my_cars.domain.board.Board;
import com.jproject.my_cars.domain.board.BoardRepository;
import com.jproject.my_cars.domain.board.BoardService;
import com.jproject.my_cars.domain.member.Member;
import com.jproject.my_cars.domain.member.MemberService;
import com.jproject.my_cars.dto.BoardWriteDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;
    private final MemberService memberService;
    @GetMapping("/board/list")
    public String board_list(Model model){
        List<Board> boards = boardService.boardList();
        model.addAttribute("list",boards);
        return "board";
    }
    @GetMapping("/board/write")
    public String board_write(){
        return "board_write";
    }
    @PostMapping("/board/writeAction")
    public String board_write_action(@ModelAttribute BoardWriteDto dto){
        Member member = memberService.getMember(dto.getLoginId());
        Board board = Board.writeBoard(dto.getTitle(), dto.getContent(), member, 1, "1234");
        boardService.saveBoard(board);
        return "redirect:/board/list";
    }
    @GetMapping("/board/boardOne/{num}")
    public String board_one(@PathVariable("num") int num,Model model){
        Board board = boardService.findBoardByBoardId((long) num).get();
        model.addAttribute("board",board);
        model.addAttribute("boardId",board.getId());
        return "board_one";
    }
    @PostMapping("/board/checkPrivateContent")
    @ResponseBody
    public boolean checkPassword(@RequestParam Map<String,Object> params, Model model){
        String password = params.get("password").toString();
        Long board_id = Long.valueOf(params.get("board").toString());
        int result = boardService.checkPassword(params.get("password").toString(), board_id);
        if(result == 0){
            return true;
        }else{
            return false;
        }
    }
    @GetMapping("/board/modify/{num}")
    public String board_modify(@PathVariable("num")int num,Model model){
        Board board = boardService.findBoardByBoardId((long)num).get();
        model.addAttribute("board",board);
        return "board_modify";
    }
    @PutMapping("/board/modifyAction/{num}")
    public String board_modify_action(@PathVariable("num")int num,@ModelAttribute BoardWriteDto dto){
        log.info("들어옵니다");
        boardService.modifyBoard((long)num,dto);
        return "redirect:/board/boardOne/"+num;
    }
    @DeleteMapping("/board/deleteAction/{num}")
    public String board_delete_action(@PathVariable("num")int num){
        boardService.deleteBoard((long)num);
        return "redirect:/board/list";
    }
    @GetMapping("/board/search")
    public String board_search_bar(@RequestParam("str") String str,Model model){
        String s = "%"+str+"%";
        List<Board> boards = boardService.searchStringWith(s);
        model.addAttribute("list",boards);
        return "board";
    }

}
