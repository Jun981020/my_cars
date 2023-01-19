package com.jproject.my_cars.web;

import com.jproject.my_cars.domain.board.Board;
import com.jproject.my_cars.domain.board.BoardRepository;
import com.jproject.my_cars.domain.board.BoardService;
import com.jproject.my_cars.domain.member.Member;
import com.jproject.my_cars.domain.member.MemberService;
import com.jproject.my_cars.domain.reply.ReplyService;
import com.jproject.my_cars.dto.BoardWriteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final MemberService memberService;
    private final BoardRepository boardRepository;

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
        Optional<Board> oBoard = boardRepository.findById((long) num);
        Board board  = oBoard.get();
        model.addAttribute("board",board);
        model.addAttribute("boardId",board.getId());
        return "board_one";
    }

}
