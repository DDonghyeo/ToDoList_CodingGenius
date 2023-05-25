package com.codingGenius.coding_genius.service;

import io.jsonwebtoken.Jwts;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import static com.codingGenius.coding_genius.config.JwtConfig.SECRET_KEY;

@Log4j2
@Service
public class JwtService {

    public static Long getUserIdx (String token) {
        return Long.valueOf(Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody().getSubject());
    }
}
