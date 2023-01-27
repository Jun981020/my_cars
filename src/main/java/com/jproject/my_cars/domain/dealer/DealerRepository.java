package com.jproject.my_cars.domain.dealer;

import com.jproject.my_cars.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DealerRepository extends JpaRepository<Dealer,Long> {

    public Dealer findByLoginId(String login_id);

    @Query("select d from Dealer d where loginId = :id and password = :password and d.card.employee_number = :number")
    public Dealer findByIDPWNU(@Param("id")String id,@Param("password")String password,@Param("number") String number );

}
