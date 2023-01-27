package com.jproject.my_cars.domain.dealer;

import com.jproject.my_cars.domain.dealer.employee.Card;
import com.jproject.my_cars.dto.DealerJoinDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class DealerServiceTest {

    @Autowired
    private DealerRepository dealerRepository;

    @Test
    public void dealerSave(){
        String date = "2018-11-13";
        DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("yyyy년MM월dd일");
        LocalDate getdate = LocalDate.parse(date);
        System.out.println("getdate.toString() = " + getdate.toString());
        DealerJoinDto dto = new DealerJoinDto("qwer", "1234", "이준형",new Card("19-1232142","한화모터스", getdate));
        Dealer dealer = Dealer.joinDealer(dto);
        dealerRepository.save(dealer);
        dealerRepository.flush();

        Dealer d = dealerRepository.findAll().get(0);
        Assertions.assertThat(d.getName()).isEqualTo(dealer.getName());
        System.out.println("d = " + d);
    }

}