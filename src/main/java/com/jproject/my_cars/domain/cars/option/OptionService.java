package com.jproject.my_cars.domain.cars.option;

import com.jproject.my_cars.domain.cars.car_options.CarOptionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OptionService {
    private final OptionRepository optionRepository;
    private final CarOptionsRepository carOptionsRepository;

    public List<Options> getOptionsList(){
        return optionRepository.findAll();
    }
    public Options getOptionOne(String name){
        return optionRepository.findByName(name);
    }

}
