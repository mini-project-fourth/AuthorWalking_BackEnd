package com.mini4.aiLibrary.service;

import com.mini4.aiLibrary.dto.LoginDto;
import com.mini4.aiLibrary.dto.SignUpDto;

public interface AuthService {
    String signupUser(SignUpDto signUpDto);
    String loginUser(LoginDto loginDto);

}
