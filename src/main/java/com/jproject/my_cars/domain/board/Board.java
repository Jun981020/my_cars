package com.jproject.my_cars.domain.board;

import com.jproject.my_cars.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Board extends BaseEntity {

    private String title;
    private String content;
    private int private_content;
    private String private_content_password;
}
