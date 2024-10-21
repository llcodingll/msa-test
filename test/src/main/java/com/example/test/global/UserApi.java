package com.example.test.global;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

//Application Program Interface
@FeignClient("USER-SERVICE")
public interface UserApi {
    @GetMapping("/api/v1/auth/me")
    UserResponse me(@RequestHeader("Authorization") String token);
}
