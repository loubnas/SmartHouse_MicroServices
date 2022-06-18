package com.example.pfe.services;

import com.example.pfe.dto.DeviceDto;
import com.example.pfe.dto.UserDto;

import java.util.List;

public interface UserService   {
    List<UserDto> getUsers();
    UserDto getById(String id);
    UserDto addUser(UserDto user);
    UserDto getUser(String email);
    void delete(String id);
}
