package com.minhle.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.minhle.service.KitchenProviderService;
 
//1 . Create configuation class extensd WebSecurity
//2. Provide BCryptPasswordEndcoder -> in this class, we can encrupt any data
//3. Invoke DaoAuthenticationProvider


@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	//Active string secureity
		//Performance 
	@Autowired
	KitchenProviderService kitchenUserService;

	
 
	@Override
	//provide access to some url, image, function 
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.authorizeRequests()
				.antMatchers("/registration**",
							 "/js",
							 "/css",
							 "/img",
							 "webjars/**").permitAll()
										  .anyRequest()
										  .authenticated()
			.and()
				.formLogin()
					.loginPage("/login").permitAll()
			.and()
				.logout()
					.invalidateHttpSession(true)
					.clearAuthentication(true) 
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
					.logoutSuccessUrl("/login?logout")
					.permitAll();
									
 
	}
	
	@Bean
	//invoke persistance layer
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	//Override DAOAuthentication 
	//getter and setter
	//inject to the repo 
	@Bean
	public DaoAuthenticationProvider authenticationProvider()
	{
		
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService( kitchenUserService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
		
	}
	@Override protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(authenticationProvider());
		
	}
	 
	
}
