package com.jproject.my_cars.domain.cars;

import com.jproject.my_cars.domain.cars.img.Img;
import com.jproject.my_cars.domain.cars.option.OptionRepository;
import com.jproject.my_cars.domain.cars.option.Options;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class CarsServiceTest {

    @Autowired
    private CarRepository carsRepository;
    @Autowired
    private OptionRepository optionRepository;
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    //차량등록
    public void save(){
        Options options1 = Options.putOption("sunroof", "태양열 막는 기능");
        Options options2 = Options.putOption("nav", "길안내 서비스");
        Car cars = Car.registrationCar("bmw-x7", 5500, "2021", "2000", false,"부산","ELECTRIC","bmw","FOREIGN");
        cars.addOption(options1);
        cars.addOption(options2);
        carsRepository.save(cars);
//        carsRepository.flush();

//        carsRepository.delete(cars);
//        carsRepository.flus]\h();

//        Car test = carsRepository.findByName("test");
//        assertThat(test.getOptions().size()).isEqualTo(0);
//        Car cars1 = carsRepository.findAll().get(0);
//        assertThat(cars.getName()).isEqualTo(cars1.getName());
    }

    @Test
    //판매중인 차량 리스트
    public void cars_list(){
        Options options1 = Options.putOption("sunroof", "태양열 막는 기능");
        Options options2 = Options.putOption("nav", "길안내 서비스");
        Car cars = Car.registrationCar("bmw-320", 2200, "2022", "130000", false,"대구","FOREIGN","hyundai","FOREIGN");
        cars.addOption(options1);
        cars.addOption(options2);
        carsRepository.save(cars);

        Options options3 = Options.putOption("kka", "웃자");
        Options options4 = Options.putOption("nav", "길안내 서비스");
        Car cars1 = Car.registrationCar("Santafe", 5000, "2023", "2000", false,"서울","DIESEL","hyundai","FOREIGN");
        cars1.addOption(options3);
        cars1.addOption(options4);
        carsRepository.save(cars1);


        List<Car> list = carsRepository.findAll();
        assertThat(list.size()).isEqualTo(2);
        for (Car car : list) {
            System.out.println(car);
        }
    }
    @Test
    //최신순으로 보기
    public void newest_list(){
        Options options1 = Options.putOption("sunroof", "태양열 막는 기능");
        Options options2 = Options.putOption("nav", "길안내 서비스");
        Car cars = Car.registrationCar("bmw-320", 3, "2022", "130000", false,"대구","ELECTRIC","hyundai","FOREIGN");
        cars.addOption(options1);
        cars.addOption(options2);
        carsRepository.save(cars);

        Options options3 = Options.putOption("kka", "웃자");
        Options options4 = Options.putOption("nav", "길안내 서비스");
        Car cars1 = Car.registrationCar("Santafe", 2, "2023", "2000", false,"서울","DIESEL","hyundai","DOMESTIC");
        cars1.addOption(options3);
        cars1.addOption(options4);
        carsRepository.save(cars1);

        Car cars2 = Car.registrationCar("Benze", 5, "2022", "130000", false,"대구","DIESEL","hyundai","DOMESTIC");
        cars2.addOption(options1);
        cars2.addOption(options2);
        carsRepository.save(cars2);

        Car cars3 = Car.registrationCar("sm-9", 1, "2023", "2000", false,"서울","DIESEL","hyundai","DOMESTIC");
        cars1.addOption(options3);
        cars1.addOption(options4);
        carsRepository.save(cars3);

        //최신순으로 보기
        List<Car> newsestList = carsRepository.findAllByOrderByCreateAtDesc();
        for (Car car : newsestList) {
            System.out.println("car = " + car);
        }
        assertThat(newsestList.size()).isEqualTo(4);
        assertThat(newsestList.get(0).getName()).isEqualTo("sm-9");
    }
    @Test
    //낮은,높은가격순으로 정렬
    public void low_or_high_price_list(){
        Options options1 = Options.putOption("sunroof", "태양열 막는 기능");
        Options options2 = Options.putOption("nav", "길안내 서비스");
        Car cars = Car.registrationCar("bmw-320", 3, "2022", "130000", false,"대구","ELECTRIC","hyundai","FOREIGN");
        cars.addOption(options1);
        cars.addOption(options2);
        carsRepository.save(cars);

        Options options3 = Options.putOption("kka", "웃자");
        Options options4 = Options.putOption("nav", "길안내 서비스");
        Car cars1 = Car.registrationCar("Santafe", 2, "2023", "2000", false,"서울","ELECTRIC","hyundai","FOREIGN");
        cars1.addOption(options3);
        cars1.addOption(options4);
        carsRepository.save(cars1);

        Car cars2 = Car.registrationCar("Benze", 5, "2022", "130000", false,"대구","ELECTRIC","hyundai","FOREIGN");
        cars2.addOption(options1);
        cars2.addOption(options2);
        carsRepository.save(cars2);

        Car cars3 = Car.registrationCar("sm-9", 1, "2023", "2000", false,"서울","ELECTRIC","hyundai","FOREIGN");
        cars1.addOption(options3);
        cars1.addOption(options4);
        carsRepository.save(cars3);

        //낮은가격순으로 조회
        List<Car> low = carsRepository.findAllByOrderByPrice();
        assertThat(low.get(0).getPrice()).isEqualTo(1);

        //높은가격순으로 조회
        List<Car> high = carsRepository.findAllByOrderByPriceDesc();
        assertThat(high.get(0).getPrice()).isEqualTo(5);
    }

    @Test
    //외제,국산차만 보기
    public void domestic_foreign_list(){
        Options options1 = Options.putOption("sunroof", "태양열 막는 기능");
        Options options2 = Options.putOption("nav", "길안내 서비스");
        Car cars = Car.registrationCar("bmw-320", 3, "2022", "130000", false,"대구","ELECTRIC","hyundai","FOREIGN");
        cars.addOption(options1);
        cars.addOption(options2);
        carsRepository.save(cars);

        Options options3 = Options.putOption("kka", "웃자");
        Options options4 = Options.putOption("nav", "길안내 서비스");
        Car cars1 = Car.registrationCar("Santafe", 2, "2023", "2000", false,"서울","DIESEL","hyundai","FOREIGN");
        cars1.addOption(options3);
        cars1.addOption(options4);
        carsRepository.save(cars1);

        Car cars2 = Car.registrationCar("Benze", 5, "2022", "130000", false,"대구","ELECTRIC","hyundai","FOREIGN");
        cars2.addOption(options1);
        cars2.addOption(options2);
        carsRepository.save(cars2);

        Car cars3 = Car.registrationCar("sm-9", 1, "2023", "2000", false,"서울","DIESEL","hyundai","DOMESTIC");
        cars1.addOption(options3);
        cars1.addOption(options4);
        carsRepository.save(cars3);

        //국산차만 보기
        List<Car> domestic_list = carsRepository.findAllByManufacture(Manufacture.DOMESTIC);
        assertThat(domestic_list.size()).isEqualTo(1);
        //외제차만 보기
        List<Car> forign_list = carsRepository.findAllByManufacture(Manufacture.FOREIGN);
        assertThat(forign_list.size()).isEqualTo(3);
    }
    @Test
    //휘발유,경유,전기차만
    public void gasoline_diesel_electric(){
        Options options1 = Options.putOption("sunroof", "태양열 막는 기능");
        Options options2 = Options.putOption("nav", "길안내 서비스");
        Car cars = Car.registrationCar("bmw-320", 3, "2022", "130000", false,"대구","ELECTRIC","hyundai","FOREIGN");
        cars.addOption(options1);
        cars.addOption(options2);
        carsRepository.save(cars);

        Options options3 = Options.putOption("kka", "웃자");
        Options options4 = Options.putOption("nav", "길안내 서비스");
        Car cars1 = Car.registrationCar("Santafe", 2, "2023", "2000", false,"서울","ELECTRIC","hyundai","FOREIGN");
        cars1.addOption(options3);
        cars1.addOption(options4);
        carsRepository.save(cars1);

        Car cars2 = Car.registrationCar("Benze", 5, "2022", "130000", false,"대구","GASOLINE","hyundai","FOREIGN");
        cars2.addOption(options1);
        cars2.addOption(options2);
        carsRepository.save(cars2);

        Car cars3 = Car.registrationCar("sm-9", 1, "2023", "2000", false,"서울","GASOLINE","hyundai","DOMESTIC");
        cars1.addOption(options3);
        cars1.addOption(options4);
        carsRepository.save(cars3);

        //휘발유 보기
        List<Car> gasoline_list = carsRepository.findAllByFuel(Fuel.GASOLINE);
        assertThat(gasoline_list.size()).isEqualTo(2);
        //경유 보기
        List<Car> diesel_list = carsRepository.findAllByFuel(Fuel.DIESEL);
        assertThat(diesel_list.size()).isEqualTo(1);
        //전기 보기
        List<Car> electric_list = carsRepository.findAllByFuel(Fuel.ELECTRIC);
        assertThat(electric_list.size()).isEqualTo(1);
    }

    @Test
    public void response_entity_test(){
        String uri = "http://localhost:"+port+"/cars";
    }

}