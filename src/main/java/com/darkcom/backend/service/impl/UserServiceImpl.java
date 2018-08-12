package com.darkcom.backend.service.impl;

import com.darkcom.backend.dto.request.UserRequest;
import com.darkcom.backend.model.User;
import com.darkcom.backend.repository.UserRepository;
import com.darkcom.backend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    public static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;

    @Override
    public void addUser(UserRequest userRequest) {
        User user = new User();
        BeanUtils.copyProperties(userRequest, user);
        userRepository.save(user);
    }

    @Override
    public User getUserByAccount(String account) {
        return userRepository.findUserByAccount(account);
    }
}
