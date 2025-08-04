package com.sha.spring_boot_device_seller.controller;

import com.sha.spring_boot_device_seller.model.User;
import com.sha.spring_boot_device_seller.service.AuthenticationService;
import com.sha.spring_boot_device_seller.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController//genellikle veri döndürmek için kullanılır
@RequestMapping("api/authentication")//pre-path
//Yani @RequestMapping("api/authentication") → tüm yolların başına bu eklenir.
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;//for registration method

    @PostMapping("sign-up")//api/authentication/sign-up
    public ResponseEntity<?>signUp(@RequestBody User user) {
        if(userService.findByUsername(user.getUsername()).isPresent()) {
            return new  ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(userService.saveUser(user),HttpStatus.CREATED);
    }

    @PostMapping("sign-in")
    public ResponseEntity<?> signIn(@RequestBody User user) {
        //we will return JWT as a response
        return new ResponseEntity<>(authenticationService.signInAndReturnJWT(user),HttpStatus.OK);
    }
}
