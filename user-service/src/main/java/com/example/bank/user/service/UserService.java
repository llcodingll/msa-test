package com.example.bank.user.service;

import com.example.bank.user.request.LoginRequest;
import com.example.bank.user.request.RegisterRequest;
import com.example.bank.user.response.UserResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {
    String login(LoginRequest loginRequest);
    void register(RegisterRequest registerRequest);
    UserResponse getById(String id);

    UserDetails loadUserByUsername(String username)  throws UsernameNotFoundException;

    UserResponse getByToken(String token);
}
