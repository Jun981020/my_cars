package com.jproject.my_cars.domain.board.member_board;

import com.jproject.my_cars.domain.board.Board;
import com.jproject.my_cars.domain.member.Member;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class MemberBoard extends Board {
    @Column(name = "MEMBER_BOARD_ID")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
}
