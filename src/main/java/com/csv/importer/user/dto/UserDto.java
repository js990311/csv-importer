package com.csv.importer.user.dto;

import com.csv.importer.user.entity.User;
import lombok.Getter;

@Getter
public class UserDto {
    private String name;
    private String email;
    private Integer age;

    public UserDto(String name, String email, Integer age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public static UserDto from(User user){
        return new UserDto(user.getName(), user.getEmail(), user.getAge());
    }
}
