package com.jproject.my_cars.domain.likes;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikesRepository extends JpaRepository<Likes,Long> {
    List<Likes> findByCarIdAndMemberId(Long carId,Long memberId);
    List<Likes> findByMemberId(Long member_id);
}
