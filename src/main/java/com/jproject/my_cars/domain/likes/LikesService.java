package com.jproject.my_cars.domain.likes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class LikesService {
    private final LikesRepository likesRepository;

    //회원 아이디로 리스트 조회
    public List<Likes> getLikesByMemberId(Long member_id){
        return likesRepository.findByMemberId(member_id);
    }
    @Transactional
    //좋아요 기록 삭제
    public void removeLikes(Long member_id,Long car_id){
        Likes findLikes = getLikesByMemberIdAndCarId(member_id, car_id);
        likesRepository.delete(findLikes);
    }
    //회원과 차량으로 조회
    public Likes getLikesByMemberIdAndCarId(Long member_id,Long car_id){
        return likesRepository.findLikes(member_id,car_id);
    }


}
