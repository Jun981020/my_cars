package com.jproject.my_cars.domain.cars.img;

import com.jproject.my_cars.domain.cars.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImgRepository extends JpaRepository<Img,Long> {

    @Query("select i from Img i , Car c where i.name like '%main' and c.id = :id")
    List<Img> findMainImg(@Param("id")Long id);
}
