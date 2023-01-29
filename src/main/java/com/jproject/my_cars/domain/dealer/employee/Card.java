package com.jproject.my_cars.domain.dealer.employee;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Embeddable
@Getter
@NoArgsConstructor
public class Card {

    private String employee_number;
    private String company;
    private LocalDate acquisition_date;

    public Card(String employee_number,String company, String date){
        this.employee_number = employee_number;
        this.company = company;
        this.acquisition_date = LocalDate.parse(date);
    }

    @Override
    public String toString() {
        return "Card{" +
                "employee_number='" + employee_number + '\'' +
                ", company='" + company + '\'' +
                ", acquisition_date=" + acquisition_date +
                '}';
    }
}