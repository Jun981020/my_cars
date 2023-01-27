package com.jproject.my_cars.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member,Long> {

    public Member findByName(String name);

    public Member findByLoginId(String login_id);

//    @Query(value = "select m from Member m where m.login_id = :login_id and m.password : password")
    public Member findByLoginIdAndPassword(@Param("login_id")String login_id,@Param("password")String password);
}
