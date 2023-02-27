package com.jproject.my_cars.domain.board.dealer_board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DealerBoardRepository extends JpaRepository<DealerBoard,Long> {
    @Query("SELECT COUNT(db) FROM DealerBoard db WHERE db.id = :id and db.secret_password = :password")
    Long countByDealerBoardIdAndDealerBoardSecretPassword(@Param("id")Long id, @Param("password")String password);
}
