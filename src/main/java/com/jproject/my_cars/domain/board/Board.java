package com.jproject.my_cars.domain.board;

import com.jproject.my_cars.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Board extends BaseEntity {
    @Column(name = "title",length = 100)
    @NotNull
    //게시글 제목
    private String title;
    @Column(name = "content",length = 500)
    @NotNull
    //게시글 내용
    private String content;
    @NotNull
    //비밀글 여/부
    private int secret_content;
    //비밀글 비밀번호
    private String secret_password;
}
