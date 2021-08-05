package com.minhle.service; 
import java.util.List; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.minhle.config.security.UserRegistrationDto;
import com.minhle.model.kitchen.Order;
import com.minhle.model.user.*; 
import com.minhle.repo.user.EndUserRepository;
@Service
public class EndUserService implements UserDetailsService 
{
	@Autowired
	EndUserRepository endUserRepository;
	
	 @Autowired
	 private BCryptPasswordEncoder passwordEncoder;
	 
	public void saveUser(EndUser user) {
		endUserRepository.saveUser(user);
	}
	public List<EndUser> findAllUser() {
		return endUserRepository.findAllUsers();
	}
	public EndUser findByEmail(String email){
        return endUserRepository.findByEmail(email) ;
	}
	

    //dto =  data transfer object 
	public EndUser saveFromDTO(UserRegistrationDto registration){
		EndUser user = new EndUser();
	    user.setName(registration.getName()); 
	    user.setEmail(registration.getEmail()); 
	    user.setPassword(passwordEncoder.encode(registration.getPassword())); 
	    Order order = new Order();
	    order.setUserEmail(registration.getEmail());
	    user.setTemporaryOrder(order );
	    return endUserRepository.saveUser(user);
	} 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		EndUser enduser = endUserRepository.findByName(username) ;
	    if (enduser.getName() == "Empty") {
	        throw new UsernameNotFoundException("Invalid username or password." + username);
	    }
	    UserDetails EndUserDetail = User.withUsername(enduser.getEmail())
	    						.password(enduser.getPassword()).roles("ENDUSER")
	    						.build();
        return EndUserDetail;  
	}
 
}
