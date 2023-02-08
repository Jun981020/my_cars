package com.jproject.my_cars.domain.cars.option;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Options,Long> {

    Options findByName(String name);

}
