package com.jproject.my_cars.domain.board.dealer_board;

import com.jproject.my_cars.domain.dealer.Dealer;
import com.jproject.my_cars.domain.dealer.DealerRepository;
import com.jproject.my_cars.domain.dealer.DealerService;
import com.jproject.my_cars.dto.BoardWriteDto;
import com.jproject.my_cars.dto.DealerBoardModifyDto;
import com.jproject.my_cars.dto.DealerWriteBoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class DealerBoardService {
    private final DealerBoardRepository dealerBoardRepository;
    private final DealerRepository dealerRepository;

    public List<DealerBoard> dealerBoardList(){
        return dealerBoardRepository.findAll();
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
}
