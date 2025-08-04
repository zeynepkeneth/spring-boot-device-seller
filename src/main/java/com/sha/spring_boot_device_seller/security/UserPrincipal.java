package com.sha.spring_boot_device_seller.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import com.sha.spring_boot_device_seller.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/*- UserPrincipal sınıfı, UserDetails arayüzünü implement
ettiği için kimlik doğrulama başarılı olduğunda
Spring Security tarafından döndürülen objedir.
İçinde kullanıcı adı, parola, yetkiler gibi kimlik
bilgilerini barındırır.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPrincipal implements UserDetails {

    private  Long id;
    private  String username;
    transient private String password;//dont show up on a serialized places.
    transient private User user;//user for login operation,dont use in JWT
    private Set<GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
