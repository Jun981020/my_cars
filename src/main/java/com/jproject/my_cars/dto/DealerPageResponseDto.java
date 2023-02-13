package com.jproject.my_cars.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class DealerPageResponseDto {
    private String name;
    private Integer price;
    private LocalDateTime createAt;
    private Integer point;
}
