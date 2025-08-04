package com.sha.spring_boot_device_seller.service;

import com.sha.spring_boot_device_seller.model.Role;
import com.sha.spring_boot_device_seller.model.User;

import java.util.Optional;

public interface UserService {
    //yeni bir kullanıcı oluşturmak için oluşturma işlemini burada service de yapıyoruz bilgiyi kaydetme işini repository de
    User saveUser(User user);// diyorum ki User modelinden User objesi oluşutr

    Optional<User> findByUsername(String username);

    /*✅ findByUsername hazırda olan bir metod mu?
        JpaRepository'nin içinde bu method yoktur, ama:
        - Spring Data JPA, method adından (findBy+AlanAdı) ne yapmak istediğini anlayıp otomatik olarak uygular.
        - username alanı senin User entity'nde varsa, Spring bunu SQL'e dönüştürüp query’yi kendisi yazar.
        Yani findByUsername bir “hazır” method değil — ama Spring onu otomatik üretir çünkü adlandırma kurallarına uygundur.
        */
    void changeRole(Role newRole, String username);
}
