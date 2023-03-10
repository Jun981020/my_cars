package com.jproject.my_cars.dto;

import com.jproject.my_cars.domain.board.reply.ReplyRole;
import lombok.Data;

@Data
public class DealerBoardReplWriteDto {

    private String loginId;
    private String boardId;
    private String content;
    private ReplyRole replyRole;

}
