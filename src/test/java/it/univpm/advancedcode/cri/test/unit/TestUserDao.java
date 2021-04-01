package it.univpm.advancedcode.cri.test.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import it.univpm.advancedcode.cri.model.dao.UserDao;
import it.univpm.advancedcode.cri.model.entities.User;
import it.univpm.advancedcode.cri.test.DataServiceConfigTest;

public class TestUserDao {

	@Test public void createAndDelete() { 
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DataServiceConfigTest.class)) {
			SessionFactory sf = ctx.getBean("sessionFactory", SessionFactory.class);
			UserDao userDao=ctx.getBean("userDao",UserDao.class);
			Session s=sf.openSession(); 
			userDao.setSession(s);

			s.beginTransaction(); 
			User user1 = userDao.create("mario98", "12345678","Mario", "Rossi", "admin"); 
			s.getTransaction().commit();
			assertEquals(userDao.findAll().size(),1);

			s.beginTransaction(); 
			userDao.delete(user1); 
			s.getTransaction().commit();

			assertEquals(userDao.findAll().size(),0); 
		} 
	}

	@Test public void createAndUpdate() { try (AnnotationConfigApplicationContext
			ctx = new AnnotationConfigApplicationContext(DataServiceConfigTest.class)) {
		SessionFactory sf = ctx.getBean("sessionFactory", SessionFactory.class);
		UserDao userDao=ctx.getBean("userDao",UserDao.class);

		Session s=sf.openSession(); userDao.setSession(s);


		s.beginTransaction(); 
		User user1 = userDao.create("mario98", "12345678","Mario", "Rossi", "admin"); 
		s.getTransaction().commit();

		assertEquals(userDao.findAll().size(),1);

		s.beginTransaction(); user1.setFirstname("Roberto"); userDao.update(user1);
		s.getTransaction().commit();

		assertEquals(userDao.findAll().size(),1);
		assertEquals(user1.getFirstname(),"Roberto"); } }

	@Test
	public void noUsersAtBeginning() {
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DataServiceConfigTest.class)) {
			SessionFactory sf = ctx.getBean("sessionFactory", SessionFactory.class);
			UserDao userDao=ctx.getBean("userDao",UserDao.class);
			Session s  = sf.openSession();
			userDao.setSession(s);
			assertEquals(userDao.findAll().size(), 0);
		}
	}

	@Test
	public void testFindUserByUsername() {
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DataServiceConfigTest.class)) {
			SessionFactory sf = ctx.getBean("sessionFactory", SessionFactory.class);
			UserDao userDao=ctx.getBean("userDao",UserDao.class);

			Session s=sf.openSession();
			userDao.setSession(s);

			s.beginTransaction();
			User user1 = userDao.create("mario98", "12345678", "Mario", "Rossi", "driver");
			User user2 = userDao.create("paolo97", "12345678", "Paolo", "Baggio", "admin");
			s.getTransaction().commit();

			try {
				assertEquals(userDao.findUserByUsername("mario98"),user1);
				assertEquals(userDao.findUserByUsername("paolo97"),user2);
			} catch(Exception e) {
				fail("Exception not excepted: "+e.getMessage());
			}
		}
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}



}
