package com.mall.jiuzhenbao.security.filter;

import com.mall.jiuzhenbao.utils.Constants;
import com.mall.jiuzhenbao.utils.JwtAuthenticationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.util.ObjectUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This filter  will be activated for /api/** url pattern.
 * and check for jwt token in request header and authorise that token.
 * Created by vishal.domale
 * @version 0.0.1
 */

public class PostAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
	private static final Logger logger = LoggerFactory.getLogger(PostAuthenticationFilter.class);

	// Call when jwt token is unauthorised.
	private final AuthenticationFailureHandler failureHandler;
	

    public PostAuthenticationFilter(AuthenticationFailureHandler failureHandler) {
	    super("/**"); // TODO will check all the requests
	    this.failureHandler = failureHandler;
    }
    
    /**
     *
     * Extracting jwt token from request header
     * and check for that jwt token valid or not.
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
        JwtAuthenticationToken authRequest;
        String tokenPayload = extractToken(request.getHeader(Constants.AUTH_HEADER_NAME));
        authRequest = new JwtAuthenticationToken(tokenPayload);
        return getAuthenticationManager().authenticate(authRequest);
    }

    /**
     *
     * This method will call when jwt token is  successfully authenticated.
     * just forward  request to next filter.
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
	@Override
	protected void successfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain, Authentication authResult)
			throws IOException, ServletException {
		if (authResult.getPrincipal() != null) {
			logger.debug("Authorization successful!");
			SecurityContext context = SecurityContextHolder.createEmptyContext();
			context.setAuthentication(authResult);
			SecurityContextHolder.setContext(context);
		}
		chain.doFilter(request, response);
	}

    /**
     *
     * This method will call when jwt token is  not valid
     * and call a failureHandler's  onAuthenticationFailure method
     * @param request
     * @param response
     * @param failed
     * @throws IOException
     * @throws ServletException
     */
    @Override
	protected void unsuccessfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException failed)
			throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        failureHandler.onAuthenticationFailure(request, response, failed);
    }


    /**
     *
     * Check for that token payload has prefix value as "Bearer "
     * Extract jwt token from token payload mean get without prefix.
     * @param tokenHeader
     * @return
     */
    public String extractToken(String tokenHeader) {
        if (ObjectUtils.isEmpty(tokenHeader)) {
            throw new AuthenticationServiceException("Authorization header cannot be blank!");
        }
        if (!tokenHeader.startsWith(Constants.HEADER_PREFIX)) {
            throw new AuthenticationServiceException("Invalid authorization header.");
        }
        return tokenHeader.substring(Constants.HEADER_PREFIX.length(), tokenHeader.length());
    }
}