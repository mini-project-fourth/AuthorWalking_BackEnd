package com.mini4.aiLibrary.service.impl;

import com.mini4.aiLibrary.domain.User;
import com.mini4.aiLibrary.dto.LoginDto;
import com.mini4.aiLibrary.dto.SignUpDto;
import com.mini4.aiLibrary.service.AuthService;
import com.mini4.aiLibrary.service.UserService;
import com.mini4.aiLibrary.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public AuthServiceImpl(UserService userService, JwtUtil jwtUtil, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String signupUser(SignUpDto signUpDto) {
        try {
            User user = User.builder()
                    .email(signUpDto.getEmail())
                    .username(signUpDto.getUsername())
                    .password(passwordEncoder.encode(signUpDto.getPassword()))
                    .role("USER")
                    .build();

            userService.saveUser(user);
            return "회원가입이 완료되었습니다.";
        } catch (Exception e) {
            logger.error("회원가입 중 오류 발생: {}", e.getMessage(), e);
            throw new RuntimeException("회원가입 중 오류가 발생했습니다.");
        }
    }

    @Override
    public String loginUser(LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
            );

            String token = jwtUtil.generateToken(loginDto.getEmail());
            logger.info("로그인 성공 - JWT 토큰: {}", token);
            return token;
        } catch (Exception e) {
            logger.error("로그인 실패: {}", e.getMessage());
            throw new RuntimeException("이메일 또는 비밀번호가 잘못되었습니다.");
        }
    }
}
