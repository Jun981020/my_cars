package com.jproject.my_cars.dto;

import lombok.Data;

@Data
public class MemberBoardReplyWriteDto {

    private String loginId;
    private String boardId;
    private String content;

}
