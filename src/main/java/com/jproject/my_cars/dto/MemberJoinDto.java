package com.jproject.my_cars.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberJoinDto {
    private String login_id;
    private String password;
    private String name;
    private String email;
    private String phone;

}
