package com.minhle.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service; 
import com.minhle.controller.KitchenUserRegistrationDto;
import com.minhle.model.user.KitchenProviderUser;
 
import com.minhle.repo.user.KitchenProviderUserRepository;
import org.springframework.security.core.userdetails.*;
@Service
public class KitchenProviderService implements UserDetailsService {
	@Autowired
	KitchenProviderUserRepository kitchenProviderRepository;
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;

	public KitchenProviderUser findByEmail(String email){
	        return kitchenProviderRepository.findByEmail(email);
    }

    //dto =  data transfer object 
    public KitchenProviderUser save(KitchenUserRegistrationDto registration){
    	KitchenProviderUser user = new KitchenProviderUser();
        user.setName(registration.getName()); 
        user.setEmail(registration.getEmail()); 
        user.setPassword(passwordEncoder.encode(registration.getPassword())); 
        return kitchenProviderRepository.saveUser(user);
    }
	 
    @Override
    public UserDetails loadUserByUsername(String providerName) throws UsernameNotFoundException {
    	KitchenProviderUser user = kitchenProviderRepository.findByEmail(providerName);
        if (user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return (user);
        		 
    }

	public void saveProvider(KitchenProviderUser kitchenProviderUser) {
		kitchenProviderRepository.saveUser(kitchenProviderUser);
	}
	public List<KitchenProviderUser> findAllProvider() {
		return kitchenProviderRepository.findAllUsers();
	}
}
