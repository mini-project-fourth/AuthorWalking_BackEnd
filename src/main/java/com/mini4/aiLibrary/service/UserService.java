package com.mini4.aiLibrary.service;

import com.mini4.aiLibrary.domain.User;

public interface UserService {
    User getByEmail(String email);

    void saveUser(User user);
}
