package com.mini4.aiLibrary.service.impl;

        import com.mini4.aiLibrary.domain.User;
        import com.mini4.aiLibrary.repository.UserRepository;
        import com.mini4.aiLibrary.service.UserService;
        import org.springframework.security.core.userdetails.UserDetails;
        import org.springframework.security.core.userdetails.UserDetailsService;
        import org.springframework.security.core.userdetails.UsernameNotFoundException;
        import org.springframework.stereotype.Service;

        @Service
        public class UserServiceImpl implements UserService, UserDetailsService {

            private final UserRepository userRepository;

            public UserServiceImpl(UserRepository userRepository) {
                this.userRepository = userRepository;
            }

            @Override
            public User getByEmail(String email) {
                return userRepository.findByEmail(email)
                        .orElseThrow(() -> new IllegalArgumentException("Email not found: " + email));
            }

            @Override
            public void saveUser(User user) {
                userRepository.save(user);
            }

            @Override
            public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                User user = getByEmail(email);
                return org.springframework.security.core.userdetails.User.builder()
                        .username(user.getEmail())
                        .password(user.getPassword())
                        .roles(user.getRole())
                        .build();
            }
        }
