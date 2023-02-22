package com.jproject.my_cars.domain.board.reply.member_board_reply;

import com.jproject.my_cars.dto.MemberBoardReplyWriteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberBoardReplyService {

    private final MemberBoardReplyRepository memberBoardReplyRepository;

    @Transactional
    public void saveReply(MemberBoardReplyWriteDto dto){

    }
}
