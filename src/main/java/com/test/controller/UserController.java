package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.datamodel.User;
import com.test.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
	 
	
	@Autowired
	private UserService service;
	
	
	@PostMapping(value="/add",consumes=MediaType.APPLICATION_JSON_VALUE)
	public String addUser(@RequestBody User user){
		return service.saveUser(user);
		
	}
	@GetMapping(value="/get",produces=MediaType.APPLICATION_JSON_VALUE)
	public User getUser(@RequestParam(name="name") String name){
		return service.searchUser(name);
		
	}
	
	@GetMapping(value="/msg")
	public String getMessage(@RequestParam(name="name") String name){
		return !StringUtils.isEmpty(name)?service.getMessage(name):"Parameter Not available!!";
	}
}
