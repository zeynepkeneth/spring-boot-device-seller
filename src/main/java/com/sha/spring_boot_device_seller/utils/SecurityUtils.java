package com.sha.spring_boot_device_seller.utils;

import ch.qos.logback.core.util.StringUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.StringUtils;

public class SecurityUtils {

    /*- Bu sabit, Spring Security'nin gerektirdiği rol ön ekini (ROLE_) temsil eder.
    - Spring Security genelde rolleri ROLE_ADMIN, ROLE_USER gibi tanımlar. Bu ön ek olmazsa rol tanıma işlemi başarısız olur.
    */
    public static final String ROLE_PREFIX = "ROLE_";

    public static final String AUTH_HEADER = "authorization";
    public static final String AUTH_TOKEN_TYPE_ = "Bearer";
    public static final String AUTH_TOKEN_PREFIX = AUTH_TOKEN_TYPE_+" ";
    /*- Parametre olarak gelen role string'ini kontrol eder.
    - Eğer zaten ROLE_ ile başlıyorsa olduğu gibi alır.
    - Değilse ROLE_ ön ekini ekler.
    - Sonra bunu SimpleGrantedAuthority nesnesine çevirir.
    - Bu nesne, Spring Security'de kullanıcıya ait yetkileri temsil eder.
    */
    public static SimpleGrantedAuthority convertToAuthority(String role) {
        String formattedAuthority = role.startsWith(ROLE_PREFIX) ? role:ROLE_PREFIX+role;
        return new SimpleGrantedAuthority(formattedAuthority);
    }

    /*
    Yardımcı sınıf. Rol tanımlarını işler, token’ı HTTP isteğinden çeker.
    Token’ı HTTP Header'dan çıkarır.
     */
    public static String extractAuthTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTH_HEADER);
        System.out.println("➡️ Raw bearer token: " + bearerToken);
        if(StringUtils.hasLength(bearerToken)&& bearerToken.startsWith(AUTH_TOKEN_PREFIX)){
            System.out.println("✅ Token: " + bearerToken);
            return bearerToken.substring(7);
        }
        System.out.println("❌ Token formatı uygun değil veya eksik.");
        return null;
    }




}
