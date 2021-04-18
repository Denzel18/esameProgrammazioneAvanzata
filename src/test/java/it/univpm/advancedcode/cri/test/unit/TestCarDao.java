package it.univpm.advancedcode.cri.test.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import it.univpm.advancedcode.cri.model.dao.CarDao;
import it.univpm.advancedcode.cri.model.entities.Car;
import it.univpm.advancedcode.cri.test.DataServiceConfigTest;

public class TestCarDao {

	@Test
	public void createAndDeleteByTarga() {
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DataServiceConfigTest.class)) {
			SessionFactory sf = ctx.getBean("sessionFactory", SessionFactory.class);
			CarDao carDao=ctx.getBean("carDao",CarDao.class);

			Session s=sf.openSession();
			carDao.setSession(s);

			s.beginTransaction();
			Car c1 = carDao.create((long)1, "AX311TY", "FIAT", "DUCATO", "X1LS22111", 3000, "Emergenza", 2, "DIESEL");
			s.getTransaction().commit();

			assertEquals(carDao.getParcoMezzi().size(),1);
			assertEquals(carDao.getByTarga("AX311TY").getTarga(),"AX311TY");

			s.beginTransaction();
			carDao.delete(c1);
			s.getTransaction().commit();

			assertEquals(carDao.getParcoMezzi().size(),0);
			assertNull(carDao.getByTarga("AX311TY"));
		}
	}

	@Test
	public void noCarsAtBeginning() {
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DataServiceConfigTest.class)) {
			SessionFactory sf = ctx.getBean("sessionFactory", SessionFactory.class);
			CarDao carDao=ctx.getBean("carDao",CarDao.class);
			Session s  = sf.openSession();
			carDao.setSession(s);
			assertEquals(carDao.getParcoMezzi().size(), 0);
		}
	}

	@Test public void testGetByTarga() { 
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DataServiceConfigTest.class)) {
			SessionFactory sf = ctx.getBean("sessionFactory", SessionFactory.class);
			CarDao carDao=ctx.getBean("carDao",CarDao.class);


			Session s=sf.openSession(); carDao.setSession(s);

			s.beginTransaction(); 
			Car c1 = carDao.create((long)1, "AX311TY", "FIAT", "DUCATO", "X1LS22111", 3000, "Emergenza", 2, "DIESEL");
			s.getTransaction().commit();

			try { 
				assertEquals(carDao.getParcoMezzi().size(),1);
				assertEquals(carDao.getByTarga("AX311TY"),c1); 
			} catch(Exception e) {
				fail("Exception not excepted: COGLIONEEEE 222"+e.getMessage()); } 
		} 
	}

	
	/** 
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	} 


	
	/** 
	 * @throws Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}


}
