package com.jproject.my_cars.domain.cars.car_options;

import com.jproject.my_cars.domain.cars.Car;
import com.jproject.my_cars.domain.cars.option.Options;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "CAR_OPTIONS")
public class CarOptions {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CAR_ID")
    private Car car;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OPTIONS_ID")
    private Options options;

    public void setCarAndOptions(Car car,Options options){
        this.car = car;
        this.options = options;
        car.getOptions().add(this);
    }
}
