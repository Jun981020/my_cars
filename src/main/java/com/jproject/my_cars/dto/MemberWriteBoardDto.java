package com.jproject.my_cars.dto;

import lombok.Data;

@Data
public class MemberWriteBoardDto {

    private String title;
    private String content;
    private int secret_content;
    private String secret_password;
    private int num;



}
