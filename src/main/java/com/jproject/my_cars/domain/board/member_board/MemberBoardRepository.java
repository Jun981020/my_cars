package com.jproject.my_cars.domain.board.member_board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberBoardRepository extends JpaRepository<MemberBoard,Long> {

    @Query("SELECT COUNT(mb) FROM MemberBoard mb WHERE mb.id = :id and mb.secret_password = :password")
    Long countByMemberBoardIdAndMemberBoardSecretPassword(@Param("id")Long id, @Param("password")String password);
}
