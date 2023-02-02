package com.jproject.my_cars.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CarPostsDto {

    private String name;
    private Integer price;
    private String year;
    private String distance_driven;
    private boolean accident_history;
    private String area;
    private String fuel;
    private String company;
    private String manufacture;
    public CarPostsDto(){

    }
}
