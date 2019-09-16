package com.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.datamodel.User;
import com.test.repo.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo repo;
	
	public String saveUser(User user){
		return repo.save(user)!=null ?" User Saved !!":"User Not Saved !!";
	}
	
	public User searchUser(String name){
		return repo.getUserByName(name);
	}
	
	public String getMessage(String name){
		return getPrefix()+name;
	}

	private static String getPrefix(){
		return "Welcome ";
	}
	
	private static String getWelcomeMessage(String name,int i){
		return getPrefix()+name;
	}
}
