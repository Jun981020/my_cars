package com.jproject.my_cars.domain.cars.car_options;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarOptionsRepository extends JpaRepository<CarOptions,Long> {

    //치량 삭제
    void deleteByCarId(Long id);
    //차량에 적용된 옵션 정보
    List<CarOptions> findByCarId(Long car_id);
}
