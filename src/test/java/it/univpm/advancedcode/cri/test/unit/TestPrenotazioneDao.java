package it.univpm.advancedcode.cri.test.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import it.univpm.advancedcode.cri.model.dao.CarDao;
import it.univpm.advancedcode.cri.model.dao.DocumentazioneDao;
import it.univpm.advancedcode.cri.model.dao.LinkDao;
import it.univpm.advancedcode.cri.model.dao.PrenotazioneDao;
import it.univpm.advancedcode.cri.model.dao.UserDao;
import it.univpm.advancedcode.cri.model.entities.Car;
import it.univpm.advancedcode.cri.model.entities.Prenotazione;
import it.univpm.advancedcode.cri.model.entities.User;
import it.univpm.advancedcode.cri.test.DataServiceConfigTest;

public class TestPrenotazioneDao {
    @Test
	public void createAndDelete() throws ParseException {
   		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DataServiceConfigTest.class)) {
			SessionFactory sf = ctx.getBean("sessionFactory", SessionFactory.class);			
			LinkDao linkDao=ctx.getBean("linkDao",LinkDao.class);
			DocumentazioneDao documentazioneDao=ctx.getBean("documentazioneDao",DocumentazioneDao.class);
			PrenotazioneDao prenotazioneDao=ctx.getBean("prenotazioneDao",PrenotazioneDao.class);
			UserDao userDao=ctx.getBean("userDao",UserDao.class);
			CarDao carDao=ctx.getBean("carDao",CarDao.class);
			
			Session s=sf.openSession();
			linkDao.setSession(s);
			documentazioneDao.setSession(s);
			prenotazioneDao.setSession(s);
			userDao.setSession(s);
			carDao.setSession(s);
			
			s.beginTransaction();
			User user1 = userDao.create("mario98", "12345678", "Mario", "Rossi", "admin");
			User user2 = userDao.create("mario108", "12345678", "Carlo", "Rossi" , "driver");
			
            String dataS1= "07/08/2023";
            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(dataS1); 
            
            String dataS2= "07/08/2025";
            Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(dataS2); 
            
            Time time1 = Time.valueOf("20:30");
            Time time2 = Time.valueOf("20:40");
            
            Car c1 = carDao.create("AX311TY", "FIAT", "DUCATO", "X1LS22111", 3000, "Emergenza", 2, "DIESEL");
			
			Prenotazione prenotazione = prenotazioneDao.create(1,date1, date2, time1, time2, "DESCRIZIONE",user1); 
			
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

   	@Test
   	public void createAndFind() throws ParseException {
   		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DataServiceConfigTest.class)) {
			SessionFactory sf = ctx.getBean("sessionFactory", SessionFactory.class);			
			LinkDao linkDao=ctx.getBean("linkDao",LinkDao.class);
			DocumentazioneDao documentazioneDao=ctx.getBean("documentazioneDao",DocumentazioneDao.class);
			PrenotazioneDao prenotazioneDao=ctx.getBean("prenotazioneDao",PrenotazioneDao.class);
			UserDao userDao=ctx.getBean("userDao",UserDao.class);
			CarDao carDao=ctx.getBean("carDao",CarDao.class);
			
			Session s=sf.openSession();
			linkDao.setSession(s);
			documentazioneDao.setSession(s);
			prenotazioneDao.setSession(s);
			userDao.setSession(s);
			carDao.setSession(s);
			
			s.beginTransaction();
			User user1 = userDao.create("mario98", "12345678", "Mario", "Rossi", "admin");
            String dataS1= "07/08/2023";
            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(dataS1); 
            
            String dataS2= "07/08/2025";
            Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(dataS2); 
            
            Time time1 = Time.valueOf("20:30");
            Time time2 = Time.valueOf("20:40");
            
            Car c1 = carDao.create("AX311TY", "FIAT", "DUCATO", "X1LS22111", 3000, "Emergenza", 2, "DIESEL");
			
			Prenotazione prenotazione = prenotazioneDao.create(1,date1, date2, time1, time2, "DESCRIZIONE",user1);  


			s.getTransaction().commit();
			
			try {
				documentazioneDao.getById(prenotazione.getId());
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
   	
   	@Test
	public void createAndUpdate() throws ParseException {
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DataServiceConfigTest.class)) {
			SessionFactory sf = ctx.getBean("sessionFactory", SessionFactory.class);			
			LinkDao linkDao=ctx.getBean("linkDao",LinkDao.class);
			DocumentazioneDao documentazioneDao=ctx.getBean("documentazioneDao",DocumentazioneDao.class);
			PrenotazioneDao prenotazioneDao=ctx.getBean("prenotazioneDao",PrenotazioneDao.class);
			UserDao userDao=ctx.getBean("userDao",UserDao.class);
			CarDao carDao=ctx.getBean("carDao",CarDao.class);
			
			Session s=sf.openSession();
			linkDao.setSession(s);
			documentazioneDao.setSession(s);
			prenotazioneDao.setSession(s);
			userDao.setSession(s);
			carDao.setSession(s);
			
			s.beginTransaction();
			User user1 = userDao.create("mario98", "12345678", "Mario", "Rossi", "admin");
			User user2 = userDao.create("mario108", "12345678", "Carlo", "Rossi", "admin");
			
            String dataS1= "07/08/2023";
            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(dataS1); 
            
            String dataS2= "07/08/2025";
            Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(dataS2); 
            
            Time time1 = Time.valueOf("20:30");
            Time time2 = Time.valueOf("20:40");
            
            Car c1 = carDao.create("AX311TY", "FIAT", "DUCATO", "X1LS22111", 3000, "Emergenza", 2, "DIESEL");
			
			Prenotazione prenotazione = prenotazioneDao.create(1,date1, date2, time1, time2, "DESCRIZIONE",user1); 
			
			s.getTransaction().commit();
			
			assertEquals(prenotazioneDao.getAll().size(),1);
			
			
			s.beginTransaction();
			prenotazione.setUtente(user2);
			prenotazioneDao.update(prenotazione);
			s.getTransaction().commit();
			
			assertEquals(prenotazioneDao.getById(1).getUtente(),user2);
		}
	}
   	
   	@Test
   	public void noPrenotazioneAtBeginning() {
   		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DataServiceConfigTest.class)) {
			SessionFactory sf = ctx.getBean("sessionFactory", SessionFactory.class);
			PrenotazioneDao postDao=ctx.getBean("prenotazioneDao",PrenotazioneDao.class);
			Session s  = sf.openSession();
			postDao.setSession(s);
			assertEquals(postDao.getAll().size(), 0);
   			}
   	}
  
	@BeforeEach
   	void setUp() throws Exception {
   	}

	@AfterEach
   	void tearDown() throws Exception {
   	}

}
