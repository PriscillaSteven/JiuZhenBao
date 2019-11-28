package com.mall.jiuzhenbao.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.jiuzhenbao.config.AppConfig;
import com.mall.jiuzhenbao.security.CustomUserDetailsService;
import com.mall.jiuzhenbao.security.filter.handler.SpogRequestURLMatcher;
import com.mall.jiuzhenbao.security.filter.provider.PreAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * Spring Security configuration class
 * Created by vishal.domale
 * @version 0.0.1
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AppConfig spogConfig;

    public static final String AUTHENTICATE_ENTRY_POINT = "/users/login";
    public static final String USER_SET_PASSWORD_ENTRY_POINT = "/users/setpassword";

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PreAuthenticationProvider preAuthenticationProvider;

//    @Autowired
//    private UserDetailsService userDetailsService;

//    @Autowired
//    private PostAuthenticationProvider postAuthenticationProvider;

//    @Autowired
//    private LoginAuthenticationSuccessHandler loginAuthenticationSuccessHandler;
//
//    @Autowired
//    private LoginAuthenticationFailureHandler loginAuthenticationFailureHandler;

    @Autowired
    private ObjectMapper objectMapper;
    /**
     *  Enable Spring security
     *  This method  will  disable csrf support.
     *  and register entry point.
     *  and register  preAuthenticationFilter and postAuthenticationFilter filter in HttpSecurity .
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .exceptionHandling()
            .and()
            .headers().frameOptions().sameOrigin()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers("/index")/*.authenticated()*/.permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/api/users/login").permitAll();
//            .and()
//            .addFilterBefore(preAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//            .addFilterBefore(postAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class);
    }
    
    /**
     *  This method will create object of PreAuthenticationFilter class
     * @return PreAuthenticationFilter
     *
     */
//    private PreAuthenticationFilter preAuthenticationFilter() {
//        PreAuthenticationFilter filter = new PreAuthenticationFilter(buildFullEntryPointPath(AUTHENTICATE_ENTRY_POINT),
//        		loginAuthenticationSuccessHandler, loginAuthenticationFailureHandler, objectMapper);
//        filter.setAuthenticationManager(authenticationManager);
//        return filter;
//    }

    /**
     * This method will create PostAuthenticationFilter class object.
     * @return PostAuthenticationFilter
     */
//    private PostAuthenticationFilter postAuthenticationFilter() {
//        PostAuthenticationFilter filter = new PostAuthenticationFilter(loginAuthenticationFailureHandler);
//        filter.setAuthenticationManager(authenticationManager);
//        filter.setRequiresAuthenticationRequestMatcher(requestMatcher());
//        return filter;
//    }
    
    private String buildFullEntryPointPath(String path) {
    	return spogConfig.getApiBase() + path;
    }

    /**
     * This method will create object of SkipPathRequestMatcher class
     * @return RequestMatcher
     */
	private RequestMatcher requestMatcher() {
		SpogRequestURLMatcher.MatcherBuilder builder = SpogRequestURLMatcher.getBuilderWithBase(spogConfig.getApiBase());
		RequestMatcher match = builder
				.addWhiteMatcher(AUTHENTICATE_ENTRY_POINT)
				.addWhiteMatcher(USER_SET_PASSWORD_ENTRY_POINT)
				.addBlackMatcher("/**")
				.build();
		return match;
	}
    
    /**
     * Configure authentication manager to call respective authentication providers
     * This method will register a loginAuthenticationProvider and jwtAuthenticationProvider in authenticationManager
     * @param auth
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
//        auth
//        	.authenticationProvider(preAuthenticationProvider);
//        	.authenticationProvider(postAuthenticationProvider);
//        auth.userDetailsService(userDetailsService);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(customUserDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    /**
     * This method will create  a AuthenticationManager Bean
     * @return AuthenticationManager
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public Md5PasswordEncoder passwordEncoder() {
        return new Md5PasswordEncoder();
    }

    @Bean
    public CustomUserDetailsService customUserDetailsService(){
        return new CustomUserDetailsService();
    }
}
