package com.rd99.rateLimiter;

import com.rd99.rateLimiter.constants.TokenConstants;
import io.jsonwebtoken.Jwts;
import org.json.JSONObject;

import javax.crypto.SecretKey;
import java.sql.Time;
import java.time.LocalDateTime;

public class Token {
    private Integer userId;
    public LocalDateTime tokenAllotedTime;
    public Integer remainCalls;
    public String tokenStr;
    public Token(Integer currUserId , SecretKey key){
        userId = currUserId;
        tokenAllotedTime = LocalDateTime.now();
        remainCalls = TokenConstants.noOfTries-1;
        tokenStr = createJwt(key);
        System.out.println(tokenStr);
    }

    public String createJwt(SecretKey key){
        JSONObject token = new JSONObject();
        token.put("user" , userId);
        token.put("time" , System.currentTimeMillis());
        return Jwts.builder().subject(token.toString()).signWith(key).compact();
    }
}
