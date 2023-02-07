package com.jproject.my_cars.dto;

import com.jproject.my_cars.domain.cars.option.Options;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
