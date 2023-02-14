package com.jproject.my_cars.web;

import com.jproject.my_cars.domain.cars.Car;
import com.jproject.my_cars.domain.cars.CarService;
import com.jproject.my_cars.domain.cars.img.ImgService;
import com.jproject.my_cars.domain.cars.option.OptionService;
import com.jproject.my_cars.domain.cars.option.Options;
import com.jproject.my_cars.domain.dealer.Dealer;
import com.jproject.my_cars.domain.member.Member;
import com.jproject.my_cars.domain.member.MemberService;
import com.jproject.my_cars.dto.CarPostsDto;
import com.jproject.my_cars.web.session.SessionManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CarController {
    private final CarService carService;
    private final ImgService imgService;
    private final SessionManager sessionManager;
    private final OptionService optionService;
    private final MemberService memberService;

    @GetMapping(value = "/cars")
    public String cars(Model model){
        List<Car> car = carService.getAll();
        model.addAttribute("car",car);
        return "cars/cars";
    }
    @GetMapping("/cars/carOne/{num}")
    public String car_one(@PathVariable("num")int num,Model model){
        Car car = carService.getOne((long) num);
        System.out.println("car.getImages() = " + car.getImages());
        model.addAttribute("car",car);
        return "cars/cars_one";
    }
    @GetMapping("/cars/posts")
    public String car_posts(Model model){
        model.addAttribute("options",optionService.getOptionsList());
        return "cars/cars_posts";
    }
    @PostMapping("/cars/postsAction")
    public String cars_posts_action(@ModelAttribute CarPostsDto dto,
                                    @RequestParam(value = "images",required = false)List<MultipartFile> images,
                                    @RequestParam("options")String str,
                                    HttpServletRequest request) throws IOException {
        System.out.println("str = " + str);
        //session dealer 정보 가져오기
        Dealer dealer = (Dealer) sessionManager.getSession(request);
        //options 배열 분리하기
        String[] options = str.split(",");

        for (String option : options) {
            System.out.println("option = " + option);
        }
        //car entity 생성
        Car car = carService.registration(dealer,dto,options);
        //img 저장
        imgService.uploadImg(images,dto.getName(),car);

        return "redirect:/main";
    }
    @GetMapping("/cars/getOptionList")
    @ResponseBody
    public List<Options> cars_get_option_list(){
        log.info("들어옴");
        return optionService.getOptionsList();
    }
    @GetMapping("/cars/upPoint")
    @ResponseBody
    public String car_up_point(Integer carNum,Integer memberNum){
        boolean result = carService.isCarUpPoint(carNum, memberNum);
        if(result){
            return "";
        }else{
            return "이미 관심표현한 차량입니다";
        }
    }
    @GetMapping("/cars/remove/{id}")
    public String cars_remove(@PathVariable("id")int id){
        carService.removeCar((long)id);
        return "redirect:/dealer/dealerPage";
    }
    @GetMapping("/cars/modify/{id}")
    public String cars_modify(@PathVariable("id")int id,Model model){
        Car car = carService.getOne((long) id);
        model.addAttribute("car",car);
        model.addAttribute("options",optionService.getOptionsList());
        return "cars/cars_modify";
    }


}
