package com.jproject.my_cars.domain.board.dealer_board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DealerBoardRepository extends JpaRepository<DealerBoard,Long> {

    @Query("SELECT COUNT(db) FROM DealerBoard db WHERE db.id = :id and db.secret_password = :password")
    //딜러 게시판에 게시글 번호와 패스워드가 일치하는지 검색, 몇개가 있는지 확인
    Long countByDealerBoardIdAndDealerBoardSecretPassword(@Param("id")Long id, @Param("password")String password);
    @Query("SELECT db FROM DealerBoard db WHERE db.title LIKE :title")
    //제목으로 찾은 게시글 검색
    Page<DealerBoard> findByLikeTitle(@Param("title") String title, PageRequest pageRequest);
}
