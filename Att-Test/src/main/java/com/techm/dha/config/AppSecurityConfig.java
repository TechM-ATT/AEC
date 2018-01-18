package com.techm.dha.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//add users for in memory authentication
		UserBuilder user = User.withDefaultPasswordEncoder();
		
		auth.inMemoryAuthentication().withUser(user.username("dhaval").password("dhaval123").roles("ADMIN"));
		auth.inMemoryAuthentication().withUser(user.username("test").password("test123").roles("EMP"));
		auth.inMemoryAuthentication().withUser(user.username("sandeep").password("sandeep123").roles("MANAGER"));
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.anyRequest().authenticated().and().formLogin()
		.loginPage("/showLoginPage").loginProcessingUrl("/authenticateTheUser").permitAll();
	}

}
