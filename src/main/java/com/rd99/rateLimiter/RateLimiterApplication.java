package com.rd99.rateLimiter;

import com.rd99.rateLimiter.repo.InMemoryRepo;
import com.rd99.rateLimiter.service.RateLimiterService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Base64;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@SpringBootApplication

public class RateLimiterApplication {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		SpringApplication.run(RateLimiterApplication.class, args);

		InMemoryRepo inMemoryRepo = new InMemoryRepo();
		RateLimiterService rateLimiterService = new RateLimiterService(inMemoryRepo);
		rateLimiterService.checkAccess(1);
		rateLimiterService.checkAccess(1);
		rateLimiterService.checkAccess(1);
		rateLimiterService.checkAccess(1);
		rateLimiterService.checkAccess(1);
		rateLimiterService.checkAccess(1);
		rateLimiterService.checkAccess(1);
		rateLimiterService.checkAccess(2);
		rateLimiterService.checkAccess(2);



		CompletableFuture<Boolean> futureReq = CompletableFuture.supplyAsync(() -> {
			try{
				Thread.sleep(11000);
				rateLimiterService.checkAccess(1);
				rateLimiterService.checkAccess(1);
				rateLimiterService.checkAccess(1);
				rateLimiterService.checkAccess(1);
				rateLimiterService.checkAccess(1);
				rateLimiterService.checkAccess(1);
				rateLimiterService.checkAccess(1);
			}catch (InterruptedException err){
				throw new IllegalStateException(err);
			}
			return true;

		});

		Boolean res = futureReq.get();
	}

}
