package it.univpm.advancedcode.cri.test.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.text.ParseException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import it.univpm.advancedcode.cri.model.dao.CarDao;
import it.univpm.advancedcode.cri.model.dao.ManutenzioneDao;
import it.univpm.advancedcode.cri.model.entities.Car;
import it.univpm.advancedcode.cri.model.entities.Manutenzione;
import it.univpm.advancedcode.cri.test.DataServiceConfigTest;

public class TestManutenzioneDao {
	@Test
	public void createAndDelete() throws ParseException {
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DataServiceConfigTest.class)) {
			SessionFactory sf = ctx.getBean("sessionFactory", SessionFactory.class);			

			ManutenzioneDao manutenzioneDao=ctx.getBean("manutenzioneDao",ManutenzioneDao.class);
			CarDao carDao=ctx.getBean("carDao",CarDao.class);

			Session s=sf.openSession();
			manutenzioneDao.setSession(s);
			carDao.setSession(s);


			s.beginTransaction();
			Car car = carDao.create(5, "PP006AA", "FIAT", "DUCATO", "P01210011", 4000, "SOCCORSO", 2, "ELETTRICO");
			Manutenzione manutenzione = manutenzioneDao.create(1,"STRAORDINARIA", (float) 900.00, car);   

			s.getTransaction().commit();

			assertEquals(manutenzioneDao.getAll().size(),1);


			try {
				assertEquals(manutenzioneDao.getAll().size(),1);
			} catch(Exception e) {
				fail("Exception not excepted: "+e.getMessage());
			}

			s.beginTransaction();
			manutenzioneDao.delete(manutenzione);
			s.getTransaction().commit();

			try {
				assertEquals(manutenzioneDao.getAll().size(),0);
				assertNull(manutenzioneDao.getById(1));
			} catch(Exception e) {
				fail("Exception not excepted: "+e.getMessage());
			}
		}
	}

	@Test
	public void createAndFind() throws ParseException {
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DataServiceConfigTest.class)) {
			SessionFactory sf = ctx.getBean("sessionFactory", SessionFactory.class);			

			ManutenzioneDao manutenzioneDao=ctx.getBean("manutenzioneDao",ManutenzioneDao.class);
			CarDao carDao=ctx.getBean("carDao",CarDao.class);

			Session s=sf.openSession();
			manutenzioneDao.setSession(s);
			carDao.setSession(s);


			s.beginTransaction();
			Car car = carDao.create(5, "PP006AA", "FIAT", "DUCATO", "P01210011", 4000, "SOCCORSO", 2, "ELETTRICO");
			Manutenzione manutenzione = manutenzioneDao.create(10,"STRAORDINARIA", (float) 900.00, car); 

			s.getTransaction().commit();

			try {
				manutenzioneDao.getById(manutenzione.getId());
			} catch(Exception e) {
				fail("Exception not expected: " + e.getMessage());
			}
			try {
				Manutenzione notFound=manutenzioneDao.getById(999);
				assertNull(notFound);
			} catch(Exception e) {
				assertTrue(true);
			}
			List<Manutenzione> allManutenzioni=manutenzioneDao.getAll();
			assertEquals(allManutenzioni.size(), 1);
		}
	}

	@Test
	public void createAndUpdate() throws ParseException {
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DataServiceConfigTest.class)) {
			SessionFactory sf = ctx.getBean("sessionFactory", SessionFactory.class);			

			ManutenzioneDao manutenzioneDao=ctx.getBean("manutenzioneDao",ManutenzioneDao.class);
			CarDao carDao=ctx.getBean("carDao",CarDao.class);

			Session s=sf.openSession();
			manutenzioneDao.setSession(s);
			carDao.setSession(s);


			s.beginTransaction();  
			Car car = carDao.create(5, "PP006AA", "FIAT", "DUCATO", "P01210011", 4000, "SOCCORSO", 2, "ELETTRICO");
			Manutenzione manutenzione = manutenzioneDao.create(1,"STRAORDINARIA", (float) 900.00, car);   

			s.getTransaction().commit();

			assertEquals(manutenzioneDao.getAll().size(),1);


			s.beginTransaction();
			manutenzione.setCostoManutenzione((float)850.00);
			manutenzioneDao.update(manutenzione);
			s.getTransaction().commit();

			assertEquals(manutenzioneDao.getById(1).getCostoManutenzione(),(float)850.00);
		}
	}

	@Test
	public void noManutenzioneAtBeginning() {
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DataServiceConfigTest.class)) {
			SessionFactory sf = ctx.getBean("sessionFactory", SessionFactory.class);
			ManutenzioneDao manutenzioneDao=ctx.getBean("manutenzioneDao",ManutenzioneDao.class);
			Session s  = sf.openSession();
			manutenzioneDao.setSession(s);
			assertEquals(manutenzioneDao.getAll().size(), 0);
		}
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

}
