package it.univpm.advancedcode.cri.test.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import it.univpm.advancedcode.cri.model.dao.CarDao;
import it.univpm.advancedcode.cri.model.dao.PrenotazioneDao;
import it.univpm.advancedcode.cri.model.dao.UserDao;
import it.univpm.advancedcode.cri.model.entities.Car;
import it.univpm.advancedcode.cri.model.entities.User;
import it.univpm.advancedcode.cri.model.entities.Prenotazione;
import it.univpm.advancedcode.cri.test.DataServiceConfigTest;

public class TestPrenotazioneDao {

	
	/** 
	 * @throws ParseException
	 */
	@Test
	public void createAndDelete() throws ParseException {
  		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DataServiceConfigTest.class)) {
			SessionFactory sf = ctx.getBean("sessionFactory", SessionFactory.class);			
			PrenotazioneDao prenotazioneDao=ctx.getBean("prenotazioneDao",PrenotazioneDao.class);
			CarDao carDao=ctx.getBean("carDao",CarDao.class);
			UserDao userDao=ctx.getBean("userDao",UserDao.class);
			
			Session s=sf.openSession();
			prenotazioneDao.setSession(s);
			carDao.setSession(s);
			userDao.setSession(s);

			s.beginTransaction();
			LocalDate data1 = LocalDate.of(2021,11,20); 
			LocalDate data2 = LocalDate.of(2021,11,20);			
			LocalTime t1 = LocalTime.of(17, 39);
			LocalTime t2 = LocalTime.of(18, 39);
			Car car = carDao.create((long)1, "AX311TY", "FIAT", "DUCATO", "X1LS22111", 3000, "Emergenza", 2, "DIESEL");
			User user = userDao.create("denisberno", "berno", "denis", "bernovschi", "admin"); 
			Prenotazione prenotazione = prenotazioneDao.create(1,data1, data2, t1, t2, "DESCRIZIONE", car, user); 
			s.getTransaction().commit();
			
			assertEquals(prenotazioneDao.getAll().size(),1);
			
			try {
				assertEquals(prenotazioneDao.getAll().size(),1);
			} catch(Exception e) {
				fail("Exception not excepted: "+e.getMessage());
			}
			
			s.beginTransaction();
			prenotazioneDao.delete(prenotazione);
			s.getTransaction().commit();
			
			try {
				assertEquals(prenotazioneDao.getAll().size(),0);
				assertNull(prenotazioneDao.getById(1));
			} catch(Exception e) {
				fail("Exception not excepted: "+e.getMessage());
			}
  		}
	}

  	
	  /** 
	   * @throws ParseException
	   */
	  @Test
  	public void createAndFind() throws ParseException {
        try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DataServiceConfigTest.class)) {
			SessionFactory sf = ctx.getBean("sessionFactory", SessionFactory.class);			
			PrenotazioneDao prenotazioneDao=ctx.getBean("prenotazioneDao",PrenotazioneDao.class);
			CarDao carDao=ctx.getBean("carDao",CarDao.class);
			UserDao userDao=ctx.getBean("userDao",UserDao.class);
			
			Session s=sf.openSession();
			prenotazioneDao.setSession(s);
			carDao.setSession(s);
			userDao.setSession(s);

			s.beginTransaction();
			LocalDate data1 = LocalDate.of(2021,11,20); 
			LocalDate data2 = LocalDate.of(2021,11,20);			
			LocalTime t1 = LocalTime.of(17, 39);
			LocalTime t2 = LocalTime.of(18, 39);
			Car car = carDao.create((long)1, "AX311TY", "FIAT", "DUCATO", "X1LS22111", 3000, "Emergenza", 2, "DIESEL");
			User user = userDao.create("denisberno", "berno", "denis", "bernovschi", "admin"); 
			Prenotazione prenotazione = prenotazioneDao.create(1,data1, data2, t1, t2, "DESCRIZIONE", car, user); 
			s.getTransaction().commit();
			
			try {
				prenotazioneDao.getById(prenotazione.getId());
			} catch(Exception e) {
				fail("Exception not expected: " + e.getMessage());
			}
			try {
				Prenotazione notFound=prenotazioneDao.getById(999);
				assertNull(notFound);
			} catch(Exception e) {
				assertTrue(true);
			}
			List<Prenotazione> allPrenotazioni=prenotazioneDao.getAll();
			assertEquals(allPrenotazioni.size(), 1);
			}
  	}
  	
  	
	  /** 
	   * @throws ParseException
	   */
	  @Test
	public void createAndUpdate() throws ParseException {
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DataServiceConfigTest.class)) {
			SessionFactory sf = ctx.getBean("sessionFactory", SessionFactory.class);			
			PrenotazioneDao prenotazioneDao=ctx.getBean("prenotazioneDao",PrenotazioneDao.class);
			CarDao carDao=ctx.getBean("carDao",CarDao.class);
			UserDao userDao=ctx.getBean("userDao",UserDao.class);
			
			Session s=sf.openSession();
			prenotazioneDao.setSession(s);
			carDao.setSession(s);
			userDao.setSession(s);

			s.beginTransaction();
			LocalDate data1 = LocalDate.of(2021,11,20); 
			LocalDate data2 = LocalDate.of(2021,11,20);			
			LocalTime t1 = LocalTime.of(17, 39);
			LocalTime t2 = LocalTime.of(18, 39);
			Car car = carDao.create((long)1, "AX311TY", "FIAT", "DUCATO", "X1LS22111", 3000, "Emergenza", 2, "DIESEL");
			User user = userDao.create("denisberno", "berno", "denis", "bernovschi", "admin"); 
			Prenotazione prenotazione = prenotazioneDao.create(1,data1, data2, t1, t2, "DESCRIZIONE", car, user); 
			s.getTransaction().commit();
			
			List<Prenotazione> allPrenotazioni=prenotazioneDao.getAll();
			assertEquals(allPrenotazioni.size(), 1);
		}
	}
  	
  	@Test
  	public void noPrenotazioneAtBeginning() {
  		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DataServiceConfigTest.class)) {
			SessionFactory sf = ctx.getBean("sessionFactory", SessionFactory.class);
			PrenotazioneDao prenotazioneDao=ctx.getBean("prenotazioneDao",PrenotazioneDao.class);
			Session s  = sf.openSession();
			prenotazioneDao.setSession(s);
			assertEquals(prenotazioneDao.getAll().size(), 0);
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
