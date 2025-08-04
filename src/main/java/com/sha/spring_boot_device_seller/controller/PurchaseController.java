package com.sha.spring_boot_device_seller.controller;

import com.sha.spring_boot_device_seller.model.Purchase;
import com.sha.spring_boot_device_seller.security.UserPrincipal;
import com.sha.spring_boot_device_seller.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    //in restcotroller we will define every method with response entity capsulation
    @PostMapping
    public ResponseEntity<?>savePurchase(@RequestBody Purchase purchase) {
        return new ResponseEntity<>(purchaseService.savePurchaase(purchase),HttpStatus.CREATED);
    }

    /*burada authenticates user a ulaşmamız lazım peki nasıl?
    * 1.SecurityContextHolder.getContext().getAuthentication().getPrincipal()
    * 2.HttpServletRequest.getPrincipal()
    * 3.@AuthenticationPrincipal */
    @GetMapping
    public ResponseEntity<?> getAllPurchases(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return new ResponseEntity<>(purchaseService.findAllPurchasesOfUser(userPrincipal.getId()),HttpStatus.OK);
    }

}
