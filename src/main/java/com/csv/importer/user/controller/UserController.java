package com.csv.importer.user.controller;

import com.csv.importer.user.dto.UserDto;
import com.csv.importer.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/user")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDto> findAll(){
        return userService.findAll();
    }
}
