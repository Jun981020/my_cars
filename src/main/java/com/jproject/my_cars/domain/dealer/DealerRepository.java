package com.jproject.my_cars.domain.dealer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DealerRepository extends JpaRepository<Dealer,Long> {

    //아이디로 딜러찾기
    Dealer findByLoginId(String login_id);

    @Query("select d from Dealer d where loginId = :id and password = :password and d.card.employee_number = :number")
    //아이디와 비밀번호로 찾기
    Dealer findByIDPWNU(@Param("id")String id,@Param("password")String password,@Param("number") String number );

}
