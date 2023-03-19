package com.jproject.my_cars.domain.cars.img;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;
import com.jproject.my_cars.domain.cars.Car;
import com.jproject.my_cars.domain.cars.CarRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class S3FileUploadService {
    // 버킷 이름 동적 할당
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;
    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;
    @Value("${cloud.aws.region.static}")
    private String region;

    private final ImgRepository imgRepository;
    private AmazonS3 amazonS3Client;

    @PostConstruct
    public void setS3Client() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        amazonS3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(this.region)
                .build();
    }

    @Transactional
    public void upload(List<MultipartFile> images, String carName, Car car) throws IOException {
        String uuid = getUuid();
        int findMainIndex = 0;
        for (MultipartFile img : images) {
            if(!images.isEmpty() && findMainIndex == 0){
                try{
                    String originalFilename = img.getOriginalFilename();
                    final String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
                    final String saveFileName = carName + "-main"+ uuid + ext;
                    File file = new File(System.getProperty("user.dir")+saveFileName);
                    img.transferTo(file);
                    uploadOnS3(saveFileName,file);
                    String dbPath = amazonS3Client.getUrl(bucket,saveFileName).toString();
                    log.info("dbPath" + dbPath);
                    Img entity = Img.addImg(saveFileName, dbPath);
                    entity.setCar(car);
                    imgRepository.save(entity);
                    file.delete();
                    findMainIndex++;
                }catch (StringIndexOutOfBoundsException e) {
                    e.getMessage();
                }
            }else if(!images.isEmpty() && findMainIndex !=0){
                try{
                    String originalFilename = img.getOriginalFilename();
                    final String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
                    final String saveFileName = carName + "-side"+ findMainIndex + uuid + ext;
                    File file = new File(System.getProperty("user.dir")+saveFileName);
                    img.transferTo(file);
                    uploadOnS3(saveFileName,file);
                    String dbPath = amazonS3Client.getUrl(bucket,saveFileName).toString();
                    log.info("dbPath" + dbPath);
                    Img entity = Img.addImg(saveFileName, dbPath);
                    entity.setCar(car);
                    imgRepository.save(entity);
                    file.delete();
                    findMainIndex++;
                }catch (Exception e){
                    e.getMessage();
                }
            }
        }
    }
    public void removeImg(Car car){
        for (Img image : car.getImages()) {
            System.out.println("image.getName() = " + image.getName());
        }
        car.getImages().forEach(
                c -> {
                    amazonS3Client.deleteObject(bucket,c.getName());
                    log.info("img name:"+c.getName());
                });
        car.getImages().clear();
    }
    @Transactional
    public void modifyImg(HashMap<String,MultipartFile> map,String carName,Car car)throws IOException{
        try {
            map.forEach(
                            (key, value) -> {
                                try {
                                    final String likeFileName = imgRepository.findNameMainOrSide(car.getId(), key);
                                    File file = new File(System.getProperty("user.dir")+likeFileName);
                                    value.transferTo(file);
                                    uploadOnS3(likeFileName,file);
                                    file.delete();
                                } catch (IOException e) {
                                    e.getMessage();
                                    throw new RuntimeException(e);
                                }
                            }
                        );

        }catch (Exception e){
            e.getMessage();
        }

    }
    private String getUuid(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
    private void uploadOnS3(final String findName,final File file) {
        final TransferManager transferManager = new TransferManager(this.amazonS3Client);
        final PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, findName, file);
        Upload upload = transferManager.upload(putObjectRequest);
        try{
            upload.waitForCompletion();
        }catch (AmazonClientException amazonClientException){
            log.error(amazonClientException.getMessage());
        }catch (InterruptedException e) {
            log.error(e.getMessage());
        }
    }

}
