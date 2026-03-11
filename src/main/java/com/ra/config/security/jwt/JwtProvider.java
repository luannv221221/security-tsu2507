package com.ra.config.security.jwt;

import com.ra.config.security.UserPrinciple;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {
    @Value("${expired}")
    private Long EXPIRED_TIME;
    @Value("${secret_key}")
    private String SECRET_KEY;
    private Logger logger = LoggerFactory.getLogger(JwtProvider.class);
    public String generateToken(UserPrinciple userPrinciple){
        // tao thoi gian song cua token
        Date dateExpiration = new Date(new Date().getTime() + EXPIRED_TIME);
        // tao va tra ve token
        return Jwts.builder().setSubject(userPrinciple.getUsername())
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY).setExpiration(dateExpiration).compact();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (Exception exception){
            logger.error(exception.getMessage());
        }
        return false;
    }

    public String getUsernameFromToken(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }
}
