package com.jproject.my_cars.entity;

import com.jproject.my_cars.domain.member.Grade;
import com.jproject.my_cars.domain.member.Member;
import com.jproject.my_cars.domain.member.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MembersTest {

    @Autowired
    private MemberRepository membersRepository;

//    @Test
//    public void saveMember(){
//        Member m1 = Member.createMember("jun","flwnsgud@naver.com","qqqwdd","flwnsgud@naver.com","010-9145-6497", Grade.SILVER);
//        membersRepository.saveAndFlush(m1);
//        Member m2 = membersRepository.findByName("qqqwdd");
//        assertThat(m1.getName()).isEqualTo(m2.getName());
//    }

}