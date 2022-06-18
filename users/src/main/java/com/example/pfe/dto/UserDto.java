package com.example.pfe.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private DeviceDto device;


}
