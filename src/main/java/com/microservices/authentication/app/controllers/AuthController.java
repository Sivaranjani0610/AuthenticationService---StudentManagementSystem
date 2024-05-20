package com.microservices.authentication.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.microservices.authentication.app.entities.ApplicationUser;
import com.microservices.authentication.app.services.AuthenticationService;

@RestController
@CrossOrigin
public class AuthController {
	
	@Autowired
	private AuthenticationService authService;
	
	@PostMapping("/register")
    public ResponseEntity<Long> registerUser(@RequestBody ApplicationUser appl) {
        long userId = authService.register(appl);
        return new ResponseEntity<>(userId, HttpStatus.CREATED);
    }
	
	@PostMapping("/authenticate")
	public ResponseEntity<ApplicationUser> authenticate(@RequestBody ApplicationUser user) {
		ApplicationUser applicationUser = authService.getApplicationUserById(user.getUserId());
		if(!applicationUser.getPassword().equals(user.getPassword())) {
			return new ResponseEntity<ApplicationUser>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<ApplicationUser>(applicationUser, HttpStatus.OK);
	}
	
	

}
