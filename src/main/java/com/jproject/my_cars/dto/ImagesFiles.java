package com.jproject.my_cars.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
@Data
public class ImagesFiles {
    private MultipartFile main;
    private MultipartFile side1;
    private MultipartFile side2;
    private MultipartFile side3;
    private MultipartFile side4;
    private MultipartFile side5;
    private MultipartFile side6;

    public HashMap<String,MultipartFile> setImageList(){
        HashMap<String,MultipartFile> map = new HashMap<>();
        if(!getMain().getOriginalFilename().isEmpty()){
            setList("main",getMain(),map);
        }
        if(!getSide1().getOriginalFilename().isEmpty()){
            setList("side1",getSide1(),map);
        }
        if(!getSide2().getOriginalFilename().isEmpty()){
            setList("side2",getSide2(),map);
        }
        if(!getSide3().getOriginalFilename().isEmpty()){
            setList("side3",getSide3(),map);
        }
        if(!getSide4().getOriginalFilename().isEmpty()){
            setList("side4",getSide4(),map);
        }
        if(!getSide5().getOriginalFilename().isEmpty()) {
            setList("side4",getSide5(),map);
        }
        if(getSide6() != null && !(getSide6().getOriginalFilename().isEmpty())){
            setList("side6",getSide6(),map);
        }
        return map;
    }
    public void setList(String filterName,MultipartFile file,HashMap<String,MultipartFile> map){
        map.put(filterName,file);
    }


}
