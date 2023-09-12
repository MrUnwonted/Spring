package com.arjun.springboot.service;

import com.arjun.springboot.entity.User;


public interface UserService {

    User createUser(User user);

    User getUserById(Long userId);

}
