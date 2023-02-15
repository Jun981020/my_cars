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

        if(this.getMain().getOriginalFilename() != null){
            setList("main",getMain(),list);
        }
        if(this.getSide1().getOriginalFilename() != null){
            setList("side1",getSide1(),list);
        }
        if(this.getSide2().getOriginalFilename() != null){
            setList("side2",getSide2(),list);
        }
        if(this.getSide3().getOriginalFilename() != null){
            setList("side3",getSide3(),list);
        }
        if(this.getSide4().getOriginalFilename() != null){
            setList("side4",getSide4(),list);
        }
        if(this.getSide5().getOriginalFilename() != null){
            setList("side4",getSide5(),list);
        }
        if(this.getSide6() != null && this.getSide6().getOriginalFilename() != null){
            setList("side6",getSide6(),list);
        }
        return list;
    }
    public void setList(String filterName,MultipartFile file,List<HashMap<String, MultipartFile>> list){
        HashMap<String,MultipartFile> map = new HashMap<>();
        map.put(filterName,file);
        list.add(map);
    }


}
