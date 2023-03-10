package com.jproject.my_cars.domain.dealer.employee;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Embeddable
@Getter
@NoArgsConstructor
public class Card {

    @NotNull
    @Column(name = "employee_number",length = 15)
    //사원번호
    private String employee_number;
    @NotNull
    @Column(name = "company",length = 30)
    //회사명
    private String company;
    @NotNull
    //사원증 발급날짜
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
