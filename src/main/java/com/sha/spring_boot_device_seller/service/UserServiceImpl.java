package com.sha.spring_boot_device_seller.service;

import com.sha.spring_boot_device_seller.model.Role;
import com.sha.spring_boot_device_seller.model.User;
import com.sha.spring_boot_device_seller.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    //veri erişimini repository üzerinden yaptığımız için repository objesi oluşturuyoruz.
    //- Böylece userRepository üzerinden veritabanı işlemleri yapabilirsin.
    @Autowired
    private UserRepository userRepository;

    //yeni bir kullanıcı oluşturmak için oluşturma işlemini burada service de yapıyoruz bilgiyi kaydetme işini repository de
    @Override
    public User saveUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        user.setCreateTime(LocalDateTime.now());
        return userRepository.save(user);// save operation repositorde
    }
    @Override
    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }
    /*✅ findByUsername hazırda olan bir metod mu?
    JpaRepository'nin içinde bu method yoktur, ama:
    - Spring Data JPA, method adından (findBy+AlanAdı) ne yapmak istediğini anlayıp otomatik olarak uygular.
    - username alanı senin User entity'nde varsa, Spring bunu SQL'e dönüştürüp query’yi kendisi yazar.
    Yani findByUsername bir “hazır” method değil — ama Spring onu otomatik üretir çünkü adlandırma kurallarına uygundur.
    */
    @Override
    @Transactional//transaction is required when executing an update/delete query
    public void changeRole(Role newRole, String username){
        userRepository.updateUserRole(username,newRole);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;
}
