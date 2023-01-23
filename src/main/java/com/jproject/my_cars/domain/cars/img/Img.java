package com.jproject.my_cars.domain.cars.img;

import com.jproject.my_cars.domain.cars.Car;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Img {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String path;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CAR_ID")
    private Car car;

    public static Img addImg(String name,String path){
        Img img = new Img();
        img.name = name;
        img.path = path;
        return img;
    }
    public void setCar(Car car){
        if(this.car != null){
            this.car.getImages().remove(this);
        }
        this.car = car;
        car.getImages().add(this);
    }

    @Override
    public String toString() {
        return "Img{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", car=" + car +
                '}';
    }
}
