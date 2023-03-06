package com.jproject.my_cars.domain.member;

import com.jproject.my_cars.domain.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Entity
public class Member extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(name = "login_id",length = 20)
    //로그인 아이디
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
    @Column(name = "email",length = 30)
    //이메일
    private String email;
    @NotNull
    @Column(name = "phone",length = 13)
    //전화번호
    private String phone;
    @Enumerated(EnumType.STRING)
    //등급
    private Grade grade;

    //Likes 를 값타입으로 가지고있기에는 사용할 요소가 많아서 entity로 바꿈
//    @ElementCollection
//    @CollectionTable(name = "LIKES",joinColumns = @JoinColumn(name = "MEMBER_ID"))
//    @Column(name = "LIKES")
//    @JsonIgnore
//    private List<Long> likes = new ArrayList<>();

    //회원 생성
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
