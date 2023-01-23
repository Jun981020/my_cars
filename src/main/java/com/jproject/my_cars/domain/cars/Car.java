package com.jproject.my_cars.domain.cars;

import com.jproject.my_cars.domain.BaseEntity;
import com.jproject.my_cars.domain.option.Options;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Car extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer price;
    private String year;
    private String distance_driven;
    private boolean accident_history;
    private String area;
    @Enumerated(EnumType.STRING)
    private Fuel fuel;
    private String company;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Options> options = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private Manufacture manufacture;
    private String img_path;

    public static Car registrationCar(String name, Integer price, String year, String distance_driven, boolean accident_history, String area , Fuel fuel, String company, Manufacture manufacture,String img_path){
        Car cars = new Car();
        cars.name = name;
        cars.price = price;
        cars.year = year;
        cars.distance_driven = distance_driven;
        cars.accident_history = accident_history;
        cars.area = area;
        cars.fuel = fuel;
        cars.company = company;
        cars.manufacture = manufacture;
        cars.img_path = img_path;
        return cars;
    }
    public void addOption(Options options){
        this.getOptions().add(options);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", year='" + year + '\'' +
                ", distance_driven='" + distance_driven + '\'' +
                ", accident_history=" + accident_history +
                ", area='" + area + '\'' +
                ", fuel=" + fuel +
                ", company='" + company + '\'' +
                ", options=" + options +
                ", manufacture=" + manufacture +
                '}';
    }
}
