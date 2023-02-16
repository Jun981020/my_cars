package com.jproject.my_cars.domain.board;

import com.jproject.my_cars.domain.BaseEntity;
import com.jproject.my_cars.domain.member.Member;
import com.jproject.my_cars.domain.reply.Reply;
import com.jproject.my_cars.dto.BoardWriteDto;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Board extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
    @OneToMany(mappedBy = "board",cascade = CascadeType.ALL)
    private List<Reply> replies = new ArrayList<>();
    private int private_content;
    private String private_content_password;

    public static Board writeBoard(String title,String content,Member member,int private_content,String private_content_password){
        Board board = new Board();
        board.title = title;
        board.member = member;
        board.content = content;
        board.private_content =private_content;
        board.private_content_password = private_content_password;
        return board;
    }
    public Board modifyBoard(BoardWriteDto dto){
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.private_content = dto.getPrivate_content();
        this.private_content_password = dto.getPrivate_content_password();
        return this;
    }

    @Override
    public String toString() {
        return "Board{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", member=" + member +
                ", replies=" + replies +
                ", private_content=" + private_content +
                '}';
    }
}
