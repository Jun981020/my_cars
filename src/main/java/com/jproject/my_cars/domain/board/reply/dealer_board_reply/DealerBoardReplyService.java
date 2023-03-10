package com.jproject.my_cars.domain.board.reply.dealer_board_reply;

import com.jproject.my_cars.domain.board.dealer_board.DealerBoard;
import com.jproject.my_cars.domain.board.dealer_board.DealerBoardRepository;
import com.jproject.my_cars.dto.DealerBoardReplWriteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DealerBoardReplyService {
    private final DealerBoardReplyRepository dealerBoardReplyRepository;
    private final DealerBoardRepository dealerBoardRepository;
    @Transactional
    //댓글 저장
    public void saveReply(DealerBoardReplWriteDto dto) {
        DealerBoardReply dealerBoardReply = DealerBoardReply.createDealerBoardReply(dto);
        DealerBoard dealerBoard = dealerBoardRepository.findById(Long.valueOf(dto.getBoardId())).get();
        dealerBoardReply.setDealerBoard(dealerBoard);
        dealerBoardReplyRepository.save(dealerBoardReply);
    }

    @Transactional
    //댓글 삭제
    public void removeReply(Long id) {
        DealerBoardReply dealerBoardReply = dealerBoardReplyRepository.findById(id).get();
        dealerBoardReply.removeBoardReply();
        dealerBoardReplyRepository.delete(dealerBoardReply);
    }

    @Transactional
    //댓글 수정
    public void modifyReply(int id, String content) {
        DealerBoardReply dealerBoardReply = dealerBoardReplyRepository.findById((long) id).get();
        dealerBoardReply.modifyReply(content);
    }
}
