package com.jproject.my_cars.domain.member;

import com.jproject.my_cars.domain.BaseEntity;
import com.jproject.my_cars.domain.cars.Car;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.*;

@Getter
@Entity
public class Member extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String loginId;
    private String password;
    private String name;
    private String email;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Embedded
    private List<Car> likes = new ArrayList<>();

    public static Member createMember(String loginId,String password,String name, String email, String phone, Role role){
        Member members = new Member();
        members.loginId = loginId;
        members.password = password;
        members.name = name;
        members.email = email;
        members.phone = phone;
        members.role = role;
        return members;
    }
    public void addLikes(Car car){
        getLikes().add(car);
    }
    public boolean isCheckDuplicateLikes(Car car){
        int result = 0;

        if(getLikes().isEmpty()){
            addLikes(car);
        }else{
            long count = getLikes().stream().filter(
                    c -> Objects.equals(c.getId(), car.getId())
            ).count();
            result = (int) count;
        }

        if(result == 0){
            addLikes(car);
            return true;
        }else{
            return false;
        }
    }
    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", loginId='" + loginId + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", role=" + role +
                '}';
    }
}
