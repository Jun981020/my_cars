package com.jproject.my_cars.domain.cars.option;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OptionService {
    private final OptionRepository optionRepository;

    public List<Options> getOptionsList(){
        return optionRepository.findAll();
    }

}
