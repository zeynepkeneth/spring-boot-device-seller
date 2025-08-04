package com.sha.spring_boot_device_seller.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
//her bir HTTP isteğinde bu class çalışır , Spring Security filtre zincirine eklenen bir sınıf
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException
    {
        Authentication authentication = jwtProvider.getAuthentication(httpServletRequest);

        if (authentication != null && jwtProvider.isTokenValid(httpServletRequest))
        {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}


/*
kendime not burada  public endpoint’leri JWT doğrulamasından muaf tutmak. Gibi bir hata
yapmamdan ötürü sign in signup gibi open endpointlerde bile token istediği için
403 forbidden hatası aldım , bunun için buraya filtre eklemem lazım.

 */