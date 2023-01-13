package com.jproject.my_cars.domain.member;

import com.jproject.my_cars.dto.MemberJoinDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    //회원가입 폼에서 회원가입하기
    public Optional<Long> joinMember(Member member){
        Member m = memberRepository.save(member);
        return m.getId().describeConstable();
    }


}
