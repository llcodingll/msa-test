package com.example.test;

import com.example.test.global.UserResponse;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/api/v1/test")
    public String test() {
        return "윤지";
    }
    @GetMapping("/api/v1/test/1")
    public UserResponse test1(@AuthenticationPrincipal UserResponse user) {
        return user;
    }
}
