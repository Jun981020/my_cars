package com.jproject.my_cars.domain.dealer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DealerService {
    private final DealerRepository dealerRepository;

    //딜러 엔티티 저장
    @Transactional
    public void saveDealer(Dealer dealer){
        dealerRepository.save(dealer);
    }

    //딜러아이디 중복확인
    public boolean check_join_id(String id){
        if(dealerRepository.findByLoginId(id) == null){
            return true;
        }else{
            return false;
        }
    }
    //딜러 로그인 아이디,패스워드,사원번호 확인
    public boolean check_login_id_pw_num(String id,String pw,String nu){
        return dealerRepository.findByIDPWNU(id, pw, nu) != null;
    }
    public Dealer findOne(String id){
        return dealerRepository.findByLoginId(id);
    }
}
