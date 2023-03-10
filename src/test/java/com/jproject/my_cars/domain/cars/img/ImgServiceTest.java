package com.jproject.my_cars.domain.cars.img;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.jproject.my_cars.domain.cars.Car;
import com.jproject.my_cars.domain.cars.CarRepository;
import jakarta.annotation.PostConstruct;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
@Transactional
class ImgServiceTest {
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;
    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;
    @Value("${cloud.aws.region.static}")
    private String region;
    @Autowired
    private ImgRepository imgRepository;
    @Autowired
    private CarRepository carRepository;
    private AmazonS3 amazonS3Client;

    @PostConstruct
    public void setS3Client() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        amazonS3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(this.region)
                .build();
    }

    @Test
    public void removeImg(){
        amazonS3Client.deleteObject(bucket,"BMW-320i-side58233f7a765e84c74aac1afb06de005ee.webp");
    }
//    @Test
//    public void imageTest(){
//        Options options1 = Options.putOption("sunroof", "태양열 막는 기능");
//        Options options2 = Options.putOption("nav", "길안내 서비스");
//        Img img = Img.addImg("bmw-320-main", "img/bmw/320_i/bmw_320i_main.webp");
//        Car cars = Car.registrationCar("bmw-320i", 3000, "2022", "130000", false,"대구", "ELECTRIC","BMW");
//        img.setCar(cars);
//        Car findCar = carRepository.findByName("bmw-320i");
//        System.out.println("findCar = " + findCar.getImages());
//    }
//    @Test
//    public void imgList(){
//        Car car = carRepository.findById(3L).get();
//        List<Img> images = car.getImages();
//        for (Img image : images) {
//            System.out.println("image = " + image);
//        }
//    }
    @Test
    @DisplayName("메인 이미지만 가져오기")
    public void mainImageGet(){
        List<Car> all = carRepository.findAll();
    }
//    @Test
//    public void multipartFileTest() throws IOException {
//        String fileName = "testImg";
//        String contentType = "image/webp";
//        String filePath = "src/test/resources/img/testImg";
//        MockMultipartFile mockMultipartFile = getMockMultipartFile(fileName, contentType, filePath);
//    }
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
//    @Test
//    public void removeImg(){
//        Car car = carRepository.findById(511L).get();
//        String path = car.getImages().get(0).getPath();
//        String substring = path.substring(0,path.lastIndexOf("/"));
//        System.out.println("substring = " + substring);
//    }
    @Test
    public void pathTest(){
        String path = "/Users/ijunhyeong/IdeaProjects/my_cars/src/main/webapp/";
        String main = path.substring(0,path.lastIndexOf("/webapp"));
        String oneInt ="/webapp/img/cars/kia/이준형-ee486968-e905-42df-8e71-838d219320e4/이준형-main.webp";
        String substring = oneInt.substring(0, oneInt.lastIndexOf("/"));
        System.out.println("substring = " + main+substring);
    }
    @Test
    public void findPathByCarIdTest(){
        List<String> pathByCarId = imgRepository.findPathByCarId(4L);
        for (String s : pathByCarId) {
            System.out.println("s = " + s);
        }
    }
    @Test
    public void findNameMainOrSideTest(){
        String main = imgRepository.findNameMainOrSide(4L, "main");
        System.out.println("main = " + main);
    }


}