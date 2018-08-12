package com.darkcom.backend.service;

import com.darkcom.backend.dto.request.UserRequest;
import com.darkcom.backend.model.User;

public interface UserService {
    void addUser(UserRequest userRequest);

    User getUserByAccount(String account);
}
