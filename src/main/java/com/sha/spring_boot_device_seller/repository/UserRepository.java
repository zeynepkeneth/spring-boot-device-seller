package com.sha.spring_boot_device_seller.repository;

import com.sha.spring_boot_device_seller.model.Role;
import com.sha.spring_boot_device_seller.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
//with jpa repository spring will handle basic CRUD operation automaticly

public interface UserRepository extends JpaRepository<User, Long> {

    //user tablosundaki username e göre kullanıcı arar
    //Optional : kullanıcı varsa döner yoksa optional döner null chekc için kolaylık sağlar
    Optional<User> findByUsername(String username);

    @Modifying//sorgunun veri değiştirdiğini belirtir (UPDATE, DELETE).
    @Query("update User set role=:role where username= :username")//manuel JPQL sorgusudur.
    void updateUserRole(@Param("username")String username, @Param("role")Role role);
    //- @Param: parametrelerin JPQL sorgusuyla eşleştirilmesini sağlar.

}
