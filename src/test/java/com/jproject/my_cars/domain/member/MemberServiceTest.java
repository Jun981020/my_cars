package com.jproject.my_cars.domain.member;

import com.jproject.my_cars.domain.board.member_board.MemberBoard;
import com.jproject.my_cars.domain.board.member_board.MemberBoardRepository;
import com.jproject.my_cars.domain.likes.Likes;
import com.jproject.my_cars.domain.likes.LikesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private LikesRepository likesRepository;
    @Autowired
    private MemberBoardRepository memberBoardRepository;
    @Test
    public void joinTest(){
        Member member = Member.createMember("qwer", "1234", "jun", "flwnsgud@naver.com", "010-9145-6497", Role.SILVER);
        Member save = memberRepository.save(member);
        assertThat(member.getName()).isEqualTo(save.getName());
    }
    //회원 로그인 아이디 중복체크
    @Test
    public void check_duplicate(){
        Member member = Member.createMember("qwer", "1234", "jun", "flwnsgud@naver.com", "010-9145-6497", Role.SILVER);
        memberRepository.save(member);
        memberRepository.flush();

        String check_id = "qwer1";
        boolean check = check(check_id);
        assertThat(check).isFalse();

    }
    //db 중복체크
    public boolean check(String id){
        if(memberRepository.findByLoginId(id) != null) {
            return false;
        } else return true;
    }
    @Test
    public void login_action(){
        Member qwer = memberRepository.findByLoginIdAndPassword("qwer","1234");
        assertThat(qwer.getPassword()).isEqualTo("1234");
    }
    @Test
    public void member_likes_list(){
        List<Likes> byMemberId = likesRepository.findByMemberId(1L);
        for (Likes likes : byMemberId) {
            System.out.println("likes = " + likes.getCar().getName());
        }
    }
    @Test
    public void countMemberBoardPrivateContentPassword(){
        Long result = memberBoardRepository.countByMemberBoardIdAndMemberBoardPrivateContentPassword(1L,"1111");
        System.out.println("memberBoard = " + result);
    }

}