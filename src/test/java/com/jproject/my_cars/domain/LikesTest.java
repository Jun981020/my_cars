package com.jproject.my_cars.domain;

import com.jproject.my_cars.domain.cars.Car;
import com.jproject.my_cars.domain.likes.Likes;
import com.jproject.my_cars.domain.member.Member;
import com.jproject.my_cars.domain.member.Role;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class LikesTest {

    @Test
    public void likesSetTest(){
        Member member = Member.createMember("qwer", "1234", "jun", "flwnsgud@naver.com", "010-9145-6497", Role.SILVER);
        Car car1 = Car.registrationCar("BMW-320i", 2000, "2022", "3000", false, "서울", "DESEL", "BMW");
        Car car2 = Car.registrationCar("BMW-320i", 2000, "2022", "3000", false, "서울", "DESEL", "BMW");
        Likes likes1 = new Likes();
        Likes likes2 = new Likes();
        Assertions.assertThat(likes1).isSameAs(likes2);
    }
}
