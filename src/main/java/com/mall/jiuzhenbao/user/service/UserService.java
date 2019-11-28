package com.mall.jiuzhenbao.user.service;

import com.mall.jiuzhenbao.user.domain.User;
import com.mall.jiuzhenbao.user.dto.UserDTO;

import java.util.Optional;

/**
 * Service contract for User.
 * Created by Steven
 * @version 0.0.1
 */
public interface UserService {
    public Optional<UserDTO> login(String username, String password);
    public boolean isFirstLogin(String userId);
    public String getInfo(User user);
    public User save(User user);
    public User findByUsername(String username);
}