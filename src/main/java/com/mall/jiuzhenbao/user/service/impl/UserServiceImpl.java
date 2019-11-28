package com.mall.jiuzhenbao.user.service.impl;

import com.mall.jiuzhenbao.user.dao.UserRepository;
import com.mall.jiuzhenbao.user.domain.User;
import com.mall.jiuzhenbao.user.dto.UserDTO;
import com.mall.jiuzhenbao.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * This class is providing implementation of UserService This class will fetch
 * or update user related data from userRepository
 * Created by Steven
 *
 * @version 0.0.1
 */
@Service
public class UserServiceImpl implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	public String getInfo(User user){
		if(user != null){
//			if(ConsumerContext.getConsumerContext().containsKey(user)){
//				return ConsumerContext.getConsumerContext().get(user);
//			}
		}
		return null;
	}

//	public Set<Role> getPermission(User user){
//		if(user != null){
//			Set<Role> roles = userRepository.getRolesByUserId(user.getUserId());
//			return roles;
//		}
//		return null;
//	}

	public User save(User user){
		if(user != null){
			user.setCreateTime(new Date());
			user.setModifyTime(new Date());
			return userRepository.save(user);
		}
		return null;
	}

	public Optional<UserDTO> login(String username, String password){
		return Optional.of(new UserDTO());
	}

	public boolean isFirstLogin(String userId){
		return false;
	}

    public User findByUsername(String username){
	    return userRepository.findByUsername(username).get(0);
    }
}