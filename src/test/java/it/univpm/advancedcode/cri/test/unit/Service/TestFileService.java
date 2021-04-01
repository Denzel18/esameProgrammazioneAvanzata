package it.univpm.advancedcode.cri.test.unit.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import it.univpm.advancedcode.cri.model.entities.Documentazione;
import it.univpm.advancedcode.cri.model.entities.File;
import it.univpm.advancedcode.cri.model.entities.Car;
import it.univpm.advancedcode.cri.model.entities.User;
import it.univpm.advancedcode.cri.services.CarService;
import it.univpm.advancedcode.cri.services.DocumentazioneService;
import it.univpm.advancedcode.cri.services.FileService;
import it.univpm.advancedcode.cri.services.UserService;
import it.univpm.advancedcode.cri.test.DataServiceConfigTest;

public class TestFileService {

	private AnnotationConfigApplicationContext ctx;
	private DocumentazioneService documentazioneService;
	private FileService fileService;
	private UserService userService;
	private CarService carService;	
	
	@BeforeEach
	void openContext() {
		ctx = new AnnotationConfigApplicationContext(DataServiceConfigTest.class);    
		documentazioneService = ctx.getBean("documentazioneService", DocumentazioneService.class);
		fileService = ctx.getBean("fileService", FileService.class);
		userService = ctx.getBean("userService", UserService.class);
		carService = ctx.getBean("carService", CarService.class);
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

	@Test
	void createAndDelete() throws ParseException {
		LocalDate data1 = LocalDate.of(2021, 10, 10); 		
		User user = userService.create("marioR", "marioR", "mario", "rossi", "admin");
		Car car = carService.create((long)1, "AX311TY", "FIAT", "DUCATO", "X1LS22111", 3000, "Emergenza", 2, "DIESEL");
		Documentazione doc1 = documentazioneService.create("TITOLO", user, "DESCRIZIONE", data1, (float) 900.10, car);
		File a=fileService.create("DESCRIZIONE", doc1, "file1.jpg",true);

		assertEquals(fileService.getAll().size(),1);

		fileService.delete(a);

		assertEquals(fileService.getAll().size(),0);
		try {
			File notFound=fileService.getById(1);
			assertEquals(notFound,null);
		} catch(Exception e) {
			assertTrue(true);
		}

	}

	@Test
	void createAndFind() throws ParseException {
		LocalDate data1 = LocalDate.of(2021, 10, 10); 		
		User user = userService.create("marioR", "marioR", "mario", "rossi", "admin");
		Car car = carService.create((long)1, "AX311TY", "FIAT", "DUCATO", "X1LS22111", 3000, "Emergenza", 2, "DIESEL");
		Documentazione doc1 = documentazioneService.create("TITOLO", user, "DESCRIZIONE", data1, (float) 900.10, car);
		File a=fileService.create("DESCRIZIONE", doc1, "file1.jpg",true);
				
		try {
			fileService.getById(a.getId());
			assertEquals(fileService.getById(a.getId()).isNoDownloadable(),true);
		} catch(Exception e) {
			fail("Exception not expected: " + e.getMessage());
		}
		List<File> allFiles=fileService.getAll();
		assertEquals(allFiles.size(), 1);
	}

	@Test
	void createAndUpdate() throws ParseException {
		LocalDate data1 = LocalDate.of(2021, 10, 10); 		
		User user = userService.create("marioR", "marioR", "mario", "rossi", "admin");
		Car car = carService.create((long)1, "AX311TY", "FIAT", "DUCATO", "X1LS22111", 3000, "Emergenza", 2, "DIESEL");
		Documentazione doc1 = documentazioneService.create("TITOLO", user, "DESCRIZIONE", data1, (float) 900.10, car);
		File a=fileService.create("DESCRIZIONE", doc1, "file1.jpg",true);


		assertEquals(fileService.getAll().size(),1);
		assertEquals(fileService.getByName("documento1_file1.jpg").getName(),"documento1_file1.jpg");

		a.setName("post1_modifica.jpg");
		fileService.update(a);

		assertEquals(fileService.getAll().size(),1);
		assertEquals(fileService.getByName("post1_modifica.jpg").getName(),"post1_modifica.jpg");
	}




}






