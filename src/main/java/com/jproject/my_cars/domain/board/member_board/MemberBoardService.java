package com.jproject.my_cars.domain.board.member_board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class MemberBoardService {
    private final MemberBoardRepository memberBoardRepository;

    public List<MemberBoard> memberBoardList(){
        return memberBoardRepository.findAll();
    }
}
