package com.jproject.my_cars.domain.board.reply.member_board_reply;

import com.jproject.my_cars.domain.board.member_board.MemberBoard;
import com.jproject.my_cars.domain.board.member_board.MemberBoardRepository;
import com.jproject.my_cars.dto.MemberBoardReplyWriteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberBoardReplyService {

    private final MemberBoardReplyRepository memberBoardReplyRepository;
    private final MemberBoardRepository memberBoardRepository;

    @Transactional
    public void saveReply(MemberBoardReplyWriteDto dto){
        MemberBoardReply memberBoardReply = MemberBoardReply.createMemberBoardReply(dto);
        MemberBoard memberBoard = memberBoardRepository.findById(Long.valueOf(dto.getBoardId())).get();
        memberBoardReply.setMemberBoard(memberBoard);
        memberBoardReplyRepository.save(memberBoardReply);
    }
}
