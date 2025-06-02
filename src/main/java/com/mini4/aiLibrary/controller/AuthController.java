package com.mini4.aiLibrary.controller;

    import com.mini4.aiLibrary.domain.User;
    import com.mini4.aiLibrary.dto.LoginDto;
    import com.mini4.aiLibrary.dto.SignUpDto;
    import com.mini4.aiLibrary.service.UserService;
    import com.mini4.aiLibrary.service.AuthService;
    import com.mini4.aiLibrary.util.JwtUtil;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;

    @RestController
    @RequestMapping("/auth")
    public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
         private final JwtUtil jwtUtil;


        private final AuthService authService;
        private final UserService userService;

        public AuthController(AuthService authService, JwtUtil jwtUtil, UserService userService) {
            this.authService = authService;
            this.jwtUtil = jwtUtil;
            this.userService = userService;
        }

        @PostMapping("/signup")
        public ResponseEntity<String> registerUser(@RequestBody SignUpDto signUpDto) {
            String message = authService.signupUser(signUpDto);
            return ResponseEntity.ok(message);
        }

        @PostMapping("/login")
        public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
            String token = authService.loginUser(loginDto);
            return ResponseEntity.ok(token);
        }
        @GetMapping("/userinfo")
        public ResponseEntity<User> getUserInfo(@RequestHeader(value = "Authorization", required = false) String token) {
            if (token == null) {
                logger.error("Authorization 헤더가 누락되었습니다.");
                throw new IllegalArgumentException("Authorization 헤더가 필요합니다.");
            }
            logger.info("Authorization 헤더: {}", token);
            String email = jwtUtil.extractEmail(token.replace("Bearer ", ""));
            logger.info("추출된 이메일: {}", email);
            User user = userService.getByEmail(email);
            return ResponseEntity.ok(user);
        }
    }
