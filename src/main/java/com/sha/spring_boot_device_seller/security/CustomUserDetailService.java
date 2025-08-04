package com.sha.spring_boot_device_seller.security;

import com.sha.spring_boot_device_seller.model.User;
import com.sha.spring_boot_device_seller.service.UserService;
import com.sha.spring_boot_device_seller.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

/*- CustomUserDetailsService gerçekten de Spring Security'nin
authentication sürecinde kullanıcı bilgilerini veritabanından
almak için kullanılan bir service'dir. Özellikle
loadUserByUsername metodunu override ederek,
kullanıcı adıyla veritabanında arama yapar ve
 yetkili bir UserDetails objesi döndürür.
 */
@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username)
                .orElseThrow(() ->new UsernameNotFoundException(username));

        Set<GrantedAuthority> authorities = Set.of(SecurityUtils.convertToAuthority(user.getRole().name()));
                return UserPrincipal.builder()
                        .user(user)
                        .id(user.getId())
                        .password(user.getPassword())
                        .username(user.getUsername())
                        .authorities(authorities)
                        .build();
    }
}
