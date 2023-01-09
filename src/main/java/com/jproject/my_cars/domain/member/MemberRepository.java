package com.jproject.my_cars.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {

    public Member findByName(String name);
}
