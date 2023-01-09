package com.jproject.my_cars.entity;

import com.jproject.my_cars.domain.member.Member;
import com.jproject.my_cars.domain.member.MemberRepository;
import com.jproject.my_cars.domain.member.Role;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MembersTest {

    @Autowired
    private MemberRepository membersRepository;

    @Test
    public void saveMember(){
        Member m1 = Member.createMember("jun","flwnsgud@naver.com","010-9145-6497", Role.SILVER);
        membersRepository.save(m1);
        membersRepository.flush();
        Member m2 = membersRepository.findByName("jun");
        Assertions.assertThat(m1.getName()).isEqualTo(m2.getName());
    }

}