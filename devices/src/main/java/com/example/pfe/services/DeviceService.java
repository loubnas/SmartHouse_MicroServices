package com.example.pfe.services;

import com.example.pfe.dto.DeviceDto;

import java.util.List;

public interface DeviceService {
    List<DeviceDto> getALL();
    DeviceDto getById(String id);
    DeviceDto getByName(String name);
    DeviceDto create(DeviceDto device);
    DeviceDto update(DeviceDto deviceDto, String id);
    DeviceDto updateStatus(String status, String id);
    boolean delete(String id);





}
