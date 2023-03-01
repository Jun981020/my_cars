package com.jproject.my_cars.domain.cars;

import com.jproject.my_cars.domain.cars.car_options.CarOptions;
import com.jproject.my_cars.domain.cars.car_options.CarOptionsRepository;
import com.jproject.my_cars.domain.cars.option.OptionRepository;
import com.jproject.my_cars.domain.cars.option.Options;
import com.jproject.my_cars.domain.dealer.Dealer;
import com.jproject.my_cars.domain.likes.Likes;
import com.jproject.my_cars.domain.likes.LikesRepository;
import com.jproject.my_cars.domain.member.Member;
import com.jproject.my_cars.domain.member.MemberRepository;
import com.jproject.my_cars.dto.CarPostsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class CarService {
    private final CarRepository carRepository;
    private final OptionRepository optionRepository;
    private final MemberRepository memberRepository;
    private final LikesRepository likesRepository;
    private final CarOptionsRepository carOptionsRepository;

    public List<Car> getAll(){
        return carRepository.findAll();
    }
    public Car getOne(Long id){
        return carRepository.findById(id).get();
    }
    @Transactional
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
//    @Transactional
//    public long likesListDuplicate(List<Likes> list){
//        Member member1 = memberRepository.findById(member.getId()).get();
//        return member1.getLikes().stream().filter(
//                c -> Objects.equals(c.getCar().getId(), num)
//        ).count();
//    }

    @Transactional
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
        for (String name : options) {
            Options findOption = optionRepository.findByName(name);
            log.info("findOption : " + findOption);
            CarOptions carOptions = new CarOptions();
            carOptions.setCarAndOptions(car,findOption);
            carOptionsRepository.saveAndFlush(carOptions);
        }
        return car;
    }
    @Transactional
    public void removeCar(Long id){
        Car car = carRepository.findById(id).get();
        carRepository.delete(car);
    }
    @Transactional
    public void modifyCar(Long carNum,CarPostsDto dto,String[] options){
        Car entity = carRepository.findById(carNum).get();
        Car car = entity.carModify(dto);
        carOptionsRepository.deleteByCarId(car.getId());

        for (String name : options) {
            Options findOption = optionRepository.findByName(name);
            CarOptions carOptions = new CarOptions();
            carOptions.setCarAndOptions(car,findOption);
            carOptionsRepository.saveAndFlush(carOptions);
        }
    }

    @Transactional
    public Car getMemberLikesCarList(Member member) {
//        List<Long> likes = member.getLikes();
//        for (Long like : likes) {
//            carRepository.findLikesNumOfCar(like);
//        }
//        return null;
        return null;
    }
    public List<Car> getTopTwoCar(){
        return carRepository.findBest2Car();
    }
    public List<Car> getLikeNameCarList(String name){
        return carRepository.findByLikeName(name);
    }
    public Page<Car> getManufactureCarList(String manufacture,PageRequest pageRequest){
        return carRepository.findByManufacture(manufacture,pageRequest);
    }
    public List<Car> getFuelCarList(String fuel){
        return carRepository.findByFuel(fuel);
    }
    public List<Car> getPriceList(Long low,Long high){
        return carRepository.findByPriceLowAndHigh(low,high);
    }

    public Page<Car> getPageList(PageRequest of) {
        return carRepository.findAll(of);
    }
}
