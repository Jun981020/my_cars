package com.jproject.my_cars.domain.cars.option;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Options,Long> {
    //해당하는 옵션 찾기
    Options findByName(String name);

}
