package com.jproject.my_cars.domain.cars.option;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Entity
public class Options {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OPTIONS_ID")
    private Long id;
    @NotNull
    //옵션 이름
    private String name;
    @NotNull
    @Column(name = "description")
    //옵션 설명
    private String description;

    //옵션 추가
    public static Options putOption(String name, String description){
        Options options = new Options();
        options.name = name;
        options.description = description;
        return options;
    }

    @Override
    public String toString() {
        return "Options{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
