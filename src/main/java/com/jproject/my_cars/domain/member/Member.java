package com.jproject.my_cars.domain.member;

import com.jproject.my_cars.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Member extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String loginId;
    private String password;
    private String name;
    private String email;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member createMember(String loginId,String password,String name, String email, String phone, Role role){
        Member members = new Member();
        members.loginId = loginId;
        members.password = password;
        members.name = name;
        members.email = email;
        members.phone = phone;
        members.role = role;
        return members;
    }
}
