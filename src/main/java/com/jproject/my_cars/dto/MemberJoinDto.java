package com.jproject.my_cars.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
public class MemberJoinDto {
    private String login_id;
    private String password;
    private String name;
    private String email;
    private String phone;

    public MemberJoinDto() {

    }

    @Override
    public String toString() {
        return "MemberJoinDto{" +
                "login_id='" + login_id + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
