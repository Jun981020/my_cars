package com.jproject.my_cars.domain.board;

import com.jproject.my_cars.domain.member.Member;
import com.jproject.my_cars.domain.member.MemberRepository;
import com.jproject.my_cars.dto.BoardWriteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    public List<Board> boardList(){
        return boardRepository.findAll();
    }
    @Transactional
    public void saveBoard(BoardWriteDto dto){
        Member member = memberRepository.findById((long) dto.getMemberId()).get();
        Board board = Board.writeBoard(dto.getTitle(), dto.getContent(),member,dto.getPrivate_content(), dto.getPrivate_content_password());
        boardRepository.save(board);
    }
    public Board getBoardById(long id){
        return boardRepository.findById(id).get();
    }

    public int checkPassword(String ps,Long id){
        Board one = boardRepository.findById(id).get();
        if(one.getPrivate_content_password().equals(ps)){
            return 0;
        }else{
            return 1;
        }
    }
    public Optional<Board> findBoardByBoardId(Long id){
        return boardRepository.findById(id);
    }
    @Transactional
    public void modifyBoard(Long num,BoardWriteDto dto){
        Board board = boardRepository.findById(num).get();
        board.modifyBoard(dto);
    }
    @Transactional
    public void deleteBoard(Long num){
        boardRepository.delete(boardRepository.findById(num).get());
    }
    public List<Board> searchStringWith(String str){
        return boardRepository.findByTitleLike(str);
    }

}
