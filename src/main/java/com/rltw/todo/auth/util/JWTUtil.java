package com.rltw.todo.auth.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import org.springframework.stereotype.Component;

@Component
public class JWTUtil {
    public String createToken(long userId){
        return JWT.create().withClaim("userId",userId).sign(Algorithm.HMAC256("test secret"));
    }

    public long getUserIdFromToken(String token){
        return JWT.decode(token).getClaim("userId").asLong();
    }

    public boolean doesAuthHeaderHaveJWT(String authorizationHeader){
        if(authorizationHeader.startsWith("Bearer ")){
            String token = this.extractTokenFromAuthHeader(authorizationHeader);

            try{
                var decodedJWT = JWT.decode(token);
                var test = decodedJWT.getClaim("userId").asLong();
                return test != null;
            }
            catch(JWTDecodeException ex) {
                return false;
            }
        }

        return false;
    }

    String extractTokenFromAuthHeader(String authorizationHeader){
        return authorizationHeader.substring(7);
    }
}
