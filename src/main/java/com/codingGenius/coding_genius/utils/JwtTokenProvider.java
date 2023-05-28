package com.codingGenius.coding_genius.utils;

import io.jsonwebtoken.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Date;

//import static com.codingGenius.coding_genius.config.JwtConfig.SECRET_KEY;

@Log4j2
@Component
public class JwtTokenProvider {

    private static final Long ACCESS_TOKEN_EXPIRE_LENGTH = 1000L * 60 * 60 * 24 * 30; //30DAY
    private static final Long REFRESH_TOKEN_EXPIRE_LENGTH = 1000L * 60 * 60 * 24 * 14; //2WEEK

    private static String SECRET_KEY = "236979CB6F1AD6B6A6184A31E6BE37DB3818CC36871E26235DD67DCFE4041492";


    //accessToken 생성
    public static String createAccessToken(String email) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + ACCESS_TOKEN_EXPIRE_LENGTH);

        log.info("create access token - tokenProvider : " + email);

        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .setSubject("access-token")
                .setSubject(email)
                .setIssuedAt(now) //token 발행 시간
                .setExpiration(validity)
                .compact();
    }

    //refreshToken 생성
    public static void createRefreshToken(String email) {
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
}
