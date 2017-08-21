package com.baeldung.lss.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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
    protected void configure(HttpSecurity http) throws Exception { // @formatter:off
        http
        .authorizeRequests()
                .anyRequest().authenticated()
        
        .and()
        .formLogin().
            loginPage("/login").permitAll().
            loginProcessingUrl("/doLogin")
            
        // enable logout and permit
        .and().logout().permitAll()
        	// specify only logout URL
        	//.logoutUrl("/doLogout")
        	// recommended: use POST as the action is to modify the state + POST can be used with CSRF
        	.logoutRequestMatcher(new AntPathRequestMatcher("/doLogout", "GET"))
        	// whether to clear user authentication when the user logs out
        	//.clearAuthentication(true|false)
        	// which cookies to delete
        	//.deleteCookies(...)
        	// whether to invalidate the session
        	//.invalidateHttpSession(true|false)
        	// URL to redirect in case of logout
        	//.logoutSuccessUrl(url)
        	// action to execute in case of logout
        	//.logoutSuccessHandler(...)
            
        .and()
        .csrf().disable();
    } // @formatter:on
}