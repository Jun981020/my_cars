package com.jproject.my_cars.domain.dealer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jproject.my_cars.domain.cars.Car;
import com.jproject.my_cars.domain.dealer.employee.Card;
import com.jproject.my_cars.dto.DealerJoinDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Dealer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(name = "login_id",length = 20)
    //로그인아이디
    private String loginId;
    @NotNull
    @Column(name = "password",length = 30)
    //비밀번호
    private String password;
    @NotNull
    @Column(name = "name",length = 10)
    //이름
    private String name;
    @NotNull
    @Column(name = "phone",length = 13)
    //전화번호
    private String phone;
    //차량 판매횟수
    private Integer sale_count;
    @Embedded
    //값타입으로 들고있는 정보
    private Card card;
    @JsonIgnore
    @OneToMany(mappedBy = "dealer",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    //딜러가 등록한 차량리스트
    private List<Car> cars = new ArrayList<>();

    //차량 팬매처리
    public void saleCar(Car car){
        this.sale_count++;
        this.cars.remove(car);
    }
    //딜러 회원가입
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
