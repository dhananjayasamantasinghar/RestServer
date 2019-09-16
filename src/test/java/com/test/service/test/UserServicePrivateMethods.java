package com.test.service.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServicePrivateMethods {

	@Autowired
	private UserService userService;

	@Test
	public void getWelcomeMessageTest() throws Exception {

		String res = ReflectionUtil.callPrivateMethod(userService, "getPrefix", String.class);

		String resStatic = ReflectionUtil.callPrivateMethod(UserService.class, "getWelcomeMessage", String.class,
				"Dhananjaya", 1);

		Assert.assertEquals("Welcome ", res);
		Assert.assertEquals("Welcome Dhananjaya", resStatic);

	}
}
