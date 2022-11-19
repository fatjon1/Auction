package com.auction.app.service;

import com.auction.app.dto.UserWrite;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auction.app.impl.UserDetailsImpl;
import com.auction.app.model.User;
import com.auction.app.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
	@NonNull protected UserRepository userRepository;
	@NonNull protected PasswordEncoder encoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user=userRepository.findByUsername(username);
		if(user==null)
			throw new UsernameNotFoundException("User Not Found");
		
		
		return new UserDetailsImpl(user);
	}

	public String register(UserWrite user, String roles) {
		if (user == null || StringUtils.isBlank(user.getUsername()))
			return "Provide a valid username";

		User dbAdmin = userRepository.findByUsername(user.getUsername());
		if (dbAdmin != null)
			return String.format("Username '%s' is present on database", user.getUsername());

		if (StringUtils.isBlank(user.getPassword()))
			return "Password can not be null!";

		if (StringUtils.isBlank(user.getRepeatedPassword()))
			return "Repeated password can not be null!";

		if (!user.getRepeatedPassword().equals(user.getPassword()))
			return "Password and repeated password are not the same!";

		User newUser = new User();
		newUser.setUsername(user.getUsername());
		newUser.setRoles(roles);
		newUser.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(newUser);

		return "User created successfully!";
	}

}
