package com.jproject.my_cars.domain.likes;

import com.jproject.my_cars.domain.cars.Car;
import com.jproject.my_cars.domain.member.Member;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Likes {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Car car;

    public void setAll(Member member,Car car){
        member.getLikes().add(this);
        this.member = member;
        this.car = car;
    }
}
