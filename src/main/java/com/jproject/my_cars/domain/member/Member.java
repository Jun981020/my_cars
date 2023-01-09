package com.jproject.my_cars.domain.member;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.Getter;

@Getter
@Entity
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Max(value = 20)
    private String name;
    @Max(value = 35)
    private String email;
    @Max(value = 13)
    private String phone;
    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member createMember(String name, String email, String phone, Role role){
        Member members = new Member();
        members.name = name;
        members.email = email;
        members.phone = phone;
        members.role = role;
        return members;
    }
}
