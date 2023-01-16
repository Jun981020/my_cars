package com.jproject.my_cars.domain.board;

import com.jproject.my_cars.domain.member.Member;
import com.jproject.my_cars.domain.reply.Reply;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Board {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
    @OneToMany(mappedBy = "board")
    private List<Reply> replies = new ArrayList<>();

    public static Board writeBoard(String title,String content,Member member){
        Board board = new Board();
        board.title = title;
        board.content = content;
        board.member = member;
        return board;
    }

    @Override
    public String toString() {
        return "Board{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", member=" + member +
                ", replies=" + replies +
                '}';
    }
}
