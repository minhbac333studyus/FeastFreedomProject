package com.minhle.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service; 
import com.minhle.controller.KitchenUserRegistrationDto;
import com.minhle.model.user.*;
import com.minhle.repo.user.KitchenProviderRepository;
import com.minhle.repo.user.UserRepository;
@Service
public class UserService implements UserDetailsService 
{
	@Autowired
	UserRepository userRepository;
	
	 @Autowired
	 private BCryptPasswordEncoder passwordEncoder;
	 
	public void saveProvider(com.minhle.model.user.EndUser user) {
		userRepository.saveUser(user);
	}
	public List<com.minhle.model.user.EndUser> findAllProvider() {
		return userRepository.findAllUsers();
	}
	public com.minhle.model.user.EndUser findByEmail(String email){
        return userRepository.findByEmail(email) ;
	}
	

    //dto =  data transfer object 
	public com.minhle.model.user.EndUser saveFromDTO(KitchenUserRegistrationDto registration){
		com.minhle.model.user.EndUser user = new com.minhle.model.user.EndUser();
	    user.setName(registration.getName()); 
	    user.setEmail(registration.getEmail()); 
	    user.setPassword(passwordEncoder.encode(registration.getPassword()));  
	    return userRepository.saveUser(user);
	} 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		final com.minhle.model.user.EndUser provider = userRepository.findByName(username) ;
	    if (provider.getName() == "Empty") {
	        throw new UsernameNotFoundException("Invalid username or password.");
	    }
	    UserDetails providerUserDetail = User.withUsername(provider.getEmail())
	    						.password(provider.getPassword())
	    						.authorities("PROVIDER").build();
        return providerUserDetail;
//	    return new User(
//	    	user.getName(),
//	        user.getPassword(),
//	        mapRolesToAuthorities(user.getRoles()));
		
	}
 
}
