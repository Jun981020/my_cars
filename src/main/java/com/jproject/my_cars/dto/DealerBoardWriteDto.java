package com.jproject.my_cars.dto;

import lombok.Data;

@Data
public class DealerBoardWriteDto {

    private String title;
    private String content;
    private int private_content;
    private String private_content_password;
    private int dealer_num;
}
