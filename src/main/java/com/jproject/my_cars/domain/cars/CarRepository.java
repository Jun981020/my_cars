package com.jproject.my_cars.domain.cars;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car,Long>  {
    //차량 이름으로 찾기
    Car findByName(String name);
    //차량번호로 가져오기
    Optional<Car> findById(Long id);
    @Query(value = "select * from car c inner join likes l on c.id = l.likes;",nativeQuery = true)
    //좋아요를 표시한 모든차량
    List<Car> findLikesNumOfCar();
    @Query(value = "select * from car order by point DESC limit 2",nativeQuery = true)
    //좋아요를 가장 많이받은 두가지 차량
    List<Car> findBest2Car();
    @Query("SELECT c FROM Car c WHERE c.name LIKE :name")
    //이름과 유사한 차량
    Page<Car> findByLikeName(@Param("name")String name,PageRequest pageRequest);
    //제조사별로 찾기
    Page<Car> findByManufacture(String manufacture,PageRequest pageRequest);
    //연료별로 찾기
    Page<Car> findByFuel(String fuel,PageRequest pageRequest);
    @Query("SELECT c FROM Car c WHERE c.price >= :low and c.price <= :high")
    //숫자 범위로 찾기
    Page<Car> findByPriceLowAndHigh(@Param("low")Long low,@Param("high")Long high,PageRequest pageRequest);
}
