package com.jproject.my_cars.dto;

import com.jproject.my_cars.domain.dealer.employee.Card;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DealerJoinDto {

    private String loginId;
    private String password;
    private String name;
    private Card card;

}
