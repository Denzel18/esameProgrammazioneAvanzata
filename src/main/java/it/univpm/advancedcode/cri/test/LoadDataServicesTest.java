package it.univpm.advancedcode.cri.test;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import it.univpm.advancedcode.cri.model.entities.Car;
import it.univpm.advancedcode.cri.model.entities.Documentazione;
import it.univpm.advancedcode.cri.model.entities.Manutenzione;
import it.univpm.advancedcode.cri.model.entities.Prenotazione;
import it.univpm.advancedcode.cri.model.entities.User;
import it.univpm.advancedcode.cri.services.AllegatoService;
import it.univpm.advancedcode.cri.services.CarService;
import it.univpm.advancedcode.cri.services.DocumentazioneService;
import it.univpm.advancedcode.cri.services.FileService;
import it.univpm.advancedcode.cri.services.LinkService;
import it.univpm.advancedcode.cri.services.ManutenzioneService;
import it.univpm.advancedcode.cri.services.PrenotazioneService;
import it.univpm.advancedcode.cri.services.UserService;


public class LoadDataServicesTest {
	private final static String DESCRIPTION = "Descrizione ....";

	private final static String TITLE = "Lorem ipsum dolor sit amet.";

	public static void main(String... args) throws ParseException {

		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
				DataServiceConfigTest.class)) {

			UserService userService = ctx.getBean("userService", UserService.class);
			PrenotazioneService prenotazioneService = ctx.getBean("prenotazioneService", PrenotazioneService.class);
			ManutenzioneService manutenzioneService = ctx.getBean("manutenzioneService", ManutenzioneService.class);
			DocumentazioneService documentazioneService = ctx.getBean("documentazioneService", DocumentazioneService.class);
			CarService carService = ctx.getBean("carService", CarService.class);
			FileService fileService = ctx.getBean("fileService", FileService.class);
			LinkService linkService = ctx.getBean("linkService", LinkService.class);
			AllegatoService allegatoService = ctx.getBean("allegatoService", AllegatoService.class);

			//utenti
			User user1 = userService.create("mario98", "12345678", "Mario", "Rossi", "admin");
			User user5 = userService.create("anto88", "12345678", "Antonio", "Bianchini", "driver");

			assert userService.findAll().size() == 2;


			//veicoli 
			Car c1 = carService.create((long)1,  "AX311TY", "FIAT", "DUCATO", "X1LS22331", 3000, "Emergenza", 2, "DIESEL");
			Car c5 = carService.create((long)3, "AX335TY", "FIAT", "DUCATO", "X1LS11121", 3400, "SOCCORSO", 2, "DIESEL");

			assert carService.getAll().size() == 2;


			//documentazione - allegati - file 

			try {
				LocalDate data1 = LocalDate.of(2021,10,20); 
				LocalDate data2 = LocalDate.of(2021,11,20);

				Car car = carService.create(1, "AZ123TY", "FIAT", "DOBLO", "124575323", 5000, "soccorso", 3, "DIESEL");

				Documentazione d1 = documentazioneService.create(TITLE, user5, DESCRIPTION, data1, 90, car) ;
				Documentazione d2 = documentazioneService.create(TITLE, user5, DESCRIPTION, data2, 180, car);

				fileService.create(DESCRIPTION, d1, "file1.jpg", true);
				fileService.create(DESCRIPTION, d2, "file2.jpg", true);

				linkService.create(DESCRIPTION, d1, "https://www.univpm.it");
				linkService.create(DESCRIPTION, d2, "https://www.univpm.it");
				assert allegatoService.getAll().size() == 4;
			}catch (Exception e) {
				System.out.println("ERRORE DOCUMENTAZIONE");
			}


			//documentazione get all test 

			List <Documentazione> allDocuments = documentazioneService.getAll();
			for( Documentazione a : allDocuments){
				System.out.println("TITOLO : "+a.getTitolo());
			}
			//prenotazione

			try {
				LocalDate data1 = LocalDate.of(2021,11,20); 
				LocalDate data2 = LocalDate.of(2021,11,20);

				LocalDate data3 = LocalDate.of(2021,11,25); 
				LocalDate data4 = LocalDate.of(2021,11,25);


				LocalTime t1 = LocalTime.of(17, 39);
				LocalTime t2 = LocalTime.of(18, 39);

				LocalTime t3 = LocalTime.of(17, 39);
				LocalTime t4 = LocalTime.of(19, 39);


				Prenotazione p1 = prenotazioneService.create(1, data1, data2,t1,t2,"DESCRIZIONE", c1, user1);
				Prenotazione p2 = prenotazioneService.create(1, data3, data4,t3,t4,"DESCRIZIONE", c5, user5);


			}catch (Exception e) {
				System.out.println("ERRORE PRENOTAZIONE");
			}



			//manutenzione 


			try {
				Car car = carService.create((long)3, "AX337TY", "FIAT", "DUCATO", "X1LS11121", 3400, "SOCCORSO", 2, "DIESEL");
				Car car2 = carService.create((long)5, "AX336TY", "FIAT", "DUCATO", "X1LS11121", 3400, "SOCCORSO", 2, "DIESEL");
				Manutenzione m1 = manutenzioneService.create(1,"tipomanutenzione",(float) 100, car);
				Manutenzione m2 = manutenzioneService.create(1,"tipomanutenzione",(float) 100, car2);
			}catch (Exception e) {
				System.out.println("ERRORE Manutenzione");
			}

		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
}
