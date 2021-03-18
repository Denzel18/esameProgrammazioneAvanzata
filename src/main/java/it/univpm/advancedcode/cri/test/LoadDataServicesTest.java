package it.univpm.advancedcode.cri.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import it.univpm.advancedcode.cri.model.entities.Car;
import it.univpm.advancedcode.cri.model.entities.Documentazione;
import it.univpm.advancedcode.cri.model.entities.User;
import it.univpm.advancedcode.cri.services.AllegatoService;
import it.univpm.advancedcode.cri.services.CarService;
import it.univpm.advancedcode.cri.services.DocumentazioneService;
import it.univpm.advancedcode.cri.services.FileService;
import it.univpm.advancedcode.cri.services.LinkService;
import it.univpm.advancedcode.cri.services.ManutenzioneService;
import it.univpm.advancedcode.cri.services.PrenotazioneService;
import it.univpm.advancedcode.cri.services.UserService;
import it.univpm.advancedcode.cri.test.DataServiceConfigTest;


public class LoadDataServicesTest {
	private final static String DESCRIPTION = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras "
			+ "tempus magna vel posuere cursus. Sed ultricies nunc purus, et maximus eros accumsan sit amet. Donec "
			+ "diam nisl, consectetur non nisl vel, condimentum finibus est.";

	private final static String TITLE = "Lorem ipsum dolor sit amet.";

	public static void main(String... args) throws ParseException {

        try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
        		DataServiceConfigTest.class)) {

            UserService userService = ctx.getBean("userService", UserService.class);
            PrenotazioneService prenotazioneService = ctx.getBean("prenotazioneService", PrenotazioneService.class);
            ManutenzioneService manutenzioneService = ctx.getBean("manutenzioneService", ManutenzioneService.class);
            LinkService linkService = ctx.getBean("linkService", LinkService.class);
            FileService fileService = ctx.getBean("fileService", FileService.class);
            DocumentazioneService documentazioneService = ctx.getBean("documentazioneService", DocumentazioneService.class);
            CarService carService = ctx.getBean("carService", CarService.class);
            AllegatoService allegatoService = ctx.getBean("allegatoService", AllegatoService.class);
//            
            //utenti
            User user1 = userService.create("mario98", "12345678", "Mario", "Rossi", "admin");
            User user5 = userService.create("anto88", "12345678", "Antonio", "Bianchini", "driver");
            
            assert userService.findAll().size() == 2;
            
            
            //veicoli 
            
            Car c1 = carService.create("AX311TY", "FIAT", "DUCATO", "X1LS22331", 3000, "Emergenza", 2, "DIESEL");
            Car c5 = carService.create("AX335TY", "FIAT", "DUCATO", "X1LS11121", 3400, "SOCCORSO", 2, "DIESEL");
            
            assert carService.getAll().size() == 2;
            
            
            //documentazione - allegati - file 
            String dataS1= "07/03/2023";
            Date date1 =new SimpleDateFormat("dd/MM/yyyy").parse(dataS1); 
            
            String dataS2= "07/08/2023";
            Date date2 =new SimpleDateFormat("dd/MM/yyyy").parse(dataS2); 
            Documentazione d1 = documentazioneService.create(TITLE, user1, DESCRIPTION, date1, 90, null);
            Documentazione d2 = documentazioneService.create(TITLE, user5, DESCRIPTION, date2, 180, null);
            
            fileService.create(DESCRIPTION, d1, "file1.jpg", true);
            fileService.create(DESCRIPTION, d2, "file2.jpg", true);
            
            linkService.create(DESCRIPTION, d1, "https://www.univpm.it");
            linkService.create(DESCRIPTION, d2, "https://www.univpm.it");
            
            assert allegatoService.getAll().size() == 4;

        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

    }
}

