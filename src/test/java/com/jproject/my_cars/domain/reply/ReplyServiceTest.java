package com.jproject.my_cars.domain.reply;

import com.jproject.my_cars.domain.board.Board;
import com.jproject.my_cars.domain.board.BoardRepository;
import com.jproject.my_cars.domain.member.Member;
import com.jproject.my_cars.domain.member.MemberRepository;
import com.jproject.my_cars.domain.member.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class ReplyServiceTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private ReplyRepository replyRepository;

    @BeforeEach
    public void saveBoard(){
        boardRepository.save(Board.writeBoard("제목입니다1","내용입니다1.",memberRepository.save(Member.createMember("qwer","1234","spring","flwnsgud@naver.com","010-9145-6497", Role.SILVER)),1,"1234"));
        boardRepository.save(Board.writeBoard("제목입니다2","내용입니다2.",memberRepository.save(Member.createMember("asdf","5678","boot","dkskwm@naver.com","010-1423-5334", Role.GOLD)),1,"1234"));
        boardRepository.save(Board.writeBoard("제목입니다3","내용입니다3.",memberRepository.save(Member.createMember("zxcv","0000","test","fwads@naver.com","010-4232-1231", Role.VIP)),1,"1234"));
    }

    @Test
    public void addReply(){
        Member m = memberRepository.findByLoginId("qwer");
        Board b = boardRepository.findByTitle("제목입니다1");
        Reply reply = Reply.createReply(m, b, "이거 ㄹㅇ임 ㅋㅋ");
        reply.setBoard(b);
        Reply save = replyRepository.save(reply);
        assertThat(save.getContent()).isEqualTo("이거 ㄹㅇ임 ㅋㅋ");
    }
    @Test
    public void reply_list(){
        Member m1 = memberRepository.findByLoginId("asdf");
        Board b1 = boardRepository.findByTitle("제목입니다1");
        Reply reply = Reply.createReply(m1, b1, "댓글test1");
        reply.setBoard(b1);
        replyRepository.save(reply);

        Member m2 = memberRepository.findByLoginId("zxcv");
        Board b2 = boardRepository.findByTitle("제목입니다1");
        Reply reply2 = Reply.createReply(m2, b2, "댓글test2");
        reply2.setBoard(b2);
        replyRepository.save(reply2);

        Member m3 = memberRepository.findByLoginId("qwer");
        Board b3 = boardRepository.findByTitle("제목입니다1");
        Reply reply3 = Reply.createReply(m3, b3, "댓글test3");
        reply3.setBoard(b3);
        replyRepository.save(reply3);

        replyRepository.flush();
        boardRepository.flush();

        Board board = boardRepository.findByTitle("제목입니다1");
        List<Reply> replies = board.getReplies();
        assertThat(replies.size()).isEqualTo(3);
    }

}