package com.jproject.my_cars.web;

import com.jproject.my_cars.domain.cars.CarService;
import com.jproject.my_cars.domain.cars.img.ImgService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CarController.class)
class CarControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    CarService carService;
    @MockBean
    ImgService imgService;
    @Test
    @DisplayName("차량 리스트 보기")
    public void car_list() throws Exception {
        mockMvc.perform(get("/cars"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().string())
                .andDo(print());
    }


}