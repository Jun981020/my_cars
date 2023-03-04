package com.jproject.my_cars.domain.member;

import com.jproject.my_cars.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

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
    private Grade grade;

    //Likes 를 값타입으로 가지고있기에는 사용할 요소가 많아서 entity로 바꿈
//    @ElementCollection
//    @CollectionTable(name = "LIKES",joinColumns = @JoinColumn(name = "MEMBER_ID"))
//    @Column(name = "LIKES")
//    @JsonIgnore
//    private List<Long> likes = new ArrayList<>();

    public static Member createMember(String loginId,String password,String name, String email, String phone, Grade grade){
        Member members = new Member();
        members.loginId = loginId;
        members.password = password;
        members.name = name;
        members.email = email;
        members.phone = phone;
        members.grade = grade;
        return members;
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
                ", grade=" + grade +
                '}';
    }
}
