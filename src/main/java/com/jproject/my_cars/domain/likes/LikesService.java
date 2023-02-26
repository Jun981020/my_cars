package com.jproject.my_cars.domain.likes;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class LikesService {
    private final LikesRepository likesRepository;

    public List<Likes> getLikesByMemberId(Long member_id){
        return likesRepository.findByMemberId(member_id);
    }

}
