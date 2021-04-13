package it.univpm.advancedcode.cri.test.unit.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import it.univpm.advancedcode.cri.model.entities.User;
import it.univpm.advancedcode.cri.services.UserService;
import it.univpm.advancedcode.cri.test.DataServiceConfigTest;

public class TestUserService {

	private AnnotationConfigApplicationContext ctx;
	private UserService userService;

	@BeforeEach
	void openContext() {
		ctx = new AnnotationConfigApplicationContext(DataServiceConfigTest.class);    
		userService = ctx.getBean("userService", UserService.class);
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}


	@AfterEach
	void closeContext() {
		ctx.close();
	}

	@Test
	public void createAndUpdate() {
		User user1 = userService.create("mario98", "12345678", "Mario", "Rossi", "admin");
		assertEquals(userService.findAll().size(),1);
		user1.setFirstname("Roberto");
		userService.update(user1);
		assertEquals(userService.findAll().size(),1);
		assertEquals(user1.getFirstname(),"Roberto");
	}


	@Test 
	void testFindAll() {

		assertEquals(userService.findAll().size(),0);
		int i;
		for (i=0; i < 10; i++) {

			userService.create("user"+i, "1234", "Utente"+i, "Prova"+i, "admin");
		}

		assertEquals(userService.findAll().size(),i);


	}

	@Test
	public void createAndDelete() {

		User user1 = userService.create("mario98", "12345678", "Mario", "Rossi", "admin");


		assertEquals(userService.findAll().size(),1);


		userService.delete(user1);


		assertEquals(userService.findAll().size(),0);
	}


	@Test
	public void testGetRuolo() {
			User user1 = userService.create("mario1", "12345678", "Mario", "Rossi", "driver");
			User user2 = userService.create("mario2", "12345678", "Paolo", "Baggio", "admin");
			User user3 = userService.create("mario3", "12345678", "Paolo", "fffff", "account");


			try {
				assertEquals(user1.getRuolo(),"driver");
				assertEquals(user2.getRuolo(),"admin");
				assertEquals(user3.getRuolo(),"account");
			} catch(Exception e) {
				fail("Exception not excepted: "+e.getMessage());
			}
	}

}




