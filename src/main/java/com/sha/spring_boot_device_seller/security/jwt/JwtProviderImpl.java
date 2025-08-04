package com.sha.spring_boot_device_seller.security.jwt;

import com.sha.spring_boot_device_seller.security.UserPrincipal;
import com.sha.spring_boot_device_seller.utils.SecurityUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;


//token oluşturma ve kontrol etme işleminden sorumlu sınıftır
@Component
public class JwtProviderImpl implements JwtProvider {

    @Value("${app.jwt.secret}")
    private String JWT_SECRET;

    @Value("${app.jwt.expiration-in.ms}")
    private  Long JWT_EXPIRATION_IN_MS;


    @Override
    public String generateToken(UserPrincipal auth) {

        /*
        🔸 Burada stream() → listedeki elemanları işler
        🔸 map() → tüm isimleri büyük harfe çevirir
        🔸 join() → bunları tek bir string’e çevirir
        Yani yazıya çeviren şey doğrudan stream() değil,
         işlemin sonunda yaptığın collect() ve join() gibi adımlar.
        Kullanıcının rollerini (ROLE_USER, ROLE_ADMIN) virgülle ayırıp yazıya çeviriyor.
         */
        String authorities=auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        //Token’ı imzalamak için bir gizli anahtar oluşturuluyor
        // (bu key .properties dosyanda app.jwt.secret olarak tanımlanmıştır).
        Key key= Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));

        //bu satırlar token ı inşa eder
        return Jwts.builder()
                .setSubject(auth.getUsername())
                .claim("roles",authorities)
                .claim("userId",auth.getId())
                .setExpiration(new Date(System.currentTimeMillis()+ JWT_EXPIRATION_IN_MS))
                .signWith(key, SignatureAlgorithm.HS512)//token için bir imza anahtar atıyoruz
                .compact();
        //compact() dediğimiz zaman JWT string ine dönüşür

    }

    /*her requestte çağrılır, token dan kullanıcı bilgilerini okur
    * ve bir authentication nesnesi üretir.*/
    @Override
    public Authentication getAuthentication(HttpServletRequest request) {
        Claims claims =extractClaims(request);
        if (claims == null) {
            System.out.println("❌ JWT claims null — token alınamadı veya geçersiz");
            return null;
        }
        //tokendan kullanıcı adı alınır
        String username = claims.getSubject();
        System.out.println("✅ Token ile bulunan kullanıcı: " + username);
        Long userId = claims.get("userId", Long.class);

        Set<GrantedAuthority> authorities = Arrays.stream(claims.get("roles").toString().split(","))
                .map(SecurityUtils::convertToAuthority)
                .collect(Collectors.toSet());
        UserDetails userDetails=UserPrincipal.builder()
                .username(username)
                .authorities(authorities)
                .id(userId)
                .build();
        if (username==null) {
            return null;
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
    }
    @Override
    public boolean isTokenValid(HttpServletRequest request) {
        Claims claims =extractClaims(request);
        if(claims == null) {
            return false;
        }
        if(claims.getExpiration().before(new Date())) {
            return false;
        }
        return true;
    }
    private Claims extractClaims(HttpServletRequest request) {
        String token = SecurityUtils.extractAuthTokenFromRequest(request);
        if (token == null) {
            return null;
        }

        Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims;
    }





}
