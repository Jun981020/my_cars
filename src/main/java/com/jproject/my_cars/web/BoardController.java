package com.jproject.my_cars.web;

import com.jproject.my_cars.domain.board.Board;
import com.jproject.my_cars.domain.board.BoardRepository;
import com.jproject.my_cars.domain.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

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

}
