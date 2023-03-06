package com.jproject.my_cars.domain.board.member_board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface MemberBoardRepository extends JpaRepository<MemberBoard,Long> {

    @Query("SELECT COUNT(mb) FROM MemberBoard mb WHERE mb.id = :id and mb.secret_password = :password")
    //비밀글 패스워드와 일치하는 정보가 있는지 확인
    Long countByMemberBoardIdAndMemberBoardSecretPassword(@Param("id")Long id, @Param("password")String password);
    @Query("SELECT mb FROM MemberBoard mb WHERE mb.title LIKE :title")
    //제목과 일치하는 게시글 page로 리턴
    Page<MemberBoard> findByLikeTitle(@Param("title")String title, PageRequest pageRequest);
}
