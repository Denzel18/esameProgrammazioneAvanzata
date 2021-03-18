package it.univpm.advancedcode.cri.test.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
import it.univpm.advancedcode.cri.model.entities.User;
import it.univpm.advancedcode.cri.test.DataServiceConfigTest;

public class TestDocumentazioneDao {
	@Test
	void deleteDocumentazione() throws ParseException {
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DataServiceConfigTest.class)) {

			SessionFactory sf = ctx.getBean("sessionFactory", SessionFactory.class);
			DocumentazioneDao documentazioneDao=ctx.getBean("documentazioneDao",DocumentazioneDao.class);
			FileDao fileDao=ctx.getBean("fileDao",FileDao.class);
			UserDao userDao=ctx.getBean("userDao",UserDao.class);
			AllegatoDao allegatoDao=ctx.getBean("allegatoDao",AllegatoDao.class);
			CarDao carDao=ctx.getBean("carDao",CarDao.class);

			Session s=sf.openSession();
			documentazioneDao.setSession(s);
			fileDao.setSession(s);
			userDao.setSession(s);
			allegatoDao.setSession(s);
			carDao.setSession(s);

			s.beginTransaction();

			User user1 = userDao.create("mario98", "12345678", "Mario", "Rossi", "admin");
			String dataS1= "07/08/2023";
			Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(dataS1); 

			Car c1 = carDao.create((long)1, "AX311TY", "FIAT", "DUCATO", "X1LS22111", 3000, "Emergenza", 2, "DIESEL");
			Documentazione doc1 = documentazioneDao.create("TITOLO", user1, "DESCRIZIONE", date1, 90, c1);

			s.getTransaction().commit();

			try {
				assertEquals(allegatoDao.getAll().size(),1);
			} catch(Exception e) {
				fail("Exception not excepted: "+e.getMessage());
			}

			s.beginTransaction();
			documentazioneDao.delete(doc1);
			s.getTransaction().commit();

			try {
				assertEquals(allegatoDao.getAll().size(),0);
			} catch(Exception e) {
				fail("Exception not excepted: "+e.getMessage());
			}
		}
	}

	@Test
	void findDocumentoById() throws ParseException {
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
			carDao.setSession(s);

			s.beginTransaction();
			User user1 = userDao.create("mario98", "12345678", "Mario", "Rossi", "admin");
			String dataS1= "07/08/2023";
			Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(dataS1); 

			Car c1 = carDao.create((long)1, "AX311TY", "FIAT", "DUCATO", "X1LS22111", 3000, "Emergenza", 2, "DIESEL");
			Documentazione doc1 = documentazioneDao.create("TITOLO", user1, "DESCRIZIONE", date1, 90, c1);

			s.getTransaction().commit();

			try {
				assertEquals(allegatoDao.getAll().size(),1);
			} catch(Exception e) {
				fail("Exception not excepted: "+e.getMessage());
			}
		}
	}

	@Test
	void noDocumentazioneAtBeginning() {
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DataServiceConfigTest.class)) {
			SessionFactory sf = ctx.getBean("sessionFactory", SessionFactory.class);
			DocumentazioneDao documentazioneDao=ctx.getBean("documentazioneDao",DocumentazioneDao.class);
			Session s  = sf.openSession();
			documentazioneDao.setSession(s);
			assertEquals(documentazioneDao.getAll().size(), 0);
		}
	}


	/**
	 * @throws ParseException
	 */
	@Test
	void updateDocumentazione() throws ParseException {
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DataServiceConfigTest.class)) {
			SessionFactory sf = ctx.getBean("sessionFactory", SessionFactory.class);
			DocumentazioneDao documentazioneDao=ctx.getBean("documentazioneDao",DocumentazioneDao.class);
			FileDao fileDao=ctx.getBean("fileDao",FileDao.class);
			UserDao userDao=ctx.getBean("userDao",UserDao.class);
			AllegatoDao allegatoDao=ctx.getBean("allegatoDao",AllegatoDao.class);
			CarDao carDao=ctx.getBean("carDao",CarDao.class);

			Session s=sf.openSession();
			documentazioneDao.setSession(s);
			fileDao.setSession(s);
			userDao.setSession(s);
			allegatoDao.setSession(s);
			carDao.setSession(s);

			s.beginTransaction();

			User user1 = userDao.create("mario98", "12345678", "Mario", "Rossi", "admin");
			String dataS1= "07/08/2023";
			Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(dataS1); 

			Car c1 = carDao.create((long)1, "AX311TY", "FIAT", "DUCATO", "X1LS22111", 3000, "Emergenza", 2, "DIESEL");
			Documentazione doc1 = documentazioneDao.create("TITOLO", user1, "DESCRIZIONE", date1, 90, c1);

			s.getTransaction().commit();

			try {
				assertEquals(allegatoDao.getAll().size(),1);
			} catch(Exception e) {
				fail("Exception not excepted: "+e.getMessage());
			}

			s.beginTransaction();
			doc1.setTitolo("NUOVO TITOLO");
			documentazioneDao.update(doc1);
			s.getTransaction().commit();

			try {
				assertEquals(allegatoDao.getAll().size(),1);
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
