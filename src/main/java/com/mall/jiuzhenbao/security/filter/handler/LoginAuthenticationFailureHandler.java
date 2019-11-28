package com.mall.jiuzhenbao.security.filter.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.jiuzhenbao.base.exception.SpogServiceException;
import com.mall.jiuzhenbao.filter.ReturnResult;
import com.mall.jiuzhenbao.message.ISpogMessages;
import com.mall.jiuzhenbao.message.SpogErrorMessageBean;
import com.mall.jiuzhenbao.message.SpogMessageCode;
import com.mall.jiuzhenbao.security.exception.AuthMethodNotSupportedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * Handle unauthenticated request
 * and write error message in response
 * Created by vishal.domale
 * @version 0.0.1
 */
//@Component
public class LoginAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private static final Logger logger = LoggerFactory.getLogger(LoginAuthenticationFailureHandler.class);

    // ObjectMapper will use to  write error response in json format.
    private final ObjectMapper mapper;
    
    @Autowired
    private ISpogMessages spogMessages;

    @Autowired
    public LoginAuthenticationFailureHandler(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    /**
     *
     * This will call when user and jwt token is unauthorised.
     * @param request
     * @param response
     * @param e
     * @throws IOException
     * @throws ServletException
     *
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException e) throws IOException, ServletException {
        logger.info("Authentication failed!");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        SpogErrorMessageBean messageBean;
        if(e instanceof AuthMethodNotSupportedException){
            messageBean = new SpogErrorMessageBean(SpogMessageCode.AUTHENTICATION_METHOD_NOT_SUPPORT.getCodeString(), e.getMessage());
        }else if(e instanceof UsernameNotFoundException){
            messageBean = new SpogErrorMessageBean(SpogMessageCode.AUTHENTICATION_USERNAME_NOT_FOUND.getCodeString(), e.getMessage());
        }else if(e instanceof DisabledException){
            messageBean = new SpogErrorMessageBean(SpogMessageCode.AUTHENTICATION_USER_DELETED.getCodeString(), e.getMessage());
        }else if(e instanceof SpogServiceException){
        	SpogServiceException serviceException = (SpogServiceException) e;
        	String message = spogMessages.getMessage(serviceException.getCode(),
					serviceException.getArguments(), request.getLocale());
        	messageBean = new SpogErrorMessageBean(serviceException.getCode(), message);
        }else{
            messageBean = new SpogErrorMessageBean(SpogMessageCode.COMMON_AUTHENTICATION_FAILED.getCodeString(), e.getMessage());
        }
        
        SpogErrorMessageBean[] errors = {messageBean};
        ReturnResult re = new ReturnResult();
        re.setStatus(HttpStatus.UNAUTHORIZED.value());
        re.setErrors(errors);
        re.setData("");
        mapper.writeValue(response.getWriter(), re);

    }
}