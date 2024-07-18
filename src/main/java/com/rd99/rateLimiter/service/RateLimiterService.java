package com.rd99.rateLimiter.service;

import com.rd99.rateLimiter.RateLimiterApplication;
import com.rd99.rateLimiter.Token;
import com.rd99.rateLimiter.constants.TokenConstants;
import com.rd99.rateLimiter.repo.InMemoryRepo;
import com.rd99.rateLimiter.repo.RedisRepo;

import java.time.LocalDateTime;

public class RateLimiterService {
    //RedisRepo redisRepo;
    InMemoryRepo inMemoryRepo;

    public RateLimiterService(InMemoryRepo inMemoryRepo){
        this.inMemoryRepo = inMemoryRepo;
    }
    public boolean checkAccess(Integer userId){
        if(!inMemoryRepo.userTokens.containsKey(userId)){
            inMemoryRepo.userTokens.put(userId,new Token(userId , inMemoryRepo.key));
            System.out.println(true);
            return true;
        }
        Token currToken = inMemoryRepo.userTokens.get(userId);
        if(currToken.remainCalls > 0){
            currToken.remainCalls -= 1;
            inMemoryRepo.userTokens.put(userId,currToken);
            System.out.println(true);
            return true;
        }
        else{
            if(currToken.tokenAllotedTime.plusSeconds(TokenConstants.timeWindow).isAfter(LocalDateTime.now())){
                System.out.println(false);
                return false;
            }
            else{
                inMemoryRepo.userTokens.put(userId,new Token(userId,inMemoryRepo.key));
                System.out.println(true);
                return true;
            }
        }

    }


}
