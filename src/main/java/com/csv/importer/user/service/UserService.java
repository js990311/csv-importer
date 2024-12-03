package com.csv.importer.user.service;

import com.csv.importer.user.dto.UserDto;
import com.csv.importer.user.entity.User;
import com.csv.importer.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public List<UserDto> findAll(){
        return userRepository.findAll().stream().map(UserDto::from).collect(Collectors.toList());
    }
}
