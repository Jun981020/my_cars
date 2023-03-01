package com.jproject.my_cars.domain.cars;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car,Long>  {
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
    //차량번호로 가져오기
    Optional<Car> findById(Long id);
    @Query(value = "select * from car c inner join likes l on c.id = l.likes;",nativeQuery = true)
    List<Car> findLikesNumOfCar();

    @Query(value = "select * from car order by point DESC",nativeQuery = true)
    List<Car> findBest2Car();

    @Query("SELECT c FROM Car c WHERE c.name LIKE :name")
    List<Car> findByLikeName(@Param("name")String name);
    Page<Car> findByManufacture(String manufacture,PageRequest pageRequest);
    List<Car> findByFuel(String fuel);
    @Query("SELECT c FROM Car c WHERE c.price >= :low and c.price <= :high")
    List<Car> findByPriceLowAndHigh(@Param("low")Long low,@Param("high")Long high);
}
