package com.jproject.my_cars.domain.cars;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jproject.my_cars.domain.BaseEntity;
import com.jproject.my_cars.domain.cars.car_options.CarOptions;
import com.jproject.my_cars.domain.cars.img.Img;
import com.jproject.my_cars.domain.cars.option.Options;
import com.jproject.my_cars.domain.dealer.Dealer;
import com.jproject.my_cars.dto.CarPostsDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Car extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CAR_ID")
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
//    @ManyToMany
//    @JoinTable(
//                name = "car_options",
//                joinColumns = {
//                        @JoinColumn(name = "CAR_ID")
//                },
//                inverseJoinColumns = {
//                        @JoinColumn(name = "OPTIONS_ID")
//                }
//    )
    @OneToMany(mappedBy = "car",orphanRemoval = true,cascade = CascadeType.ALL)
    private List<CarOptions> options = new ArrayList<>();
    private String manufacture;
    @JsonIgnore
    @OneToMany(mappedBy = "car",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Img> images = new ArrayList<>();
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEALER_ID")
    private Dealer dealer;
    private Integer point;

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
        cars.point = 0;
        return cars;
    }
//    public void addOption(Options options){
//        this.options.add(options);
//    }
    public void upPoint(){
        this.point++;
    }
    public Car carModify(CarPostsDto dto){
        this.name = dto.getName();
        this.price = dto.getPrice();
        this.year = dto.getYear();
        this.distance_driven = dto.getDistance_driven();
        this.accident_history = Boolean.parseBoolean(dto.getAccident_history());
        this.area = dto.getArea();
        this.fuel = dto.getFuel();
        this.manufacture = dto.getManufacture();
        return this;
    }
    @Builder
    public Car(String name,Integer price,String year,String distance_driven,boolean accident_history,String area,String fuel,String manufacture,int point){
        this.name = name;
        this.price = price;
        this.year = year;
        this.distance_driven = distance_driven;
        this.accident_history = accident_history;
        this.area = area;
        this.fuel = fuel;
        this.manufacture = manufacture;
        this.point = point;
    }
    public Car(){

    }
}
