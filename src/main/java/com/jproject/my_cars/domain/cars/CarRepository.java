package com.jproject.my_cars.domain.cars;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car,Long> {
    //최신순으로 정렬
    List<Car> findAllByOrderByCreateAtDesc();
    //낮은가격순으로 정렬
    List<Car> findAllByOrderByPrice();
    //높은가격순으로 정렬
    List<Car> findAllByOrderByPriceDesc();
    //국산,외제차 만보기
    List<Car> findAllByManufacture(Manufacture manufacture);
    //연료별로 보기
    List<Car> findAllByFuel(Fuel fuel);
    //이름으로 찾기
    Car findByName(String name);
}
