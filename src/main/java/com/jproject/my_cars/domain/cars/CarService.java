package com.jproject.my_cars.domain.cars;

import com.jproject.my_cars.domain.cars.car_options.CarOptions;
import com.jproject.my_cars.domain.cars.car_options.CarOptionsRepository;
import com.jproject.my_cars.domain.cars.img.ImgService;
import com.jproject.my_cars.domain.cars.img.S3FileUploadService;
import com.jproject.my_cars.domain.cars.option.OptionRepository;
import com.jproject.my_cars.domain.cars.option.Options;
import com.jproject.my_cars.domain.dealer.Dealer;
import com.jproject.my_cars.domain.likes.Likes;
import com.jproject.my_cars.domain.likes.LikesRepository;
import com.jproject.my_cars.domain.member.Member;
import com.jproject.my_cars.domain.member.MemberRepository;
import com.jproject.my_cars.dto.CarPostsDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class CarService {
    private final CarRepository carRepository;
    private final OptionRepository optionRepository;
    private final LikesRepository likesRepository;
    private final CarOptionsRepository carOptionsRepository;
    private final ImgService imgService;
    private final S3FileUploadService s3FileUploadService;

    //모든 차량 가져오기
    public List<Car> getAll(){
        return carRepository.findAll();
    }
    //치량 하나 가져오기
    public Car getOne(Long id){
        return carRepository.findById(id).get();
    }
    @Transactional
    //차량 좋아요 추가
    public boolean isCarUpPoint(Integer carNum,Member member){
        Car car = carRepository.findById((long) carNum).get();
        List<Likes> likesList = likesRepository.findByCarIdAndMemberId(car.getId(), member.getId());
        if(likesList.isEmpty()){
            Likes likes = new Likes();
            likes.setCar(car);
            likes.setMember(member);
            likesRepository.save(likes);
            car.upPoint();
            return true;
        }else{
            return false;
        }
    }
    @Transactional
    //차량 등록
    public Car registration(Dealer dealer, CarPostsDto dto,String[] options) {
        Car car = Car.registrationCar(
                dto.getName(),
                dto.getPrice(),
                dto.getYear(),
                dto.getDistance_driven(),
                Boolean.parseBoolean(dto.getAccident_history()),
                dto.getArea(),
                dto.getFuel(),
                dto.getManufacture());
        car.setDealer(dealer);
        carRepository.saveAndFlush(car);
        if(options.length != 0){
            for (String name : options) {
                Options findOption = optionRepository.findByName(name);
                CarOptions carOptions = new CarOptions();
                carOptions.setCarAndOptions(car,findOption);
                carOptionsRepository.saveAndFlush(carOptions);
            }
        }
        return car;
    }
    @Transactional
    //차량 삭제
    public void removeCar(Long id) throws IOException {
        Car car = carRepository.findById(id).get();
        s3FileUploadService.removeImg(car);
        carRepository.delete(car);
    }
    @Transactional
    //차량 수정
    public void modifyCar(Long carNum,CarPostsDto dto,String[] options){
        Car entity = carRepository.findById(carNum).get();
        Car car = entity.carModify(dto);
        carOptionsRepository.deleteByCarId(car.getId());
        if(options.length != 0){
            for (String name : options) {
                Options findOption = optionRepository.findByName(name);
                CarOptions carOptions = new CarOptions();
                carOptions.setCarAndOptions(car,findOption);
                carOptionsRepository.saveAndFlush(carOptions);
            }
        }
    }

    //좋아요 차량 탑2 리턴
    public List<Car> getTopTwoCar(){
        return carRepository.findBest2Car();
    }
    //이름과 비슷한 차량리스트
    public Page<Car> getLikeNameCarList(String name,PageRequest pageRequest){
        return carRepository.findByLikeName(name,pageRequest);
    }
    //제조사로 찾기
    public Page<Car> getManufactureCarList(String manufacture,PageRequest pageRequest){
        return carRepository.findByManufacture(manufacture,pageRequest);
    }
    //연료로 찾기
    public Page<Car> getFuelCarList(String fuel,PageRequest pageRequest){
        return carRepository.findByFuel(fuel,pageRequest);
    }
    //가격별로 찾기
    public Page<Car> getPriceList(Long low,Long high,PageRequest pageRequest){
        return carRepository.findByPriceLowAndHigh(low,high,pageRequest);
    }
    //차량 페이지로 가져오기
    public Page<Car> getPageList(PageRequest of) {
        return carRepository.findAll(of);
    }
    @Transactional
    //차량 판매처리
    public void saleCar(Long id,HttpServletRequest request) throws IOException {
        Car one = getOne(id);
        Dealer dealer = one.getDealer();
        dealer.saleCar(one);
        s3FileUploadService.removeImg(one);
        likesRepository.deleteByCarId(one.getId());
        carRepository.delete(one);
    }
}
