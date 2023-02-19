package com.jproject.my_cars.domain.board.dealer_board;

import com.jproject.my_cars.domain.board.Board;
import com.jproject.my_cars.domain.dealer.Dealer;
import com.jproject.my_cars.dto.DealerBoardWriteDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
public class DealerBoard extends Board{

    @Column(name = "DEALER_BOARD_ID")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEALER_ID")
    private Dealer dealer;
    public DealerBoard(){

    }
    public static DealerBoard writeDealerBoard(DealerBoardWriteDto dto){
        DealerBoard dealerBoard = new DealerBoard();
        dealerBoard.setTitle(dto.getTitle());
        dealerBoard.setContent(dto.getContent());
        dealerBoard.setPrivate_content(dto.getPrivate_content());
        dealerBoard.setPrivate_content_password(dto.getPrivate_content_password());
        return dealerBoard;
    }
}
