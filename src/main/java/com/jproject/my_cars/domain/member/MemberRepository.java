package com.jproject.my_cars.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member,Long> {

    //이름으로 조회
    Member findByName(String name);
    //로그인 아이디로 조회
    Member findByLoginId(String login_id);
    //아이디와 비밀번호로 조회
    Member findByLoginIdAndPassword(@Param("login_id")String login_id,@Param("password")String password);
}
