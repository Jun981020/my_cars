package com.jproject.my_cars.domain.dealer.employee;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class EmployeeNumber {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;

}
