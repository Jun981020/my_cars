package com.jproject.my_cars.domain.cars.img;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImgRepository extends JpaRepository<Img,Long> {

    @Query("SELECT i.path FROM Img i WHERE i.id = :id")
    //차량에 해당하는 이미지 리스트
    List<String> findPathByCarId(@Param("id")Long id);
    @Query("SELECT i.name FROM Img i WHERE i.car.id = :id AND i.name LIKE %:main%")
    String findNameMainOrSide(@Param("id")Long id,@Param("main")String main);

}
