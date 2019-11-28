package com.mall.jiuzhenbao.user.service;

import com.mall.jiuzhenbao.user.domain.User;

/**
 * Service contract for User.
 * Created by Steven
 * @version 0.0.1
 */
public interface UserService {
    public User login(String username, String password);
//    public void logout();
    public User save(User user);
    public User findByUsername(String username);
    public User update(User user);
}