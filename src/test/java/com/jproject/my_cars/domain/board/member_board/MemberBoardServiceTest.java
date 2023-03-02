package com.jproject.my_cars.domain.board.member_board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
        Page<MemberBoard> list = memberBoardRepository.findByLikeTitle("%후반%", PageRequest.of(0,1));
        System.out.println("list.size() = " + list.getContent());
    }

}