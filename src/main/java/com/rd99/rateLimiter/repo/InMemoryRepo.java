package com.rd99.rateLimiter.repo;

import com.rd99.rateLimiter.Token;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.util.HashMap;
import java.util.Map;

public class InMemoryRepo {
    public SecretKey key;
    public Map<Integer, Token> userTokens ;
    public InMemoryRepo(){
        this.userTokens = new HashMap<>();
        key = Jwts.SIG.HS256.key().build();
    }

}
