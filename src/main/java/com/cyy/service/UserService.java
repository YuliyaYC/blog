package com.cyy.service;

import com.cyy.po.User;

public interface UserService {

    User checkUser(String username, String password);
}
