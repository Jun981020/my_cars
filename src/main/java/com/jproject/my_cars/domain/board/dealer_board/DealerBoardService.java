package com.jproject.my_cars.domain.board.dealer_board;

import com.jproject.my_cars.domain.dealer.DealerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class DealerBoardService {
    private final DealerBoardRepository dealerBoardRepository;
    private final DealerService dealerService;

    public List<DealerBoard> dealerBoardList(){
        return dealerBoardRepository.findAll();
    }

}
