package it.univpm.advancedcode.cri.test.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.text.ParseException;
import java.time.LocalDate;
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
import it.univpm.advancedcode.cri.model.dao.UserDao;
import it.univpm.advancedcode.cri.model.entities.Car;
import it.univpm.advancedcode.cri.model.entities.User;
import it.univpm.advancedcode.cri.model.entities.Documentazione;
import it.univpm.advancedcode.cri.model.entities.Link;
import it.univpm.advancedcode.cri.test.DataServiceConfigTest;

public class TestLinkDao {

	@Test
	void createAndDelete() throws ParseException {
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DataServiceConfigTest.class)) {
			SessionFactory sf = ctx.getBean("sessionFactory", SessionFactory.class);
			LinkDao linkDao=ctx.getBean("linkDao",LinkDao.class);
			DocumentazioneDao documentazioneDao=ctx.getBean("documentazioneDao",DocumentazioneDao.class);
			UserDao userDao=ctx.getBean("userDao",UserDao.class);
			CarDao carDao=ctx.getBean("carDao",CarDao.class);

			Session s=sf.openSession();
			linkDao.setSession(s);
			documentazioneDao.setSession(s);
			userDao.setSession(s);
			carDao.setSession(s);

			s.beginTransaction();
			LocalDate data1 = LocalDate.of(2021,11,20); 
			User user = userDao.create("marioR", "marioR", "mario", "rossi", "admin");
			Car car = carDao.create((long)1, "AX311TY", "FIAT", "DUCATO", "X1LS22111", 3000, "Emergenza", 2, "DIESEL");
			Documentazione doc1 = documentazioneDao.create("TITOLO", user, "DESCRIZIONE......", data1, 90, car);
			Link link1=linkDao.create("DESCRIZIONE", doc1, "https://www.univpmLink.it");
			s.getTransaction().commit();

			assertEquals(linkDao.getAll().size(),1);

			s.beginTransaction();
			linkDao.delete(link1);
			s.getTransaction().commit();

			assertEquals(linkDao.getAll().size(),0);
		}
	}

	@Test
	void createAndFind() throws ParseException {
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DataServiceConfigTest.class)) {
			SessionFactory sf = ctx.getBean("sessionFactory", SessionFactory.class);
			LinkDao linkDao=ctx.getBean("linkDao",LinkDao.class);
			DocumentazioneDao documentazioneDao=ctx.getBean("documentazioneDao",DocumentazioneDao.class);
			UserDao userDao=ctx.getBean("userDao",UserDao.class);
			CarDao carDao=ctx.getBean("carDao",CarDao.class);

			Session s=sf.openSession();
			linkDao.setSession(s);
			documentazioneDao.setSession(s);
			userDao.setSession(s);
			carDao.setSession(s);

			s.beginTransaction();
			LocalDate data1 = LocalDate.of(2021,11,20); 
			User user = userDao.create("marioR", "marioR", "mario", "rossi", "admin");
			Car car = carDao.create((long)1, "AX311TY", "FIAT", "DUCATO", "X1LS22111", 3000, "Emergenza", 2, "DIESEL");
			Documentazione doc1 = documentazioneDao.create("TITOLO", user, "DESCRIZIONE......", data1, 90, car);
			Link link1=linkDao.create("DESCRIZIONE", doc1, "https://www.univpmLink.it");
			s.getTransaction().commit();

			try {
				linkDao.getById(link1.getId());
			} catch(Exception e) {
				fail("Exception not expected: " + e.getMessage());
			}
			try {
				Link notFound=linkDao.getById(999);
				assertEquals(notFound,null);
			} catch(Exception e) {
				assertTrue(true);
			}
			List<Link> allFiles=linkDao.getAll();
			assertEquals(allFiles.size(), 1);
		}
	}

	@Test
	void createAndUpdate() throws ParseException {
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DataServiceConfigTest.class)) {
			SessionFactory sf = ctx.getBean("sessionFactory", SessionFactory.class);
			LinkDao linkDao=ctx.getBean("linkDao",LinkDao.class);
			DocumentazioneDao documentazioneDao=ctx.getBean("documentazioneDao",DocumentazioneDao.class);
			UserDao userDao=ctx.getBean("userDao",UserDao.class);
			CarDao carDao=ctx.getBean("carDao",CarDao.class);

			Session s=sf.openSession();
			linkDao.setSession(s);
			documentazioneDao.setSession(s);
			userDao.setSession(s);
			carDao.setSession(s);

			s.beginTransaction();
			LocalDate data1 = LocalDate.of(2021,11,20); 
			User user = userDao.create("marioR", "marioR", "mario", "rossi", "admin");
			Car car = carDao.create((long)1, "AX311TY", "FIAT", "DUCATO", "X1LS22111", 3000, "Emergenza", 2, "DIESEL");
			Documentazione doc1 = documentazioneDao.create("TITOLO", user, "DESCRIZIONE......", data1, 90, car);
			Link link1=linkDao.create("DESCRIZIONE", doc1, "https://www.univpmLink.it");
			s.getTransaction().commit();

			assertEquals(linkDao.getAll().size(),1);

			s.beginTransaction();
			link1.setLink("https://www.google.com");
			linkDao.update(link1);
			s.getTransaction().commit();

			assertEquals(linkDao.getAll().size(),1);
			assertEquals(linkDao.getById(1).getLink(),"https://www.google.com");
			assertEquals(linkDao.getById(1),link1);
		}
	}

	@Test
	void noLinksAtBeginning() {
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DataServiceConfigTest.class)) {
			SessionFactory sf = ctx.getBean("sessionFactory", SessionFactory.class);
			LinkDao linkDao=ctx.getBean("linkDao",LinkDao.class);
			Session s  = sf.openSession();
			linkDao.setSession(s);
			assertEquals(linkDao.getAll().size(), 0);
		}
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

}
