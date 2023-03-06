package com.jproject.my_cars.domain.cars.img;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImgRepository extends JpaRepository<Img,Long> {

    @Query("select i.path from Img i , Car c where c.id = :id")
    //차량에 해당하는 이미지 리스트
    List<String> findPathByCarId(@Param("id")Long id);

}
