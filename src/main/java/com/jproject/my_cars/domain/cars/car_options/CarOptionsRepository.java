package com.jproject.my_cars.domain.cars.car_options;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarOptionsRepository extends JpaRepository<CarOptions,Long> {

    void deleteByCarId(Long id);

    List<CarOptions> findByCarId(Long car_id);
}
