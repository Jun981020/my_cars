package com.jproject.my_cars.web;

import com.jproject.my_cars.domain.Test;
import com.jproject.my_cars.domain.board.member_board.MemberBoard;
import com.jproject.my_cars.domain.board.member_board.MemberBoardService;
import com.jproject.my_cars.domain.cars.Car;
import com.jproject.my_cars.domain.cars.CarService;
import com.jproject.my_cars.domain.cars.car_options.CarOptionsService;
import com.jproject.my_cars.domain.cars.img.ImgService;
import com.jproject.my_cars.domain.cars.option.OptionService;
import com.jproject.my_cars.domain.cars.option.Options;
import com.jproject.my_cars.domain.dealer.Dealer;
import com.jproject.my_cars.domain.member.Member;
import com.jproject.my_cars.domain.member.MemberService;
import com.jproject.my_cars.dto.CarPostsDto;
import com.jproject.my_cars.dto.ImagesFiles;
import com.jproject.my_cars.web.session.SessionManager;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
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
    private final CarOptionsService carOptionsService;
    private final CarService carService;
    private final ImgService imgService;
    private final SessionManager sessionManager;
    private final OptionService optionService;
    private final MemberService memberService;
    private final MemberBoardService memberBoardService;

    @GetMapping(value = "/cars")
    public String cars(@RequestParam(value = "page",required = false)Integer pageNum,Model model){
        Page<Car> page = carService.getPageList();
        page.get();
        model.addAttribute("page",page);
        return "cars/cars";
    }
    @GetMapping("/cars/carOne/{num}")
    public String car_one(@PathVariable("num")int num,Model model){
        Car car = carService.getOne((long) num);
        model.addAttribute("car",car);
        car.getOptions().get(0).getOptions().getName();
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
    public String car_up_point(Integer carNum,HttpServletRequest request){
        Member member = (Member) sessionManager.getSession(request);
        boolean result = carService.isCarUpPoint(carNum, member);
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
        model.addAttribute("car_options",carOptionsService.getCarOptionsListByCarId(car.getId()));
        return "cars/cars_modify";
    }
    @PutMapping("/cars/modifyAction")
    public String cars_modify_action(@RequestParam(value = "id")int id,
                                     @ModelAttribute CarPostsDto dto,
                                     @ModelAttribute ImagesFiles files,
                                     @RequestParam(value = "options",required = false)String str,
                                     HttpServletRequest request) throws IOException {
        Long carNum = (long)id;
        String[] options = str.split(",");
        //car entity 가져오기
        Car car = carService.getOne(carNum);
        //options & car 정보 수정
        carService.modifyCar(carNum,dto,options);
        //imgList 넘겨주기
        HashMap<String, MultipartFile> map = files.setImageList();
        System.out.println("list.size() = " + map.size());
        imgService.modifyImg(map,dto.getName(),car);
        return "redirect:/main";
    }
    @GetMapping("/cars/search")
    public String cars_search(@RequestParam("name")String name,Model model){
        String carName = "%"+name+"%";
        List<Car> likeNameCarList = carService.getLikeNameCarList(carName);
        model.addAttribute("car",likeNameCarList);
        return "cars/cars";
    }
    @GetMapping("/cars/category/manufacture")
    public String car_category(@RequestParam("value")String value,Model model){
        List<Car> manufactureCarList = carService.getManufactureCarList(value);
        model.addAttribute("car",manufactureCarList);
        return "cars/cars";
    }
    @GetMapping("/cars/category/fuel")
    public String car_fuel(@RequestParam("value")String value,Model model){
        String fuel = switch (value) {
            case "Gasoline" -> "가솔린";
            case "Diesel" -> "디젤";
            case "Electricity" -> "전기";
            default -> "";
        };
        List<Car> fuelCarList = carService.getFuelCarList(fuel);
        model.addAttribute("car",fuelCarList);
        return "cars/cars";
    }
    @GetMapping("/cars/category/price")
    public String car_price(@RequestParam("low")int lowInt,@RequestParam("high")int highInt,Model model){
        Long low = (long) lowInt;
        Long high = (long) highInt;
        List<Car> priceList = carService.getPriceList(low, high);
        System.out.println("priceList = " + priceList);
        model.addAttribute("car",priceList);
        return "cars/cars";
    }





}
