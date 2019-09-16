package com.test.controller.test;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.controller.UserController;
import com.test.datamodel.User;
import com.test.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

	@MockBean
	private UserService service;

	@Autowired
	private UserController controller;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void getMessageTest() throws Exception {
		Mockito.when(service.getMessage("Dhananjaya")).thenReturn("Welcome Dhananjaya");

		// positive case
		mockMvc.perform(MockMvcRequestBuilders.get("/user/msg").param("name", "Dhananjaya"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("Welcome Dhananjaya"));

		// Negative Case
		mockMvc.perform(MockMvcRequestBuilders.get("/user/msg")
				.param("name", ""))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(""));
	}

	@Test
	public void addUserTest() throws Exception {
		Mockito.when(service.saveUser(Mockito.any())).thenReturn(" User Saved !!");

		ObjectMapper mapper = new ObjectMapper();
		String valueAsString = mapper.writeValueAsString(new User(1, "Dhananjaya", "user"));

		// Positive Case
		mockMvc.perform(MockMvcRequestBuilders.post("/user/add")
				.contentType(MediaType.APPLICATION_JSON).content(valueAsString))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(" User Saved !!"));

		// Negative Case
		mockMvc.perform(MockMvcRequestBuilders.post("/user/add")
				.contentType(MediaType.APPLICATION_JSON).content(""))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	public void getUserTest() throws Exception {
		User user = new User(1, "Dhananjaya", "user");
		Mockito.when(service.searchUser("Dhananjaya")).thenReturn(user);

		// Positive Case
		mockMvc.perform(MockMvcRequestBuilders.get("/user/get").param("name", "Dhananjaya"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name",Matchers.is("Dhananjaya")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.role",Matchers.is("user")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.id",Matchers.is(1)));
				

		// Negative Case
		mockMvc.perform(MockMvcRequestBuilders.get("/user/get")
				.param("name", "Aswini"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(""));
	}
}
