package com.jproject.my_cars.domain.board.member_board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberBoardServiceTest {
    @Autowired
    private MemberBoardRepository memberBoardRepository;

    @Test
    public void likeListTest(){
        List<MemberBoard> list = memberBoardRepository.findByLikeTitle("%후반%");
        System.out.println("list.size() = " + list.size());
    }

}