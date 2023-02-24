package com.jproject.my_cars.domain.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jproject.my_cars.domain.BaseEntity;
import com.jproject.my_cars.domain.cars.Car;
import com.jproject.my_cars.domain.likes.Likes;
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

    //Likes 를 값타입으로 가지고있기에는 사용할 요소가 많아서 entity로 바꿈
//    @ElementCollection
//    @CollectionTable(name = "LIKES",joinColumns = @JoinColumn(name = "MEMBER_ID"))
//    @Column(name = "LIKES")
//    @JsonIgnore
//    private List<Long> likes = new ArrayList<>();
    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL)
    private List<Likes> likes = new ArrayList<>();

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
    public void addLikes(Likes likes,Car car){
        likes.setAll(this,car);
        this.getLikes().add(likes);
    }
    public boolean isCheckDuplicateLikes(Car car){
        int bindingResult = 0;

        if(getLikes().isEmpty()){
//            addLikes(car);
            return true;
        }else{
            long count = getLikes().stream().filter(
                    c -> Objects.equals(c, car.getId())
            ).count();
            bindingResult = (int) count;
            if(bindingResult == 0){
//                addLikes(car);
                return true;
            }else{
                return false;
            }
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
