package it.univpm.advancedcode.cri.test;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import it.univpm.advancedcode.cri.model.dao.*;
import it.univpm.advancedcode.cri.model.entities.*;

public class LoadDataTest {
	public static void main(String... args) {

		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
				DataServiceConfigTest.class)) {

			SessionFactory sf = ctx.getBean("sessionFactory", SessionFactory.class);

			UserDao userDao = ctx.getBean("userDao", UserDao.class);
			DocumentazioneDao documentazioneDao = ctx.getBean("documentazioneDao", DocumentazioneDao.class);
			CarDao carDao = ctx.getBean("carDao", CarDao.class);
			ManutenzioneDao manutenzioneDao = ctx.getBean("manutenzioneDao", ManutenzioneDao.class);
			PrenotazioneDao prenotazioneDao = ctx.getBean("prenotazioneDao", PrenotazioneDao.class);
			FileDao fileDao = ctx.getBean("fileDao", FileDao.class);
            LinkDao linkDao = ctx.getBean("linkDao", LinkDao.class);
            AllegatoDao allegatoDao = ctx.getBean("allegatoDao", AllegatoDao.class);


			try (Session session = sf.openSession()) {

				userDao.setSession(session);
				documentazioneDao.setSession(session);
				prenotazioneDao.setSession(session);
				carDao.setSession(session);
				manutenzioneDao.setSession(session);
				fileDao.setSession(session);
				linkDao.setSession(session);
				allegatoDao.setSession(session);



				// CREAZIONE UTENTI
				session.beginTransaction();
				User user1 = userDao.create("mario98", "12345678", "Mario", "Rossi","admin");
				User user2 = userDao.create("luca78", "12345678", "Luca", "Rossini", "driver");
				User user3 = userDao.create("matteoVerdi", "12345678", "Matteo", "Verdi", "account");
				User user4 = userDao.create("giov_bian", "12345678", "Giovanni", "Bianchi", "driver");
				User user5 = userDao.create("anto88", "12345678", "Antonio", "Bianchini", "driver");
				user1.setRuolo("admin");
				session.getTransaction().commit();
				assert userDao.findAll().size() == 5;



				//CREAZIONE VEICOLI 
				session.beginTransaction();
				Car c1 = carDao.create((long) 1, "TX123TY", "FIAT", "DOBLO", "11119090", 4000, "emergenza", 3, "diesel");
				Car c2 = carDao.create((long) 12, "TX333TY", "FIAT", "CAMION", "22229090", 3800, "emergenza", 3, "elettrica");
				Car c3 = carDao.create((long) 14, "TX444TY", "IVECO", "DAILER", "33339090", 2500, "soccorso", 2, "diesel");
				Car c4 = carDao.create((long) 16, "TX555TY", "MERCEDES", "VAN", "44449090", 1400, "emergenza", 3, "benzina");
				Car c5 = carDao.create((long) 19, "TX666TY", "MERCEDES", "VAN", "55559090", 1400, "trasporto", 2, "diesel");
				session.getTransaction().commit();
				assert carDao.getParcoMezzi().size() == 5;


				//CREAZIONE DOCUMENTAZIONE
				session.beginTransaction(); 
				LocalDate data1 = LocalDate.of(2021,11,20); 
				
				Documentazione d1 =
						documentazioneDao.create("REVISIONE -TX123TY", "DESCRIZIONE",
								LocalDate.of(2020,10,10), (float)900.00);

				Documentazione d4 = documentazioneDao.create("REVISIONE -TX444TY",
						"DESCRIZIONE", data1, (float)920.00);
				
				Documentazione d5 = documentazioneDao.create("TAGLIANDO -TX123TY",
						"DESCRIZIONE", data1, (float)930.00);

				Documentazione d2 = documentazioneDao.create("BOLLO  -TX123TY",
						"DESCRIZIONE", data1, (float)950.00);

				Documentazione d3 = documentazioneDao.create("REVISIONE -TX123TY",
						"DESCRIZIONE", data1, (float)970.00);

				session.getTransaction().commit(); 
				assert documentazioneDao.getAll().size() == 5;


				//LINK - FILE - ALLEGATI
				session.beginTransaction();


				fileDao.create("DESCRIZIONE", d1, "file1.jpg", true);
				fileDao.create("DESCRIZIONE", d2, "file2.jpg", true);
				fileDao.create("DESCRIZIONE", d1, "file1.jpg", true);
				fileDao.create("DESCRIZIONE", d2, "file2.jpg", true);
				fileDao.create("DESCRIZIONE", d3, "file3.jpg", true);

				linkDao.create("DESCRIZIONE", d1, "https://www.univpm.it");
				linkDao.create("DESCRIZIONE", true, d2, "https://www.univpm.it");
				linkDao.create("DESCRIZIONE", d1, "https://www.univpm.it");
				linkDao.create("DESCRIZIONE", true, d2, "https://www.univpm.it");
				linkDao.create("DESCRIZIONE", d3, "https://www.univpm.it");

				session.getTransaction().commit();

				//assert allegatoDao.getAll().size() == 10;



			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
	}

}
