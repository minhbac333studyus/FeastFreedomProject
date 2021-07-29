package com.minhle.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

import com.minhle.controller.KitchenUserRegistrationDto;
import com.minhle.model.user.KitchenProviderUser;
import com.minhle.model.user.Role;
import com.minhle.repo.user.KitchenProviderRepository;
 
@Service
public class KitchenProviderService implements UserDetailsService 
{
	@Autowired
	KitchenProviderRepository kitchenProviderRepository;

	 @Autowired
private BCryptPasswordEncoder passwordEncoder;
	 
	public void saveProvider(KitchenProviderUser kitchenProviderUser) {
		kitchenProviderRepository.saveUser(kitchenProviderUser);
	}
	public List<KitchenProviderUser> findAllProvider() {
		return kitchenProviderRepository.findAllUsers();
	}
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
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		KitchenProviderUser user = kitchenProviderRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(
        	user.getEmail(),
            user.getPassword(),
            mapRolesToAuthorities(user.getRoles()));
	}
	private Collection < ? extends GrantedAuthority > mapRolesToAuthorities(Collection < Role > roles) {
        return roles.stream()
            .map(role -> new SimpleGrantedAuthority(role.getName()))
            .collect(Collectors.toList());
    }
}
