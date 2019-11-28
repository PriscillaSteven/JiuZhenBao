package com.mall.jiuzhenbao.user.resource;

import com.mall.jiuzhenbao.security.LoginRequest;
import com.mall.jiuzhenbao.security.LoginResponse;
import com.mall.jiuzhenbao.user.domain.User;
import com.mall.jiuzhenbao.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

@Controller
@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    private Logger logger = LoggerFactory.getLogger(UserResource.class);
    @Autowired
    private UserService userService;

    @POST
    @Path("/login")
    public LoginResponse login(@Valid LoginRequest loginRequest){
        return new LoginResponse();
    }

    @POST
    @Path("/admin/settings")
    public void loginSetting(User user){

    }

    @POST
    @Path("/add")
    public User addUser(User user){
        if(user != null){
            return userService.save(user);
        }
        return null;
    }

    @GET
    @Path("/login/test")
    public LoginResponse login(){
        return new LoginResponse();
    }
}
