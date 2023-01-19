package com.jproject.my_cars.domain.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public List<Board> boardList(){
        return boardRepository.findAll();
    }
    @Transactional
    public void saveBoard(Board board){
        boardRepository.save(board);
    }
    public Board getBoardById(long id){
        return boardRepository.findById(id).get();
    }

}
