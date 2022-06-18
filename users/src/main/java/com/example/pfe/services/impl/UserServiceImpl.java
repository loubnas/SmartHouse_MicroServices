package com.example.pfe.services.impl;

import com.example.pfe.dto.DeviceDto;
import com.example.pfe.dto.UserDto;
import com.example.pfe.dto.services.IMapClassWithDto;
import com.example.pfe.entities.UserEntity;
import com.example.pfe.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.pfe.repositories.userRepository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    userRepository userRepository;
    @Autowired
    IMapClassWithDto<UserEntity, UserDto> userMapping;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    RestTemplate restTemplate;

    private final WebClient.Builder webClient;

    @Override
    public List<UserDto> getUsers() {
        List<UserEntity> users = userRepository.findAll();
        return userMapping.convertListToListDto(users,UserDto.class);
    }

    @Override
    public UserDto getById(String id) {
        UserEntity user = userRepository.findUserEntityById(id);
        UserDto userDto =userMapping.convertToDto(user, UserDto.class);
        //////with restTemplate
//
//       DeviceDto deviceDto=
//               restTemplate.getForObject("http://DEVICES-SERVICE/device/byname/TVred",DeviceDto.class);

       ////with web client
//
        DeviceDto deviceDto=
                webClient.build().get().uri("http://DEVICES-SERVICE/device/byname/TVred")
                                .retrieve().bodyToMono(DeviceDto.class)
                                .block();


        userDto.setDevice(deviceDto);
        return userDto;
    }

    @Override
    public UserDto addUser(UserDto user) {
        String pass = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(pass);
        UserEntity userEntity = userMapping.convertToEntity(user, UserEntity.class);

        userEntity = userRepository.save(userEntity);

        return userMapping.convertToDto(userEntity, UserDto.class);
    }

    @Override
    public UserDto getUser(String email) {
        System.out.println("userDto.getEmail()\n");
        System.out.println(email);


        UserEntity currentUser = userRepository.findByEmail(email);

        return userMapping.convertToDto(currentUser, UserDto.class);
    }
    @Override
    public void delete(String id) {
        UserEntity user = userRepository.findUserEntityById(id);

        if(user == null) throw new RuntimeException("user not found");

        userRepository.deleteById(id);

    }


    /////////////////////////////////////////jwt

}