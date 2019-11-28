package com.mall.jiuzhenbao.user.service.impl;

import com.mall.jiuzhenbao.user.dao.UserRepository;
import com.mall.jiuzhenbao.user.domain.User;
import com.mall.jiuzhenbao.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
	private UserRepository userRep;

	public User save(User user){
		if(user != null){
			user.setCreateTime(new Date());
			user.setModifyTime(new Date());
			return userRep.save(user);
		}
		return null;
	}

	public User login(String username, String password){
		User exist = userRep.findByUsername(username).get(0);
		if(exist != null){
			return exist;
		}
		return null;
	}

    public User findByUsername(String username){
	    return userRep.findByUsername(username).get(0);
    }

	public User update(User user){
		return userRep.update(user);
	}

	public String logOut(User user){
		return "SUCCESS";
	}
}