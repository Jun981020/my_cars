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

    public List<Likes> getLikesByMemberId(Long member_id){
        return likesRepository.findByMemberId(member_id);
    }
    @Transactional
    public void removeLikes(Long member_id,Long car_id){
        Likes findLikes = getLikesByMemberIdAndCarId(member_id, car_id);
        likesRepository.delete(findLikes);
    }
    public Likes getLikesByMemberIdAndCarId(Long member_id,Long car_id){
        return likesRepository.findLikes(member_id,car_id);
    }


}
