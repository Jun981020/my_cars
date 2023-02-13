package com.jproject.my_cars.domain.dealer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jproject.my_cars.domain.cars.Car;
import com.jproject.my_cars.domain.dealer.employee.Card;
import com.jproject.my_cars.dto.DealerJoinDto;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Dealer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String loginId;
    private String password;
    private String name;
    private String phone;
    private Integer sale_count;
    @Embedded
    private Card card;
    @OneToMany(mappedBy = "dealer",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Car> cars = new ArrayList<>();

    public void saleCar(Car car){
        this.sale_count++;
        this.cars.remove(car);
    }
    public static Dealer joinDealer(DealerJoinDto dto,Card card){
        Dealer dealer = new Dealer();
        dealer.loginId = dto.getLoginId();
        dealer.password = dto.getPassword();
        dealer.name = dto.getName();
        dealer.phone = dto.getPhone();
        dealer.card = card;
        dealer.sale_count = 0;
        return dealer;
    }


    @Override
    public String toString() {
        return "Dealer{" +
                "id=" + id +
                ", loginId='" + loginId + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", sale_count=" + sale_count +
                ", card=" + card +
                ", cars=" + cars +
                '}';
    }
}
