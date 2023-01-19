package com.jproject.my_cars.domain.board;

import com.jproject.my_cars.domain.member.Member;
import com.jproject.my_cars.domain.member.MemberRepository;
import com.jproject.my_cars.domain.member.Role;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardServiceTest {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private MemberRepository memberRepository;
    @BeforeEach
    public void saveBoard(){
        boardRepository.save(Board.writeBoard("제목입니다1","내용입니다2.",memberRepository.save(Member.createMember("qwer","1234","spring","flwnsgud@naver.com","010-9145-6497", Role.SILVER)),1,"1234"));
        boardRepository.save(Board.writeBoard("제목입니다1","내용입니다2.",memberRepository.save(Member.createMember("asdf","5678","boot","dkskwm@naver.com","010-1423-5334", Role.GOLD)),1,"1234"));
        boardRepository.save(Board.writeBoard("제목입니다1","내용입니다2.",memberRepository.save(Member.createMember("zxcv","0000","test","fwads@naver.com","010-4232-1231", Role.VIP)),1,"1234"));
    }
    //board data 저장
    @Test
    public void save_board_test(){
        Member m = memberRepository.findByLoginId("qwer");
        Board board = Board.writeBoard("제목입니다.", "내용입니다.", m,1,"1234");
        boardRepository.save(board);
        boardRepository.flush();

        Board findTitle = boardRepository.findByTitle(board.getTitle());
        assertThat(findTitle.getContent()).isEqualTo(board.getContent());
    }

    //게시글 리스트 보기
    @Test
    public void board_list(){
        List<Board> all = boardRepository.findAll();
        assertThat(all.size()).isEqualTo(6);
        for (Board board : all) {
            System.out.println("board = " + board);
        }
    }

}