package com.example.bank.user.response;

import com.example.bank.user.domain.User;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String username
) {
    public static UserResponse from(User user){
        return new UserResponse(user.getId(), user.getUsername());
    }
}
