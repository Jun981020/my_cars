package com.jproject.my_cars.dto;

import lombok.Data;
@Data
public class DealerBoardModifyDto {
    private String title;
    private String content;
    private int secret_content;
    private String secret_password;
}
