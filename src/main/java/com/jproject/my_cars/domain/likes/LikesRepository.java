package com.jproject.my_cars.domain.likes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LikesRepository extends JpaRepository<Likes,Long> {
    List<Likes> findByCarIdAndMemberId(Long carId,Long memberId);
    List<Likes> findByMemberId(Long member_id);
    @Query("select l from Likes l where l.member.id = :mid and l.car.id = :cid")
    Likes findLikes(@Param("mid")Long memberId,@Param("cid")Long carId);
}
