package com.sha.spring_boot_device_seller.repository.projection;

import com.sha.spring_boot_device_seller.model.DeviceType;

import java.time.LocalDateTime;

public interface PurchaseItem {
    String getName();
    DeviceType getType();
    Double getPrice();
    String getColor();
    LocalDateTime getPurchaseTime();

}
