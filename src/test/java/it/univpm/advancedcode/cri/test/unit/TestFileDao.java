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
import it.univpm.advancedcode.cri.model.dao.FileDao;
import it.univpm.advancedcode.cri.model.dao.UserDao;
import it.univpm.advancedcode.cri.model.entities.Documentazione;
import it.univpm.advancedcode.cri.model.entities.File;
import it.univpm.advancedcode.cri.test.DataServiceConfigTest;

public class TestFileDao {
	
	@Test
	void createAndDelete() throws ParseException {
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DataServiceConfigTest.class)) {
			SessionFactory sf = ctx.getBean("sessionFactory", SessionFactory.class);
			FileDao fileDao=ctx.getBean("fileDao",FileDao.class);
			DocumentazioneDao documentazioneDao=ctx.getBean("documentazioneDao",DocumentazioneDao.class);
			UserDao userDao=ctx.getBean("userDao",UserDao.class);
			CarDao carDao=ctx.getBean("carDao",CarDao.class);
			
			Session s=sf.openSession();
			fileDao.setSession(s);
			documentazioneDao.setSession(s);
			userDao.setSession(s);
			
			s.beginTransaction();
			LocalDate data1 = LocalDate.of(2021,11,20); 
			Documentazione doc1 = documentazioneDao.create("TITOLO", "DESCRIZIONE......", data1, 90);
			s.getTransaction().commit();
			
			assertEquals(fileDao.getAll().size(),1);
			assertEquals(fileDao.getById(1),a);
			
			s.beginTransaction();
			fileDao.delete(a);
			s.getTransaction().commit();
			
			assertEquals(fileDao.getAll().size(),0);
			try {
				File notFound=fileDao.getById(1);
				assertEquals(notFound,null);
			} catch(Exception e) {
				assertTrue(true);
			}
			
		}
	}

	@Test
	void createAndFind() throws ParseException {
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DataServiceConfigTest.class)) {
			SessionFactory sf = ctx.getBean("sessionFactory", SessionFactory.class);
			FileDao fileDao=ctx.getBean("fileDao",FileDao.class);
			DocumentazioneDao documentazioneDao=ctx.getBean("documentazioneDao",DocumentazioneDao.class);
			UserDao userDao=ctx.getBean("userDao",UserDao.class);
			CarDao carDao=ctx.getBean("carDao",CarDao.class);
			
			Session s=sf.openSession();
			fileDao.setSession(s);
			documentazioneDao.setSession(s);
			userDao.setSession(s);
			
			s.beginTransaction();
			LocalDate data1 = LocalDate.of(2021,11,20); 
			Documentazione doc1 = documentazioneDao.create("TITOLO", "DESCRIZIONE......", data1, 90);
			File a=fileDao.create("DESCRIZIONE", doc1, "file1.jpg",true);
			s.getTransaction().commit();
			
			try {
				fileDao.getById(a.getId());
			} catch(Exception e) {
				fail("Exception not expected: " + e.getMessage());
			}
			try {
				File notFound=fileDao.getById(999);
				assertEquals(notFound,null);
			} catch(Exception e) {
				assertTrue(true);
			}
			List<File> allFiles=fileDao.getAll();
			assertEquals(allFiles.size(), 1);
			}
		}
	
	@Test
	void createAndUpdate() throws ParseException {
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DataServiceConfigTest.class)) {
			SessionFactory sf = ctx.getBean("sessionFactory", SessionFactory.class);
			FileDao fileDao=ctx.getBean("fileDao",FileDao.class);
			DocumentazioneDao documentazioneDao=ctx.getBean("documentazioneDao",DocumentazioneDao.class);
			UserDao userDao=ctx.getBean("userDao",UserDao.class);
			CarDao carDao=ctx.getBean("carDao",CarDao.class);
			
			Session s=sf.openSession();
			fileDao.setSession(s);
			documentazioneDao.setSession(s);
			userDao.setSession(s);
			
			s.beginTransaction();
			LocalDate data1 = LocalDate.of(2021,11,20); 
			Documentazione doc1 = documentazioneDao.create("TITOLO", "DESCRIZIONE......", data1, 90);

			File a=fileDao.create("DESCRIZIONE", doc1, "file1.jpg",true);
			s.getTransaction().commit();
			
			assertEquals(fileDao.getAll().size(),1);
			assertEquals(fileDao.getByName("post1_file1.jpg").getName(),"post1_file1.jpg");
			
			s.beginTransaction();
			a.setName("post1_modifica.jpg");
			fileDao.update(a);
			s.getTransaction().commit();
			
			assertEquals(fileDao.getAll().size(),1);
			assertEquals(fileDao.getByName("post1_modifica.jpg").getName(),"post1_modifica.jpg");
		}
	}
		
	@Test
	void noFilesAtBeginning() {
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DataServiceConfigTest.class)) {
			SessionFactory sf = ctx.getBean("sessionFactory", SessionFactory.class);
			FileDao fileDao=ctx.getBean("fileDao",FileDao.class);
			Session s  = sf.openSession();
			fileDao.setSession(s);
			assertEquals(fileDao.getAll().size(), 0);
		}
	}
		
	@BeforeEach
	void setUp() throws Exception {
	}
	
	@AfterEach
	void tearDown() throws Exception {
	}
	
}

