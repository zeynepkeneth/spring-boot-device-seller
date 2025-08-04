package com.sha.spring_boot_device_seller.service;

import com.sha.spring_boot_device_seller.security.UserPrincipal;
import com.sha.spring_boot_device_seller.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import com.sha.spring_boot_device_seller.model.User;
import org.springframework.stereotype.Service;

//bu sınıf ,kullanıcı giriş yaptığında (login) kimlik doğrulamasını yapar ve JWT üretir
@Service
public class AuthenticationServiceImpl implements AuthenticationService {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User signInAndReturnJWT(User signInRequest){

        //authenticaitonManager:Spring Securitynin kendi sağladığı doğrulayıcıdır.
        //authenticate: bu token ı alır ve userDetailsSrvice(CustomUserDetailsService)kullanarak kullanıcıyı veritabınından kontrol eder.
        Authentication authentication = authenticationManager.authenticate(
                //UsernamePasswordAuthenticationToken : kullanıcının girdiği password ve username ile oluşturulan bir tokendır
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(),signInRequest.getPassword())
        );

        //bu işlem başarılıysa sistem bize kullanıcıya ait bilgileri UserPrincipal olarak döner.
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        //jtwProviderImpl kullanarak bir JWT token üretilir.
        //Bu token ileri aşamalarda kullanıcının kimliğini taşır.
        String jwt=jwtProvider.generateToken(userPrincipal);

        //kullanıcı userPrincipaldan alıyoruz
        User signInUser=userPrincipal.getUser();
        //aldığımız kullanıcıya hazırladığımız token ı döndürüyoruz.
        signInUser.setToken(jwt);

        return signInUser;


    }
}
