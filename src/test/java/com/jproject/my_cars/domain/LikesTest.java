package com.jproject.my_cars.domain;

import com.jproject.my_cars.domain.cars.Car;
import com.jproject.my_cars.domain.likes.Likes;
import com.jproject.my_cars.domain.likes.LikesRepository;
import com.jproject.my_cars.domain.member.Grade;
import com.jproject.my_cars.domain.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class LikesTest {

    @Autowired
    private LikesRepository likesRepository;

    @Test
    public void likesSetTest(){
        Member member = Member.createMember("qwer", "1234", "jun", "flwnsgud@naver.com", "010-9145-6497", Grade.SILVER);
        Car car1 = Car.registrationCar("BMW-320i", 2000, "2022", "3000", false, "서울", "DESEL", "BMW");
        Car car2 = Car.registrationCar("BMW-320i", 2000, "2022", "3000", false, "서울", "DESEL", "BMW");
        Likes likes1 = new Likes();
        Likes likes2 = new Likes();
        assertThat(likes1).isSameAs(likes2);
    }
    @Test
    public void findLikesTest(){
        Likes byMemberIdAndCarId = likesRepository.findLikes(1L, 6L);
        assertThat(byMemberIdAndCarId).isNotNull();
        System.out.println("byMemberIdAndCarId = " + byMemberIdAndCarId.getCar().getName());
    }
    @Test
    public void deleteByCarIdTest(){
        List<Likes> all1 = likesRepository.findAll();
        System.out.println("all1.size() = " + all1.size());
        likesRepository.deleteByCarId(1L);
        List<Likes> all2 = likesRepository.findAll();
        System.out.println("all1.size() = " + all2.size());

    }
}
