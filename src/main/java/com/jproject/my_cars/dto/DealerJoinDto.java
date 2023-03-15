package com.jproject.my_cars.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class DealerJoinDto {

    private String loginId;
    private String password;
    private String name;
    private String phone;
    private String employee_number;
    private String company;
    private String acquisition_date;

}
