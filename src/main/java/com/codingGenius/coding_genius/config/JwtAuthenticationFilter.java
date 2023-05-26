package com.codingGenius.coding_genius.config;

import com.codingGenius.coding_genius.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class JwtAuthenticationFilter extends GenericFilterBean {


    // Request로 들어오는 Jwt Token의 유효성을 검증하는 filter를 filterChain에 등록합니다.
    @Override

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = ((HttpServletRequest) servletRequest).getHeader("Authorization");
        if (token != null && JwtUtil.validateToken(token)) {   // token 검증
            String auth_email = JwtUtil.getBody(token);    // 인증 객체 생성
            SecurityContextHolder.getContext().setAuthentication(auth_email); // SecurityContextHolder에 인증 객체 저장
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
