package com.minhle.service; 
import java.util.List; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service; 
import com.minhle.config.security.KitchenUserRegistrationDto;
import com.minhle.model.user.KitchenProviderUser;
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
        return kitchenProviderRepository.findByEmail(email) ;
	}
	

    //dto =  data transfer object 
	public KitchenProviderUser saveFromDTO(KitchenUserRegistrationDto registration){
		KitchenProviderUser user = new KitchenProviderUser();
	    user.setName(registration.getName()); 
	    user.setEmail(registration.getEmail()); 
	    user.setPassword(passwordEncoder.encode(registration.getPassword()));  
	    return kitchenProviderRepository.saveUser(user);
	} 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		final KitchenProviderUser provider = kitchenProviderRepository.findByName(username) ;
	    if (provider.getName() == "Empty") {
	        throw new UsernameNotFoundException("Invalid username or password.");
	    }
	    UserDetails providerUserDetail = User.withUsername(provider.getEmail())
	    						.password(provider.getPassword()).roles("PROVIDER")
	    						.build();
        return providerUserDetail; 
		
	}
 
}
