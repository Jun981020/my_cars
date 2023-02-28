package com.jproject.my_cars.domain.board.dealer_board;

import com.jproject.my_cars.domain.board.member_board.MemberBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DealerBoardRepository extends JpaRepository<DealerBoard,Long> {
    @Query("SELECT COUNT(db) FROM DealerBoard db WHERE db.id = :id and db.secret_password = :password")
    Long countByDealerBoardIdAndDealerBoardSecretPassword(@Param("id")Long id, @Param("password")String password);
    @Query("SELECT db FROM DealerBoard db WHERE db.title LIKE :title")
    List<DealerBoard> findByLikeTitle(@Param("title") String title);
}
