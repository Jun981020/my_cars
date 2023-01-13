package com.jproject.my_cars.domain.member;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.Getter;

@Getter
@Entity
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login_id;
    private String password;
    private String name;
    private String email;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member createMember(String login_id,String password,String name, String email, String phone, Role role){
        Member members = new Member();
        members.login_id = login_id;
        members.password = password;
        members.name = name;
        members.email = email;
        members.phone = phone;
        members.role = role;
        return members;
    }
}
