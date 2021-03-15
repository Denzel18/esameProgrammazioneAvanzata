package it.univpm.advancedcode.cri.test.unit.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import it.univpm.advancedcode.cri.model.entities.Car;
import it.univpm.advancedcode.cri.model.entities.Documentazione;
import it.univpm.advancedcode.cri.model.entities.File;
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

public class TestFileService {

	private AnnotationConfigApplicationContext ctx;
	private AllegatoService allegatoService;
	private CarService carService;
	private DocumentazioneService documentazioneService;
	private FileService fileService;
	private LinkService linkService;
	private ManutenzioneService manutenzioneService;
	private PrenotazioneService prenotazioneService;
	private UserService userService;
	
	@BeforeEach
	void openContext() {
		ctx = new AnnotationConfigApplicationContext(DataServiceConfigTest.class);    
		carService = ctx.getBean("carService", CarService.class);
		documentazioneService = ctx.getBean("documentazioneService", DocumentazioneService.class);
		fileService = ctx.getBean("fileService", FileService.class);
		userService = ctx.getBean("userService", UserService.class);
	}


	@BeforeEach
	void setUp() throws Exception {
	}


	@AfterEach
	void tearDown() throws Exception {
	}

	@AfterEach
	void closeContext() {
		ctx.close();
	}

//	@Test
//	void createAndDelete() throws ParseException {
//		Car c1 = carService.create("AX311TY", "FIAT", "DUCATO", "X1LS22111", 3000, "Emergenza", 2, "DIESEL");
//		Set<Car> cars = new HashSet<Car>(); 
//		cars.add(c1);
//		String dataS1= "07/08/2023";
//        Date data1 = new SimpleDateFormat("dd/MM/yyyy").parse(dataS1); 
//		User user1 = userService.create("mario98", "12345678", "Mario", "Rossi", "admin");
//		Documentazione doc1 = documentazioneService.create("TITOLO", user1, "DESCRIZIONE", data1, (float) 900.10, cars);
//		File a=fileService.create("DESCRIZIONE", doc1, "file1.jpg",true);
//
//		assertEquals(fileService.getAll().size(),1);
//
//		fileService.delete(a);
//
//		assertEquals(fileService.getAll().size(),0);
//		try {
//			File notFound=fileService.getById(1);
//			assertEquals(notFound,null);
//		} catch(Exception e) {
//			assertTrue(true);
//		}
//
//	}
//
//	@Test
//	void createAndFind() throws ParseException {
//
//		Car c1 = carService.create("AX311TY", "FIAT", "DUCATO", "X1LS22111", 3000, "Emergenza", 2, "DIESEL");
//		Set<Car> cars = new HashSet<Car>(); 
//		cars.add(c1);
//		String dataS1= "07/08/2023";
//        Date data1 = new SimpleDateFormat("dd/MM/yyyy").parse(dataS1); 
//		User user1 = userService.create("mario98", "12345678", "Mario", "Rossi", "admin");
//		Documentazione doc1 = documentazioneService.create("TITOLO", user1, "DESCRIZIONE", data1, (float) 900.10,
//	    	    null, cars);
//		File a=fileService.create("DESCRIZIONE", doc1, "file1.jpg",true);
//		
//		try {
//			fileService.getById(a.getId());
//			assertEquals(fileService.getById(a.getId()).isNoDownloadable(),true);
//		} catch(Exception e) {
//			fail("Exception not expected: " + e.getMessage());
//		}
//		List<File> allFiles=fileService.getAll();
//		assertEquals(allFiles.size(), 1);
//	}
//
//	@Test
//	void createAndUpdate() throws ParseException {
//
//		Car c1 = carService.create("AX311TY", "FIAT", "DUCATO", "X1LS22111", 3000, "Emergenza", 2, "DIESEL");
//		Set<Car> cars = new HashSet<Car>(); 
//		cars.add(c1);
//		String dataS1= "07/08/2023";
//        Date data1 = new SimpleDateFormat("dd/MM/yyyy").parse(dataS1); 
//		User user1 = userService.create("mario98", "12345678", "Mario", "Rossi", "admin");
//		Documentazione doc1 = documentazioneService.create("TITOLO", user1, "DESCRIZIONE", data1, (float) 900.10, cars);
//		File a=fileService.create("DESCRIZIONE", doc1, "file1.jpg",true);
//
//
//		assertEquals(fileService.getAll().size(),1);
//		assertEquals(fileService.getByName("post1_file1.jpg").getName(),"post1_file1.jpg");
//
//		a.setName("post1_modifica.jpg");
//		fileService.update(a);
//
//		assertEquals(fileService.getAll().size(),1);
//		assertEquals(fileService.getByName("post1_modifica.jpg").getName(),"post1_modifica.jpg");
//	}




}






