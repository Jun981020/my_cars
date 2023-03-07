package com.jproject.my_cars.domain.dealer;

import com.jproject.my_cars.domain.dealer.employee.Card;
import com.jproject.my_cars.dto.DealerJoinDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class DealerServiceTest {

    @Autowired
    private DealerRepository dealerRepository;

    @Test
    public void dealerSave(){
//        String dateStr = "2018-11-13";
//        Card card = new Card("1023-1221-32-31", "한양모터스", dateStr);
//        DealerJoinDto dto = new DealerJoinDto("qwer","1234","이준형");
//        Dealer dealer = Dealer.joinDealer(dto,card);
//        dealerRepository.save(dealer);
//        dealerRepository.flush();
//
//        Dealer d = dealerRepository.findAll().get(0);
//        Assertions.assertThat(d.getName()).isEqualTo(dealer.getName());
//        System.out.println("d = " + d);
    }
    @Test
    public void timeTest(){
//        String dateStr = "2018-11-13";
//        Card card = new Card("1023-1221-32-31", "한양모터스", dateStr);
//        DealerJoinDto dto = new DealerJoinDto("qwer","1234","이준형",card);
//        System.out.println("dto = " + dto);
    }
//    @Test
//    @DisplayName("딜러 아아디 비번 사원번호로 데이터 찾아오기")
//    public void findByLoginIdAndPasswordAndEmployeeNumberTest(){
//        Dealer d = dealerRepository.findByIDPWNU("qwer", "1234", "18-112-312311");
//        assertThat(d.getLoginId()).isEqualTo("qwer");
//    }

}