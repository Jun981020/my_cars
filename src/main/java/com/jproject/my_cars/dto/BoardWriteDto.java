package com.jproject.my_cars.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BoardWriteDto {

    private String title;
    private String content;
    private int private_content;
    private String private_content_password;
    private String loginId;
    @Override
    public String toString() {
        return "BoardWriteDto{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", private_content=" + private_content +
                ", private_content_password='" + private_content_password + '\'' +
                ", login_id='" + loginId + '\'' +
                '}';
    }
}
