package com.jproject.my_cars.domain.cars.img;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ImgService {
    private final ImgRepository imgRepository;
    @Value("file.dir")
    private String path;

    //차량등록 폼에서 넘어온 이미지 리스트를 메인 사진과 사이드 사진으로 저장
    public void uploadImg(List<MultipartFile> images,String carName) throws IOException {
        int findMainIndex = 0;
        for (MultipartFile image : images) {
            if(!image.isEmpty() && findMainIndex ==0){
                String origName = image.getOriginalFilename();
                // 파일 이름으로 쓸 uuid + main 표시 이름 생성
                String fileName = UUID.randomUUID() + carName + "main";
                // 확장자 추출(ex : .png)
                String extension = origName.substring(origName.lastIndexOf("."));
                // file name 과 확장자 결합
                String savedName = fileName + extension;
                // 파일을 불러올 때 사용할 파일 경로
                String savedPath = path + savedName;
                image.transferTo(new File(savedPath));
                imgRepository.save(Img.addImg(fileName,savedPath));
                findMainIndex++;
            }else if(!image.isEmpty() && findMainIndex !=0){
                String origName = image.getOriginalFilename();
                // 파일 이름으로 쓸 uuid + main 표시 이름 생성
                String fileName = UUID.randomUUID() + carName + "side" + findMainIndex;
                // 확장자 추출(ex : .png)
                String extension = origName.substring(origName.lastIndexOf("."));
                // file name 과 확장자 결합
                String savedName = fileName + extension;
                // 파일을 불러올 때 사용할 파일 경로
                String savedPath = path + savedName;
                image.transferTo(new File(savedPath));
                imgRepository.save(Img.addImg(fileName,savedPath));
                findMainIndex++;
            }
        }
    }

}
