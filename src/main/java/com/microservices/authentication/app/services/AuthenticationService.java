package com.microservices.authentication.app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.microservices.authentication.app.entities.ApplicationUser;
import com.microservices.authentication.app.repository.ApplicationUserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor

public class AuthenticationService {
	
	@Autowired
	private ApplicationUserRepository userRepository;
	
	public ApplicationUser getApplicationUserById(int id) {
		Optional<ApplicationUser> optionalUser = userRepository.findById(id);
		return optionalUser.get();
	}
	    public long register(ApplicationUser appl) {
	        
		ApplicationUser user
	                = ApplicationUser.builder()
	                .userId(appl.getUserId())
	                .username(appl.getUsername())
	                .password(appl.getPassword())
	                
	                .build();

	        user = userRepository.save(user);

	        return user.getUserId();
	    }
	
	
}
