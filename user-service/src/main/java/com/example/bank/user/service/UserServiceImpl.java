package com.example.bank.user.service;

import com.example.bank.global.JwtUtils;
import com.example.bank.user.domain.User;
import com.example.bank.user.repository.UserRepository;
import com.example.bank.user.request.LoginRequest;
import com.example.bank.user.request.RegisterRequest;
import com.example.bank.user.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String login(LoginRequest loginRequest) {
        Optional<User> loginUser = userRepository.findByEmail(loginRequest.email());
        if(loginUser.isEmpty()) throw new RuntimeException("로그인 실패");
        User user = loginUser.get();
        if (passwordEncoder.matches(loginRequest.password(), user.getPassword()))
            throw new RuntimeException("로그인 실패");
        return jwtUtils.generateToken(user.getUsername());
    }

    @Override
    public void register(RegisterRequest registerRequest) {

        Optional<User> byEmail = userRepository.findByEmail(registerRequest.email());
        if(byEmail.isPresent()) throw new RuntimeException("이미 등록된 이메일");
        Optional<User> byUsername = userRepository.findByUsername(registerRequest.username());
        if(byUsername.isPresent()) throw new RuntimeException("이미 등록된 이름");
        User entity = registerRequest.toEntity(passwordEncoder);
        userRepository.save(entity);

    }

    @Override
    public UserResponse getById(String id) {
        UUID uuid = UUID.fromString(id);
        Optional<User> byId = userRepository.findById(uuid);
        if (byId.isEmpty()) throw new RuntimeException("없는 유저");
        return UserResponse.from(byId.get());
    }

    @Override
    public UserDetails loadUserByUsername(String username)  throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("없는 유저"));
    }

    @Override
    public UserResponse getByToken(String token) {
        String username = jwtUtils.parseToken(token);
        Optional<User> byUsername = userRepository.findByUsername(username);
        if (byUsername.isEmpty()) throw new RuntimeException("없는 유저");
        return UserResponse.from(byUsername.get());
    }
}
