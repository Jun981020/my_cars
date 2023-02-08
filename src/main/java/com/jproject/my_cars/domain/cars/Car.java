package com.jproject.my_cars.domain.cars;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jproject.my_cars.domain.BaseEntity;
import com.jproject.my_cars.domain.cars.img.Img;
import com.jproject.my_cars.domain.cars.option.Options;
import com.jproject.my_cars.domain.dealer.Dealer;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Car extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //차량이름
    private String name;
    //차량가격
    private Integer price;
    //출시년도
    private String year;
    //주행거리
    private String distance_driven;
    //사고이력
    private boolean accident_history;
    //판매지역
    private String area;
    //연료
    private String fuel;
    @OneToMany
    private List<Options> options = new ArrayList<>();
    private String manufacture;
    @JsonIgnore
    @OneToMany(mappedBy = "car",cascade = CascadeType.ALL)
    private List<Img> images = new ArrayList<>();
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEALER_ID")
    private Dealer dealer;

    public void setDealer(Dealer dealer){
        if(this.dealer != null){
            this.dealer.getCars().remove(this);
        }
        this.dealer = dealer;
        dealer.getCars().add(this);
    }

    public static Car registrationCar(String name, Integer price, String year, String distance_driven, boolean accident_history, String area , String fuel, String manufacture){
        Car cars = new Car();
        cars.name = name;
        cars.price = price;
        cars.year = year;
        cars.distance_driven = distance_driven;
        cars.accident_history = accident_history;
        cars.area = area;
        cars.fuel = fuel;
        cars.manufacture = manufacture;
        return cars;
    }
    public void addOption(Options options){
        this.options.add(options);
    }
}
