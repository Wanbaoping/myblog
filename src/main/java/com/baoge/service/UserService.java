package com.baoge.service;

import com.baoge.entity.User;


public interface UserService {
    User checkUser(String username, String password);
}
