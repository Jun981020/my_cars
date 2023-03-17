package com.jproject.my_cars.domain.cars.car_options;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarOptionsRepository extends JpaRepository<CarOptions,Long> {

    //치량 삭제
    @Modifying
    @Query("DELETE FROM CarOptions co WHERE co.car.id = :carId")
    void deleteByCarId(@Param("carId") Long id);
    //차량에 적용된 옵션 정보
    List<CarOptions> findByCarId(Long car_id);
}
