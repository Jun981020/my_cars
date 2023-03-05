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
    public void saveReply(DealerBoardReplWriteDto dto) {
        DealerBoardReply dealerBoardReply = DealerBoardReply.createDealerBoardReply(dto);
        DealerBoard dealerBoard = dealerBoardRepository.findById(Long.valueOf(dto.getBoardId())).get();
        dealerBoardReply.setDealerBoard(dealerBoard);
        dealerBoardReplyRepository.save(dealerBoardReply);
    }

    @Transactional
    public void removeReply(Long id) {
        DealerBoardReply dealerBoardReply = dealerBoardReplyRepository.findById(id).get();
        dealerBoardReply.removeBoardReply();
        dealerBoardReplyRepository.delete(dealerBoardReply);
    }

    @Transactional
    public void modifyReply(int id, String content) {
        DealerBoardReply dealerBoardReply = dealerBoardReplyRepository.findById((long) id).get();
        dealerBoardReply.modifyReply(content);
    }
}
