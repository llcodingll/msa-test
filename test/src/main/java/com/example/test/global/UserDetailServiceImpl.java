package com.example.test.global;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserApi userApi;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userApi.me(username);
    }
}
