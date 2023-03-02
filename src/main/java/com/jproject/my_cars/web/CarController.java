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
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping(value = "/cars/all")
    public String cars(@RequestParam(value = "page",defaultValue = "0")int pageNum,
                       @RequestParam(value = "value",required = false)String value,
                       Model model){
        Page<Car> page = carService.getPageList(PageRequest.of(pageNum, 6));
        model.addAttribute("page",page);
        model.addAttribute("category","all");
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
    @GetMapping("/cars/sale/{id}")
    public String cars_sale(@PathVariable("id")int id){
        carService.saleCar((long)id);
        return "redirect:/main";
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
    public String cars_search(@RequestParam(value = "page",defaultValue = "0")int pageNum,
                              @RequestParam("value")String name,
                              Model model){
        String carName = "%"+name+"%";
        Page<Car> page = carService.getLikeNameCarList(carName,PageRequest.of(pageNum,6));
        model.addAttribute("page",page);
        model.addAttribute("category","search");
        model.addAttribute("query",name);
        return "cars/cars";
    }
    @GetMapping("/cars/manufacture")
    public String car_category(@RequestParam(value = "page",defaultValue = "0")int pageNum,
                               @RequestParam("value")String value,
                               Model model){
        Page<Car> page = carService.getManufactureCarList(value,PageRequest.of(pageNum, 6));
        model.addAttribute("page",page);
        model.addAttribute("category","manufacture");
        model.addAttribute("query",value);
        return "cars/cars";
    }
    @GetMapping("/cars/fuel")
    public String car_fuel(@RequestParam(value = "page",defaultValue = "0")int pageNum,
                           @RequestParam("value")String value,
                           Model model){
        String fuel = switch (value) {
            case "Gasoline" -> "가솔린";
            case "Diesel" -> "디젤";
            case "Electricity" -> "전기";
            default -> "";
        };
        Page<Car> page = carService.getFuelCarList(fuel,PageRequest.of(pageNum,6));
        model.addAttribute("page",page);
        model.addAttribute("category","fuel");
        model.addAttribute("query",value);
        return "cars/cars";
    }
    @GetMapping("/cars/price")
    public String car_price(@RequestParam(value = "page",defaultValue = "0")int pageNum,
                            @RequestParam("value")String value,
                            Model model){
        String[] split = value.split("-");
        Long low = Long.valueOf(split[0]);
        Long high = Long.valueOf(split[1]);
        Page<Car> page = carService.getPriceList(low, high,PageRequest.of(pageNum,6));
        model.addAttribute("page",page);
        model.addAttribute("category","price");
        model.addAttribute("query",value);
        return "cars/cars";
    }





}
