package com.mall.jiuzhenbao.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.io.UnsupportedEncodingException;

import com.mall.jiuzhenbao.security.dto.TokenType;
import com.mall.jiuzhenbao.security.dto.UserContext;
import com.mall.jiuzhenbao.security.exception.JwtExpiredTokenException;
import com.mall.jiuzhenbao.security.exception.JwtTokenMalformedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * In this utility will generate a jwt token using user context object .
 * and it also parse a jwt token.
 * Created by vishal.domale
 * @version 0.0.1
 */
@Component
public class JwtUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    //Read jwt secret from .properties file
    //private key use in hash algorithm(HS256).
    @Value("${jwt.secret}")
    private String secret;

    // user access token  expire time, default is 1 hours(in second)
    @Value("${jwt.userAccessToken.expireTime}")
    private long userAccessTokenExpireTime;
    
    // user refresh token  expire time, default is 2 hours(in second)
    @Value("${jwt.userRefreshToken.expireTime}")
    private long userRefreshTokenExpireTime;
    
    /**
     * This method will create jwt token.
     * and add claims in jwt token
     * @param userContext
     * @return
    */
    public String generateToken(UserContext userContext) {
        Claims claims = Jwts.claims().setSubject(userContext.getUserId().toString());
        claims.put(Constants.USER_ID,userContext.getUserId());
        claims.put(Constants.EMAIL,userContext.getEmail());
        claims.put(Constants.ROLE_ID,userContext.getRoleId());
        claims.put(Constants.FIRST_LOGIN, userContext.isFirstLogin());
        claims.put(Constants.TOKEN_TYPE, TokenType.Access);
        try {
            LocalDateTime currentTime = LocalDateTime.now();
            // Visit https://www.jsonwebtoken.io/ and see "JWTK/JJWT" tab for usage
            return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, secret.getBytes("UTF-8"))
                .setExpiration(Date.from(currentTime
                        .plusSeconds(userAccessTokenExpireTime)
                        .atZone(ZoneId.systemDefault()).toInstant()))
                .compact();
            
        } catch (UnsupportedEncodingException encodingEx) {
            logger.error("Exception in building JWT. " + encodingEx);
            throw new JwtTokenMalformedException("Invalid JWT token: " + encodingEx.getMessage());
        }
    }
    
    /**
     * This method will create jwt refresh token.
     * and add claims in jwt token
     * @param userContext
     * @return
    */
    public String generateRefreshToken(UserContext userContext) {
        Claims claims = Jwts.claims().setSubject(userContext.getUserId().toString());
        claims.put(Constants.USER_ID,userContext.getUserId());
        claims.put(Constants.EMAIL,userContext.getEmail());
        claims.put(Constants.ROLE_ID,userContext.getRoleId());
        claims.put(Constants.FIRST_LOGIN, userContext.isFirstLogin());
        claims.put(Constants.TOKEN_TYPE, TokenType.Refresh);
        try {
            LocalDateTime currentTime = LocalDateTime.now();
            // Visit https://www.jsonwebtoken.io/ and see "JWTK/JJWT" tab for usage
            
            return Jwts.builder()
                    .setClaims(claims)
                    .signWith(SignatureAlgorithm.HS256, secret.getBytes("UTF-8"))
                    .setExpiration(Date.from(currentTime
                            .plusSeconds(userRefreshTokenExpireTime)
                            .atZone(ZoneId.systemDefault()).toInstant()))
                    .compact();
        } catch (UnsupportedEncodingException encodingEx) {
            logger.error("Exception in building JWT. " + encodingEx);
            throw new JwtTokenMalformedException("Invalid JWT token: " + encodingEx.getMessage());
        }
    }

    /**
     * This method will parse jwt token by using secret key
     *  and return jws claims
     * @param authToken
     * @return Jws<Claims>
     *
     */
    public Jws<Claims> parseClaims(String authToken) {
        logger.debug("parseClaims...");
        try {
            // Visit https://www.jsonwebtoken.io/ and see "JWTK/JJWT" tab for usage
            return Jwts.parser().setSigningKey(secret.getBytes("UTF-8")).parseClaimsJws(authToken);
        } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException | SignatureException ex) {
        	logger.error("Validating user authToken :: " + authToken);
            logger.error("Exception in parsing claim. " + ex);
            throw new BadCredentialsException("Invalid JWT token: ", ex);
        } catch (ExpiredJwtException expiredEx) {
            logger.error("Exception in parsing claim. " + expiredEx);
            throw new JwtExpiredTokenException("JWT Token expired", expiredEx);
        } catch (UnsupportedEncodingException encodingEx) {
            logger.error("Exception in parsing claim. " + encodingEx);
            throw new JwtTokenMalformedException("Invalid JWT token: " + encodingEx.getMessage());
        }
    }
}
