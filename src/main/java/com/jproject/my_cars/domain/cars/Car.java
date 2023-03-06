package com.jproject.my_cars.domain.cars;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jproject.my_cars.domain.BaseEntity;
import com.jproject.my_cars.domain.cars.car_options.CarOptions;
import com.jproject.my_cars.domain.cars.img.Img;
import com.jproject.my_cars.domain.dealer.Dealer;
import com.jproject.my_cars.dto.CarPostsDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Car extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CAR_ID")
    private Long id;
    @NotNull
    @Column(name = "name",length = 30)
    //차량이름
    private String name;
    @NotNull
    @Column(name = "price")
    //차량가격
    private Integer price;
    @NotNull
    @Column(name = "year",length = 4)
    //출시년도
    private String year;
    @NotNull
    @Column(name = "distance_driven",length = 7)
    //주행거리
    private String distance_driven;
    @NotNull
    //사고이력
    private boolean accident_history;
    @NotNull
    @Column(name = "area",length = 5)
    //판매지역
    private String area;
    @NotNull
    @Column(name = "fuel",length = 10)
    //연료
    private String fuel;
    @OneToMany(mappedBy = "car",orphanRemoval = true,cascade = CascadeType.ALL)
    //차량이 가지고있는 옵션 리스트
    private List<CarOptions> options = new ArrayList<>();
    @NotNull
    @Column(name = "manufacture",length = 10)
    //제조회사
    private String manufacture;
    @JsonIgnore
    @OneToMany(mappedBy = "car",cascade = CascadeType.ALL,orphanRemoval = true)
    //차량이 가지고있는 이미지 리스트
    private List<Img> images = new ArrayList<>();
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEALER_ID")
    @NotNull
    //차량을 등록한 딜러
    private Dealer dealer;
    //차량의 관심을 보인 회원 숫자
    private Integer point;
    //딜러주입 연관관계 메서드
    public void setDealer(Dealer dealer){
        if(this.dealer != null){
            this.dealer.getCars().remove(this);
        }
        this.dealer = dealer;
        dealer.getCars().add(this);
    }
    //차량 등록
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
    //관심표현 증가
    public void upPoint(){
        this.point++;
    }
    //차량 수정 메서드
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
    public Car(){

    }
}
