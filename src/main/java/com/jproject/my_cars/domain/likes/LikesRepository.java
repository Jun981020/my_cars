package com.jproject.my_cars.domain.likes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LikesRepository extends JpaRepository<Likes,Long> {
    //차량과 회원으로 조회
    List<Likes> findByCarIdAndMemberId(Long carId,Long memberId);
    //회원 번호로 조회
    List<Likes> findByMemberId(Long member_id);
    @Query("select l from Likes l where l.member.id = :mid and l.car.id = :cid")
    //차량 아이디와 맴버아이디로 조회
    Likes findLikes(@Param("mid")Long memberId,@Param("cid")Long carId);
    @Modifying
    @Query("DELETE FROM Likes l WHERE l.car.id = :carId")
    //차량 아이디로 삭제
    void deleteByCarId(@Param("carId") Long carId);
}
