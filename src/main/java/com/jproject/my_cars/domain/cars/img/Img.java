package com.jproject.my_cars.domain.cars.img;

import com.jproject.my_cars.domain.cars.Car;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Entity
@Getter
public class Img {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    //이름
    private String name;
    @NotNull
    //경로
    private String path;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CAR_ID")
    @NotNull
    //차량 정보
    private Car car;
    //이미지 추가
    public static Img addImg(String name,String path){
        Img img = new Img();
        img.name = name;
        img.path = path;
        return img;
    }
    //차량 주입 연관관계 메서드
    public void setCar(Car car){
        if(this.car != null){
            this.car.getImages().remove(this);
        }
        this.car = car;
        car.getImages().add(this);
    }

}
