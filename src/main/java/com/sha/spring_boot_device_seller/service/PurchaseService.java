package com.sha.spring_boot_device_seller.service;

import com.sha.spring_boot_device_seller.model.Purchase;
import com.sha.spring_boot_device_seller.repository.projection.PurchaseItem;

import java.util.List;

public interface PurchaseService {

    List<PurchaseItem> findAllPurchasesOfUser(Long userId);

    Purchase savePurchaase(Purchase purchase);
}
