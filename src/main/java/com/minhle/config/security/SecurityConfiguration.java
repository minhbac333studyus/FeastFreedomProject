package com.minhle.config.security;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter; 
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.minhle.service.EndUserService;
import com.minhle.service.KitchenProviderService;
 
//1 . Create configuation class extensd WebSecurity
//2. Provide BCryptPasswordEndcoder -> in this class, we can encrupt any data
//3. Invoke DaoAuthenticationProvider 
@Configuration
@EnableWebSecurity
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter { 
	@Autowired
	KitchenProviderService kitchenUserService;
	@Autowired
	EndUserService endUserService;
	
 
	@Override
	//provide access to some url, image, function 
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.csrf().disable(); //CSRF ( Cross Site Request Forgery) 
		
		/*
		 *  Home page request to login with role User or Administrator
			if the user don't login then it redirect to /login 
			ROLE_USER, ADMIN, PROVIDER is take from database
		 */
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests() .antMatchers("/registration**").permitAll();
		http.authorizeRequests() .antMatchers("/homepage**").permitAll();
		/*
		 * If user login with ROLE_USER but try to access to admin page then send them a message denied
		 */
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
		 
		/*
		 * save user id for 24 hour after login successfully
		 */
		http.authorizeRequests().and().rememberMe()
			.tokenRepository(this.persistentTokenRepository())  
	        .tokenValiditySeconds(1 * 24 * 60 * 60); // 24h
		//http.antMatcher("/provider*").authorizeRequests().anyRequest().hasRole("PROVIDER");
		
		/*
		 * Allow login with provider Role
		 */
		
		http.authorizeRequests() 
			
			.and()
				.formLogin()
				.loginPage("/login")
				//if successful then go to this page
				.defaultSuccessUrl("/homepage/loginsucess")
				//if not go error
                .failureUrl("/login?error=true") 
                .usernameParameter("username")// parameter from FORM login ở bước 3 có input  name='username'
                .passwordParameter("password")// parameter from FORM login ở bước 3 có input  name='password'
				.permitAll();
	 
		http.authorizeRequests() 
			.and()
				.logout()
				.invalidateHttpSession(true)
				.clearAuthentication(true) 
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/?logout")
				.permitAll();
									
		
 
	}
	
	 @Bean
	    public PersistentTokenRepository persistentTokenRepository() {
	        InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl(); 
	        return memory;
	    }
	
	 /*
	  * used to decode user password after registration
	  */
	@Bean 
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	} 
	@Bean
	public DaoAuthenticationProvider authenticationProvider()
	{
		
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService( kitchenUserService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
		
	}
	@Bean
	public DaoAuthenticationProvider authenticationEndUser()
	{
		
		DaoAuthenticationProvider authenticationUser = new DaoAuthenticationProvider();
		authenticationUser.setUserDetailsService( endUserService);
		authenticationUser.setPasswordEncoder(passwordEncoder());
		return authenticationUser;
		
	}
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationEndUser());
        auth.authenticationProvider(authenticationProvider());

    }
 
	 
	
}
