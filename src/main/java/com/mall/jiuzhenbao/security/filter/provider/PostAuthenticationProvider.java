package com.mall.jiuzhenbao.security.filter.provider;

import java.util.HashSet;
import java.util.Set;

import com.mall.jiuzhenbao.security.dto.UserContext;
import com.mall.jiuzhenbao.utils.Constants;
import com.mall.jiuzhenbao.utils.JwtAuthenticationToken;
import com.mall.jiuzhenbao.utils.JwtUtil;
import com.mall.jiuzhenbao.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

/**
 * This class support  JwtAuthenticationToken.class object.
 * When authentication manager will  have JwtAuthenticationToken  object
 * then it will call this class's authenticate method.
 * Created by vishal.domale
 *
 * @version 0.0.1
 */
//@Component
public class PostAuthenticationProvider implements AuthenticationProvider {
	private static final Logger logger = LoggerFactory.getLogger(PostAuthenticationProvider.class);

    // use for  parse a jwt token.
    @Autowired
    protected JwtUtil jwtUtil;

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.equals(authentication));
    }

    /**
     * This method will parsing jwt token
     * and validate that token
     *
     * @param authentication
     * @return
     */
    @Override
    public Authentication authenticate(Authentication authentication) {
        JwtAuthenticationToken authToken = (JwtAuthenticationToken) authentication;
        Claims claims = validateAccessToken(authToken.getToken());
        String siteId = getSiteId(claims);
        UserContext context;
        if(StringUtils.isNotEmpty(siteId)) {
        	// site token
        	logger.debug("Site: {} has authorities: {}", siteId);
        	context = UserContext.create("userId",
                    getEmail(claims), getRoleId(claims), getOrganizationType(claims),isFirstLogin(claims));
        }else {
        	// user token
        	String userId = getUseId(claims);
        	logger.debug("User: {} has authorities: {}", userId);
        	context = UserContext.create(userId,
    				getEmail(claims), getRoleId(claims), getOrganizationType(claims),isFirstLogin(claims));
        }
		Set<GrantedAuthority> accesses=new HashSet<GrantedAuthority>();
		accesses.add(new SimpleGrantedAuthority(context.getRoleId()));
        return new JwtAuthenticationToken(context, null, accesses);
    }

    /**
     * This  method will return
     * a username form claims object.
     *
     * @param claims
     * @return
     */
    private String getUseId(Claims claims) {
        return (String) claims.get(Constants.USER_ID);
    }
    
    /**
     * This method will return site id from claims object.
     * @param claims
     * @return
     */
    private String getSiteId(Claims claims) {
        return (String) claims.get(Constants.SITE_ID);
    }

    /**
     * This  method will return a email form claims object.
     *
     * @param claims
     * @return
     */
    private String getEmail(Claims claims) {
        return (String) claims.get(Constants.EMAIL);
    }

    /**
     * This  method will return a OrganizationType form claims object.
     *
     * @param claims
     * @return
     */
    protected String getOrganizationType(Claims claims) {
        return (String) claims.get(Constants.ORGANIZATION_TYPE);
    }

    /**
     * This  method will return a roleId form claims object.
     *
     * @param claims
     * @return
     */
    protected String getRoleId(Claims claims) {
      return (String)claims.get(Constants.ROLE_ID);
    }

    protected boolean isFirstLogin(Claims claims){
    	return claims.get(Constants.FIRST_LOGIN) == null ? false : (boolean)claims.get(Constants.FIRST_LOGIN);
    }
    
    /**
     * This method will parse string jwt token and
     * return a claims(payload of JWT TOKEN)
     *
     * @param authToken
     * @return Claims
     */
    protected Claims validateAccessToken(String authToken) {
        logger.debug("Validating access token");
        logger.debug("Signing JWT token ");
        Jws<Claims> jwsClaims = jwtUtil.parseClaims(authToken);
        logger.debug("Signed JWT token ");
        Claims claims = jwsClaims.getBody();
        //Check claim

        //Check validity date of auth token
//        Date expiryDate = claims.getExpiration();
//        if (expiryDate.before(new Date())) {
//            throw new JwtExpiredTokenException("JWT Token expired");
//        }
        logger.debug("Validation of access token is successfully done ");
        return claims;
    }

}