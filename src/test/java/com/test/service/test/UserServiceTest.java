package com.test.service.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.datamodel.User;
import com.test.repo.UserRepo;
import com.test.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@MockBean
	private UserRepo repo;

	@Autowired
	private UserService service;

	@Test
	public void saveUserTest() {
		User user = new User(1, "Dhananjaya", "user");
		User user1 = new User(2, "Ajay", "user");
		Mockito.when(repo.save(user)).thenReturn(user);

		String saveResult = service.saveUser(user);
		String saveResult1 = service.saveUser(user1);

		Assert.assertEquals(" User Saved !!", saveResult);
		Assert.assertEquals("User Not Saved !!", saveResult1);
	}

	@Test
	public void searchUserTest() {
		User user = new User(1, "Dhananjaya", "user");
		Mockito.when(repo.getUserByName("Dhananjaya")).thenReturn(user);

		User data = service.searchUser("Dhananjaya");
		Assert.assertEquals(user, data);
	}

	@Test
	public void getMessageTest() {
		String message = service.getMessage("Dhananjaya");
		Assert.assertEquals("Welcome Dhananjaya", message);
	}
}
