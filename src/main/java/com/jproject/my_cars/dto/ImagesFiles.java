package com.jproject.my_cars.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
public class ImagesFiles {
    private MultipartFile main;
    private MultipartFile side1;
    private MultipartFile side2;
    private MultipartFile side3;
    private MultipartFile side4;
    private MultipartFile side5;
    private MultipartFile side6;

    public List<HashMap<String,MultipartFile>> setImageList(){
        List<HashMap<String, MultipartFile>> list = new ArrayList<>();
        if(this.getMain() != null){
            HashMap<String, MultipartFile> map = new HashMap<>();
            map.put("main",getMain());
            list.add(map);
        }
        if(this.getSide1() != null){
            HashMap<String, MultipartFile> map = new HashMap<>();
            map.put("side1",getSide1());
            list.add(map);
        }
        if(this.getSide2() != null){
            HashMap<String, MultipartFile> map = new HashMap<>();
            map.put("side2",getSide2());
            list.add(map);
        }
        if(this.getSide3() != null){
            HashMap<String, MultipartFile> map = new HashMap<>();
            map.put("side3",getSide3());
            list.add(map);
        }
        if(this.getSide4() != null){
            HashMap<String, MultipartFile> map = new HashMap<>();
            map.put("side4",getSide4());
            list.add(map);
        }
        if(this.getSide5() != null){
            HashMap<String, MultipartFile> map = new HashMap<>();
            map.put("side5",getSide5());
            list.add(map);
        }
        if(this.getSide6() != null){
            HashMap<String, MultipartFile> map = new HashMap<>();
            map.put("side6",getSide6());
            list.add(map);
        }
        return list;
    }


}
