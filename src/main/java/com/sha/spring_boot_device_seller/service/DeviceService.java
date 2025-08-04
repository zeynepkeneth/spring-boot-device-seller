package com.sha.spring_boot_device_seller.service;

import com.sha.spring_boot_device_seller.model.Device;

import java.util.List;

public interface DeviceService {
    Device saveDevice(Device device);

    void  deleteDevice(Long id);

    List<Device> findAllDevices();

    //bunları otomatik ekliyorrrr

}
