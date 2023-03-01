package com.jproject.my_cars.domain.board.dealer_board;

import com.jproject.my_cars.domain.board.member_board.MemberBoard;
import com.jproject.my_cars.domain.dealer.Dealer;
import com.jproject.my_cars.domain.dealer.DealerRepository;
import com.jproject.my_cars.domain.dealer.DealerService;
import com.jproject.my_cars.dto.BoardWriteDto;
import com.jproject.my_cars.dto.DealerBoardModifyDto;
import com.jproject.my_cars.dto.DealerWriteBoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class DealerBoardService {
    private final DealerBoardRepository dealerBoardRepository;
    private final DealerRepository dealerRepository;

    public Page<DealerBoard> dealerBoardList(PageRequest pageRequest){
        return dealerBoardRepository.findAll(pageRequest);
    }

    @Transactional
    public void save(DealerWriteBoardDto dto) {
        DealerBoard dealerBoard = DealerBoard.writeDealerBoard(dto);
        Dealer dealer = dealerRepository.findById((long) dto.getNum()).get();
        dealerBoard.setDealer(dealer);
        dealerBoardRepository.save(dealerBoard);
    }

    public DealerBoard findByNum(long num) {
        return dealerBoardRepository.findById(num).get();
    }

    @Transactional
    public void modifyBoard(long num, DealerBoardModifyDto dto) {
        DealerBoard dealerBoard = dealerBoardRepository.findById(num).get();
        dealerBoard.modifyDealerBoard(dto);
    }

    @Transactional
    public void deleteBoard(long num) {
        dealerBoardRepository.deleteById(num);
    }
    public List<DealerBoard> getDealerBoardList(){
        return dealerBoardRepository.findAll();
    }

    public Long checkPrivateContentPassword(Long boardId, String password) {
        return dealerBoardRepository.countByDealerBoardIdAndDealerBoardSecretPassword(boardId,password);
    }

    public List<DealerBoard> getLikesTitleList(String title) {
        return dealerBoardRepository.findByLikeTitle(title);
    }
}
