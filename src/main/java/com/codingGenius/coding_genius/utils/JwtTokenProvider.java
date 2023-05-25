package com.codingGenius.coding_genius.utils;

import io.jsonwebtoken.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.codingGenius.coding_genius.config.JwtConfig.SECRET_KEY;

@Log4j2
@Component
public class JwtTokenProvider {

    private final Long ACCESS_TOKEN_EXPIRE_LENGTH = 1000L * 60 * 60 * 24 * 30; //30DAY
    private final Long REFRESH_TOKEN_EXPIRE_LENGTH = 1000L * 60 * 60 * 24 * 14; //2WEEK


    //accessToken 생성
    public String createAccessToken(String email) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + ACCESS_TOKEN_EXPIRE_LENGTH);

        log.info("create access token - tokenProvider : " + email);

        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .setSubject("refresh-token")
                .claim("role", "USER")
                .setPayload(email)
                .setIssuedAt(now) //token 발행 시간
                .setExpiration(validity)
                .compact();
    }

    //refreshToken 생성
    public void createRefreshToken(String email) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + REFRESH_TOKEN_EXPIRE_LENGTH);

        String refreshToken = Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .setSubject("refresh-token")
                .setIssuedAt(now)
                .setPayload(email)
                .claim("role", "USER")
                .setExpiration(validity)
                .compact();
    }

    //token 유효성 검증
    public Boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        } catch (NullPointerException ex){
            log.error("JWT is empty");
        } catch (IllegalStateException e) {
            log.info("JWT is illegal");
        }
        return false;
    }

    // Jwt Parser
    private String getBody(String accessToken) {
        try {
            return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(accessToken).getBody().toString();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}
