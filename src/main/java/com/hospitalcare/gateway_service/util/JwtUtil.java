package com.hospitalcare.gateway_service.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    public static final String SECRET = "BwL26QVI4U4r7/LPPpR1dLc43jhKiAyfV2K20Yehd/4=";

    public void validateToken(final String token) {
        Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token);
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}