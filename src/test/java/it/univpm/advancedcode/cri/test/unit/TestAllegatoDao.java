package it.univpm.advancedcode.cri.test.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.text.ParseException;
import java.time.LocalDate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import it.univpm.advancedcode.cri.model.dao.AllegatoDao;
import it.univpm.advancedcode.cri.model.dao.CarDao;
import it.univpm.advancedcode.cri.model.dao.DocumentazioneDao;
import it.univpm.advancedcode.cri.model.dao.FileDao;
import it.univpm.advancedcode.cri.model.dao.LinkDao;
import it.univpm.advancedcode.cri.model.dao.UserDao;
import it.univpm.advancedcode.cri.model.entities.Car;
import it.univpm.advancedcode.cri.model.entities.Documentazione;
import it.univpm.advancedcode.cri.model.entities.File;
import it.univpm.advancedcode.cri.model.entities.Link;
import it.univpm.advancedcode.cri.model.entities.User;
import it.univpm.advancedcode.cri.test.DataServiceConfigTest;

public class TestAllegatoDao {
	private final static String DESCRIPTION = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras " +
			"tempus magna vel posuere cursus.";

	@Test
	void deleteAllegato() throws ParseException {
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DataServiceConfigTest.class)) {
			SessionFactory sf = ctx.getBean("sessionFactory", SessionFactory.class);
			LinkDao linkDao=ctx.getBean("linkDao",LinkDao.class);
			DocumentazioneDao documentazioneDao=ctx.getBean("documentazioneDao",DocumentazioneDao.class);
			FileDao fileDao=ctx.getBean("fileDao",FileDao.class);
			UserDao userDao=ctx.getBean("userDao",UserDao.class);
			AllegatoDao allegatoDao=ctx.getBean("allegatoDao",AllegatoDao.class);
			CarDao carDao=ctx.getBean("carDao",CarDao.class);

			Session s=sf.openSession();
			linkDao.setSession(s);
			documentazioneDao.setSession(s);
			fileDao.setSession(s);
			userDao.setSession(s);
			allegatoDao.setSession(s);

			s.beginTransaction();

			User user1 = userDao.create("mario98", "12345678", "Mario", "Rossi", "admin");
			LocalDate data1 = LocalDate.of(2021,11,20); 
			Car c1 = carDao.create((long)1, "AX311TY", "FIAT", "DUCATO", "X1LS22111", 3000, "Emergenza", 2, "DIESEL");
			Documentazione doc1 = documentazioneDao.create("TITOLO", user1, DESCRIPTION, data1, 90, null, c1);

			Link link1=linkDao.create(DESCRIPTION, doc1, "https://www.univpm.it");
			File file1=fileDao.create(DESCRIPTION, doc1, "file1.jpg", true);
			s.getTransaction().commit();

			try {
				assertEquals(allegatoDao.getAll().size(),2);
				assertEquals(allegatoDao.getById(1),link1);
				assertEquals(allegatoDao.getById(2),file1);
			} catch(Exception e) {
				fail("Exception not excepted: "+e.getMessage());
			}

			s.beginTransaction();
			allegatoDao.delete(file1);
			allegatoDao.delete(link1);
			s.getTransaction().commit();

			try {
				assertEquals(allegatoDao.getAll().size(),0);
				assertEquals(allegatoDao.getById(1),null);
				assertEquals(allegatoDao.getById(2),null);
			} catch(Exception e) {
				fail("Exception not excepted: "+e.getMessage());
			}
		}
	}

	@Test
	void findAllegatoById() throws ParseException {
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DataServiceConfigTest.class)) {
			SessionFactory sf = ctx.getBean("sessionFactory", SessionFactory.class);
			LinkDao linkDao=ctx.getBean("linkDao",LinkDao.class);
			DocumentazioneDao documentazioneDao=ctx.getBean("documentazioneDao",DocumentazioneDao.class);
			FileDao fileDao=ctx.getBean("fileDao",FileDao.class);
			UserDao userDao=ctx.getBean("userDao",UserDao.class);
			AllegatoDao allegatoDao=ctx.getBean("allegatoDao",AllegatoDao.class);
			CarDao carDao=ctx.getBean("carDao",CarDao.class);

			Session s=sf.openSession();
			linkDao.setSession(s);
			documentazioneDao.setSession(s);
			fileDao.setSession(s);
			userDao.setSession(s);
			allegatoDao.setSession(s);

			s.beginTransaction();

			User user1 = userDao.create("mario98", "12345678", "Mario", "Rossi", "admin");
			LocalDate data1 = LocalDate.of(2021,11,20); 
			Car c1 = carDao.create((long)1, "AX311TY", "FIAT", "DUCATO", "X1LS22111", 3000, "Emergenza", 2, "DIESEL");
			Documentazione doc1 = documentazioneDao.create("TITOLO", user1, DESCRIPTION, data1, 90, null, c1);
			
			Link link1=linkDao.create(DESCRIPTION, doc1, "https://www.univpm.it");
			File file1=fileDao.create(DESCRIPTION, doc1, "file1.jpg", true);
			s.getTransaction().commit();

			try {
				assertEquals(allegatoDao.getAll().size(),2);
				assertEquals(allegatoDao.getById(1),link1);
				assertEquals(allegatoDao.getById(2),file1);
			} catch(Exception e) {
				fail("Exception not excepted: "+e.getMessage());
			}
		}
	}

	@Test
	void noAllegatoAtBeginning() {
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DataServiceConfigTest.class)) {
			SessionFactory sf = ctx.getBean("sessionFactory", SessionFactory.class);
			AllegatoDao allegatoDao=ctx.getBean("allegatoDao",AllegatoDao.class);
			Session s  = sf.openSession();
			allegatoDao.setSession(s);
			assertEquals(allegatoDao.getAll().size(), 0);
		}
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void updateAttachment() throws ParseException {
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DataServiceConfigTest.class)) {
			SessionFactory sf = ctx.getBean("sessionFactory", SessionFactory.class);
			LinkDao linkDao=ctx.getBean("linkDao",LinkDao.class);
			DocumentazioneDao documentazioneDao=ctx.getBean("documentazioneDao",DocumentazioneDao.class);
			FileDao fileDao=ctx.getBean("fileDao",FileDao.class);
			UserDao userDao=ctx.getBean("userDao",UserDao.class);
			AllegatoDao allegatoDao=ctx.getBean("allegatoDao",AllegatoDao.class);
			CarDao carDao=ctx.getBean("carDao",CarDao.class);

			Session s=sf.openSession();
			linkDao.setSession(s);
			documentazioneDao.setSession(s);
			fileDao.setSession(s);
			userDao.setSession(s);
			allegatoDao.setSession(s);

			s.beginTransaction();

			User user1 = userDao.create("mario98", "12345678", "Mario", "Rossi", "admin");
			LocalDate data1 = LocalDate.of(2021,11,20); 
			Car c1 = carDao.create((long)1, "AX311TY", "FIAT", "DUCATO", "X1LS22111", 3000, "Emergenza", 2, "DIESEL");
			Documentazione doc1 = documentazioneDao.create("TITOLO", user1, DESCRIPTION, data1, 90, null, c1);
			
			Link link1=linkDao.create(DESCRIPTION, doc1, "https://www.univpm.it");
			File file1=fileDao.create(DESCRIPTION, doc1, "file1.jpg", true);
			s.getTransaction().commit();

			try {
				assertEquals(allegatoDao.getAll().size(),2);
				assertEquals(allegatoDao.getById(1),link1);
				assertEquals(allegatoDao.getById(2),file1);
			} catch(Exception e) {
				fail("Exception not excepted: "+e.getMessage());
			}

			s.beginTransaction();
			file1.setName("file2.jpg");
			allegatoDao.update(file1);
			s.getTransaction().commit();

			try {
				assertEquals(allegatoDao.getAll().size(),2);
				assertEquals(allegatoDao.getById(1),link1);
				assertEquals(allegatoDao.getById(2),file1);
			} catch(Exception e) {
				fail("Exception not excepted: "+e.getMessage());
			}
		}
	}
}
