package com.codingGenius.coding_genius.utils;

import com.codingGenius.coding_genius.config.JwtConfig;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

//import static com.codingGenius.coding_genius.config.JwtConfig.SECRET_KEY;

@Slf4j
@RequiredArgsConstructor
public class JwtUtil {

    private static final  String SECRET_KEY = "236979CB6F1AD6B6A6184A31E6BE37DB3818CC36871E26235DD67DCFE4041492";
    JwtConfig jwtConfig;

    //token 유효성 검증
    public static Boolean validateToken(String token) {
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
    public static String getBody(String accessToken) {
        try {
            validateToken(accessToken);
            return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(accessToken).getBody().getSubject();
        } catch (ExpiredJwtException e) {
            return "expired token";
        }
    }
}
