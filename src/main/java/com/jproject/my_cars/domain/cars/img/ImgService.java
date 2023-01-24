package com.jproject.my_cars.domain.cars.img;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ImgService {
    private final ImgRepository imgRepository;

//    public Img findMainImg(Long id){
//        return imgRepository.findMainImg(id);
//    }
}
