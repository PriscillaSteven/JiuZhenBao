package com.mall.jiuzhenbao.security.filter.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.jiuzhenbao.filter.ReturnResult;
import com.mall.jiuzhenbao.security.dto.UserContext;
import com.mall.jiuzhenbao.user.service.UserService;
import com.mall.jiuzhenbao.utils.Constants;
import com.mall.jiuzhenbao.utils.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/*
 * Handle successfull authentication request.
 * Create Jwt token. and stored  UserContext data in session
 * Created by vishal.domale
 * @version 0.0.1
 */
//@Component
public class LoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(LoginAuthenticationSuccessHandler.class);

    @Autowired
    protected JwtUtil jwtUtil;

    @Autowired
    protected ObjectMapper mapper;

    @Autowired
    protected UserService userService;
    
    /**
     *
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     * @throws ServletException
     *
     * This method will call when user successfully authenticated
     *  and in this method  Create Jwt token and write that  jwt token in to response.
     *  Store Authenticated user's user context in to session.
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
    	logger.debug("User successfully authenticated.");
    	Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put(Constants.ACCESS_TOKEN, generateToken(authentication));
        tokenMap.put(Constants.REFRESH_TOKEN, generateRefreshToken(authentication));
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ReturnResult re = new ReturnResult(HttpStatus.OK.value(), tokenMap);
        mapper.writeValue(response.getWriter(), re);
        clearAuthenticationAttributes(request);
        //updateLoginTime(authentication);
    }

    
    /**
     * generate user token
     * @param authentication
     * @return
     */
    protected String generateToken(Authentication authentication) {
    	UserContext userContext = (UserContext) authentication.getPrincipal();
    	return jwtUtil.generateToken(userContext);
    }
    
    /**
     * generate user refresh token
     * @param authentication
     * @return
     */
    protected String generateRefreshToken(Authentication authentication) {
    	UserContext userContext = (UserContext) authentication.getPrincipal();
    	return jwtUtil.generateRefreshToken(userContext);
    }
    
    /**
     * Removing attribute from http session if present.
     * @param request
     */
    protected final void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
    
}