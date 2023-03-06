package com.jproject.my_cars.domain.board.dealer_board;

import com.jproject.my_cars.domain.dealer.Dealer;
import com.jproject.my_cars.domain.dealer.DealerRepository;
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

    //모든 딜러 게시판 가져오기
    public Page<DealerBoard> dealerBoardList(PageRequest pageRequest){
        return dealerBoardRepository.findAll(pageRequest);
    }

    @Transactional
    //게시글 저장
    public void save(DealerWriteBoardDto dto) {
        DealerBoard dealerBoard = DealerBoard.writeDealerBoard(dto);
        Dealer dealer = dealerRepository.findById((long) dto.getNum()).get();
        dealerBoard.setDealer(dealer);
        dealerBoardRepository.save(dealerBoard);
    }

    //번호로 딜러게시글 가져오기
    public DealerBoard findByNum(long num) {
        return dealerBoardRepository.findById(num).get();
    }

    @Transactional
    //게시글 수정
    public void modifyBoard(long num, DealerBoardModifyDto dto) {
        DealerBoard dealerBoard = dealerBoardRepository.findById(num).get();
        dealerBoard.modifyDealerBoard(dto);
    }

    @Transactional
    //게시글 삭제
    public void deleteBoard(long num) {
        dealerBoardRepository.deleteById(num);
    }
    //딜러페이지 게시한 게시글 가져오기
    public List<DealerBoard> getDealerBoardList(){
        return dealerBoardRepository.findAll();
    }
    //비밀번호가 일치하는지 체크
    public Long checkPrivateContentPassword(Long boardId, String password) {
        return dealerBoardRepository.countByDealerBoardIdAndDealerBoardSecretPassword(boardId,password);
    }

    //리스트를 페이지로 가져옴
    public Page<DealerBoard> getLikesTitleList(String title,PageRequest pageRequest) {
        return dealerBoardRepository.findByLikeTitle(title,pageRequest);
    }
}
