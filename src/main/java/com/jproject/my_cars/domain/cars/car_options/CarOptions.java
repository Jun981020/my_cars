package com.jproject.my_cars.domain.cars.car_options;

import com.jproject.my_cars.domain.cars.Car;
import com.jproject.my_cars.domain.cars.option.Options;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Entity
@Table(name = "CAR_OPTIONS")
public class CarOptions {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CAR_ID")
    @NotNull
    //차 정보
    private Car car;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OPTIONS_ID")
    //옵션정보
    private Options options;

    //차,옵션 연관관계 메서드
    public void setCarAndOptions(Car car,Options options){
        this.car = car;
        this.options = options;
        car.getOptions().add(this);
    }
}
