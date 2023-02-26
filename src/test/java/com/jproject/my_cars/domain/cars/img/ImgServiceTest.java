package com.jproject.my_cars.domain.cars.img;

import com.jproject.my_cars.domain.cars.Car;
import com.jproject.my_cars.domain.cars.CarRepository;
import com.jproject.my_cars.domain.cars.option.Options;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@Transactional
class ImgServiceTest {
    @Value("${file.dir}")
    private String path;

    @Autowired
    private ImgRepository imgRepository;
    @Autowired
    private CarRepository carRepository;
    @Test
    public void imageTest(){
        Options options1 = Options.putOption("sunroof", "태양열 막는 기능");
        Options options2 = Options.putOption("nav", "길안내 서비스");
        Img img = Img.addImg("bmw-320-main", "img/bmw/320_i/bmw_320i_main.webp");
        Car cars = Car.registrationCar("bmw-320i", 3000, "2022", "130000", false,"대구", "ELECTRIC","BMW");
//        cars.addOption(options1);
//        cars.addOption(options2);
        img.setCar(cars);
        carRepository.save(cars);
        carRepository.flush();
        Car findCar = carRepository.findByName("bmw-320i");
        System.out.println("findCar = " + findCar.getImages());

//        Img i = imgRepository.findById(1L).get();
//        assertThat(i.getCar().getName()).isEqualTo("test1");
    }
    @Test
    public void imgList(){
        Car car = carRepository.findById(3L).get();
        List<Img> images = car.getImages();
        for (Img image : images) {
            System.out.println("image = " + image);
        }
    }
    @Test
    @DisplayName("메인 이미지만 가져오기")
    public void mainImageGet(){
        List<Car> all = carRepository.findAll();
        for (Car car : all) {
            List<Img> mainImg = imgRepository.findMainImg(car.getId());
            for (Img img : mainImg) {
                System.out.println("img = " + img);
            }
        }

    }
    @Test
    public void makeDir() throws IOException {
        String newDir = path + "/bmw" + "/bmw320-" + UUID.randomUUID();
        Path directory = Files.createDirectory(Paths.get(newDir));
        System.out.println("directory = " + directory);
    }
    @Test
    public void bindingPath() throws IOException {
        String newDir = path + "/bmw" + "/bmw320-" + UUID.randomUUID();
        String substring = newDir.substring(newDir.lastIndexOf("/img"));
        System.out.println("substring = " + substring);
        Files.createDirectory(Paths.get(newDir));

    }
    @Test
    public void multipartFileTest() throws IOException {
        String fileName = "testImg";
        String contentType = "image/webp";
        String filePath = "src/test/resources/img/testImg";
        MockMultipartFile mockMultipartFile = getMockMultipartFile(fileName, contentType, filePath);
    }
    private MockMultipartFile getMockMultipartFile(String fileName, String contentType, String path) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(path));
        return new MockMultipartFile(fileName, fileName + "." + contentType, contentType, fileInputStream);
    }
    @Test
    public void findPath(){
        List<String> fullPath = imgRepository.findPathByCarId(2L);
        for (String path : fullPath) {
            String substring = path.substring(0,path.lastIndexOf("/"));
            System.out.println("substring = " + substring);
        };
    }
    @Test
    public void dbPath(){
        String savedName = "BMW-320i-main.webp";
        String newDir = "/Users/ijunhyeong/IdeaProjects/my_cars/src/main/webapp/img/cars/bmw/bmw-320i-ca7b02ca-1a09-4026-ab12-738100ef8c91";
        String dbPath = newDir.substring(newDir.lastIndexOf("/webapp"))+ "/" +savedName;
        System.out.println("dbPath = " + dbPath);
    }
    @Test
    public void mapForEach(){
        HashMap<String,Integer> map = new HashMap<>();
        map.put("one",1);
        map.put("two",2);
        map.put("three",3);
        map.put("four",4);

    }

}