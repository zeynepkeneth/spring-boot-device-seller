package com.sha.spring_boot_device_seller.service;

import com.sha.spring_boot_device_seller.model.Purchase;
import com.sha.spring_boot_device_seller.repository.PurchaseRepository;
import com.sha.spring_boot_device_seller.repository.projection.PurchaseItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PurchaseServiceImp implements PurchaseService {

    public PurchaseServiceImp(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    @Autowired
    public PurchaseRepository purchaseRepository;

    @Override
    public List<PurchaseItem> findAllPurchasesOfUser(Long userId){
         return purchaseRepository.findAllPurchasesOfUser(userId);
    }


    @Override
    public Purchase savePurchaase(Purchase purchase){
        purchase.setPurchaseTime(LocalDateTime.now());
        return purchaseRepository.save(purchase);
    }


}
