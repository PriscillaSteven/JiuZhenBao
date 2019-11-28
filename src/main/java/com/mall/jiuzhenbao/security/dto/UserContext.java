package com.mall.jiuzhenbao.security.dto;

import java.util.Objects;

/**
 * This class will define context for authenticated user for jwt utility.
 * {@link UserContext}
 * Created by vishal.domale
 * @version 0.0.1
 */
public class UserContext{

    //userId of the authenticated user
    protected String userId;

    //email  of the authenticated user
    protected String email;

    //email of the authenticated user
    private String roleId;

    // Organization Type of the authenticated user

    private boolean firstLogin;
    

    private UserContext() {}
    
    /**
     * Used in site context
     * @param siteId
     * @param organizationID
     * @param roleId
     */
    protected UserContext(String siteId, String organizationID, String roleId) {
    	this.userId = siteId;
        this.roleId = roleId;
    }
    
    /**
     * This method will check  for username if it found null then it will throw exception
     * otherwise  it will create object of user context and return that object.
     * @param userId
     * @param email
     * @param roleId
     * @param organizationType
     * @return
     */
    public static UserContext create(String userId, String email, String roleId, String organizationType,boolean firstLogin) {
       if (Objects.isNull(userId)) throw new IllegalArgumentException("UserId is blank: " + userId);
        UserContext context = new UserContext();
        context.userId = userId;
        context.email = email;
        context.roleId = roleId;
        context.firstLogin = firstLogin;
        return context;
    }

	/**
     * Return userId of the user context.
     * @return userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Return Email of the user context.
     * @return Email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Return RoleId of the user context.
     * @return RoleId
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * Return Organization Type of the user context.
     * @return Organization Type
     */

    public boolean isFirstLogin(){
    	return firstLogin;
    }
    
    public void setFirstLogin(boolean isFirstLogin){
    	this.firstLogin = isFirstLogin;
    }
}