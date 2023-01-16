package com.jproject.my_cars.domain.board;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Long> {

    Board findByTitle(String title);

}
