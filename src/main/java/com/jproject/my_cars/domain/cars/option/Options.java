package com.jproject.my_cars.domain.cars.option;

import com.jproject.my_cars.domain.cars.Car;
import com.jproject.my_cars.dto.OptionsDto;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Options {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OPTIONS_ID")
    private Long id;
    private String name;
    private String description;

    public static Options putOption(String name, String description){
        Options options = new Options();
        options.name = name;
        options.description = description;
        return options;
    }

    @Override
    public String toString() {
        return "Options{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
    //    SUNROOF,
//    HEADLAMP,
//    PARKING_SENSOR,
//    REAR_CAMERA,
//    NAVIGATION,
//    SMART_KEY,
//    HEATED_SEAT
}
