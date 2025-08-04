package com.sha.spring_boot_device_seller.controller;

import com.sha.spring_boot_device_seller.model.Device;
import com.sha.spring_boot_device_seller.service.DeviceService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/device")//pre-path
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @PostMapping
    public ResponseEntity<?> saveDevice(@RequestBody Device device) {
        return new ResponseEntity<>(deviceService.saveDevice(device), HttpStatus.CREATED);
    }

    @DeleteMapping("{deviceId}")
    public ResponseEntity<?> deleteDevice(@PathVariable Long deviceId) {
        deviceService.deleteDevice(deviceId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllDevices() {
        return new ResponseEntity<>(deviceService.findAllDevices(),HttpStatus.OK);
    }
}
