package com.jproject.my_cars.domain.cars.img;

import com.jproject.my_cars.domain.cars.Car;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ImgService {
    private final ImgRepository imgRepository;

    //차량등록 폼에서 넘어온 이미지 리스트를 메인 사진과 사이드 사진으로 저장
    public void uploadImg(List<MultipartFile> images, String carName, Car car, HttpServletRequest request) throws IOException {
        String path = request.getSession().getServletContext().getRealPath("/")+"img/cars";
        int findMainIndex = 0;
        String uuid = UUID.randomUUID().toString();
        for (MultipartFile image : images) {
            if(!image.isEmpty() && findMainIndex == 0){
                String origName = image.getOriginalFilename();
                // 파일 이름으로 쓸 uuid + main 표시 이름 생성
                String fileName = carName + "-main";
                log.info("fileName: " + fileName);
                // 확장자 추출(ex : .png)
                String extension = origName.substring(origName.lastIndexOf("."));
                // file name 과 확장자 결합
                String savedName = fileName + extension;
                log.info("savedName: " + savedName);
                // 파일을 불러올 때 사용할 파일 경로
                //새로운 디렉토리 생성 -- path + 제조사 + 차량종류 + 고유번호
                String newDir = path +"/"+ car.getManufacture().toLowerCase() +"/" + carName.toLowerCase() + "-" + uuid;
                Files.createDirectory(Path.of(newDir));
                log.info("newDir: " + newDir);

                //디렉토리 + 파일이름 으로 저장하기
                String savePath = newDir + "/" +savedName;
                log.info("savePath: " + savePath);
                image.transferTo(new File((savePath)));

                //db에 저장될 경로 핸들링
                String dbPath = newDir.substring(newDir.lastIndexOf("/webapp"))+ "/" +savedName;
                log.info("dbPath: " + dbPath);
                Img img = Img.addImg(fileName, dbPath);
                img.setCar(car);
                imgRepository.save(img);
                findMainIndex++;
            }else if(!image.isEmpty() && findMainIndex !=0){
                String origName = image.getOriginalFilename();
                // 파일 이름으로 쓸 uuid + main 표시 이름 생성
                String fileName = carName + "-side" + findMainIndex;
                // 확장자 추출(ex : .png)
                String extension = origName.substring(origName.lastIndexOf("."));
                // file name 과 확장자 결합
                String savedName = fileName + extension;
                // 파일을 불러올 때 사용할 파일 경로
                String newDir = path +"/"+ car.getManufacture().toLowerCase() +"/" + carName.toLowerCase() + "-" + uuid;
                String savedPath = newDir + "/" +savedName;
                image.transferTo(new File(savedPath));

                String dbPath = newDir.substring(newDir.lastIndexOf("/webapp"))+ "/" +savedName;
                log.info("dbPath: " + dbPath);
                Img img = Img.addImg(fileName, dbPath);
                img.setCar(car);
                imgRepository.save(img);
                findMainIndex++;
            }
        }
    }
    public void modifyImg(HashMap<String,MultipartFile> map, String carName, Car car,HttpServletRequest request) throws IOException {
        //해당 차량의 전체 이미지 파일 경로는 가져옴
        List<String> pathList = imgRepository.findPathByCarId(car.getId());
        //해당 차량의 디렉토리 찾기
        String str = pathList.get(0);
        map.forEach( (key, value) -> {
            try{
                changeImg(key,car.getId(),value,request);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    //치량 수정에서 이미지 바꾸기
    public void changeImg(String filter,Long id,MultipartFile image,HttpServletRequest request) throws IOException {
        List<String> pathList = imgRepository.findPathByCarId(id);
        String path = request.getSession().getServletContext().getRealPath("/");
        String changePath = path.substring(0,path.lastIndexOf("/webapp"));
        String mainImgPath = changePath + pathList.stream().filter(s -> s.contains(filter)).findFirst().get();
        System.out.println("mainImgPath = " + mainImgPath);
        Files.delete(Path.of(mainImgPath));
        image.transferTo(new File(mainImgPath));
    }
    @Transactional
    //이미지 삭제
    public void removeImgDir(Car car,HttpServletRequest request) throws IOException {
        String path = request.getSession().getServletContext().getRealPath("/");
        String changePath = path.substring(0,path.lastIndexOf("/webapp"));
        String oneImg = car.getImages().get(0).getPath();
        String realDirPath = changePath+oneImg.substring(0, oneImg.lastIndexOf("/"));
        car.getImages().stream().forEach(
                c -> {
                    String imgPath = changePath + c.getPath();
                    try {
                        Files.delete(Path.of(imgPath));
                    } catch (IOException e) {
                        e.getMessage();
                        throw new RuntimeException(e);
                    }
                }
        );
        Files.delete(Path.of(realDirPath));
    }
}
