package com.baeldung.lss.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class LssSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { // @formatter:off 
        auth.
            inMemoryAuthentication().
            withUser("user").password("pass").
            roles("USER");
    } // @formatter:on

    @Override
    protected void configure(HttpSecurity http) throws Exception {   	
    	http
    		.authorizeRequests()    			
    			// for requests with /delete prefix need to have role
    			.antMatchers("/delete/**").hasAnyAuthority("ADMIN", "ADMIN2")
    			.anyRequest().authenticated()
    			
    			// needs to have the ADMIN role => check against "ROLE_ADMIN" - old usage
    			//.hasRole("ADMIN")
    			
    			// same but no need to prefix with "ROLE_"
    			//.hasAuthority("ADMIN")
    			
    			// has any authority
    			//.hasAnyAuthority("ADMIN", "ADMIN2")
    			
    			// needs to have specific IP
    			//.hasIpAddress("192.168.0.1")
    			
    			// allow to use SpEL
    			//.access("...")
    			
    			// signal that any type of access is ok
    			//.anonymous()
    			
    			// check only that is authenticated
    			//.authenticated()
    			
    			// deny any access
    			//.denyAll()
    			
    			// authenticated but not remembered
    			//.fullyAuthenticated()
    			
    			// remember me
    			//.rememberMe()
    			
    			// negation
    			//.not()
    			
    		.and()
    		.formLogin()
    		.and()
    		.httpBasic();
    }
}