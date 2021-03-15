package it.univpm.advancedcode.cri.test.unit.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

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
import it.univpm.advancedcode.cri.model.entities.User;
import it.univpm.advancedcode.cri.services.CarService;
import it.univpm.advancedcode.cri.services.DocumentazioneService;
import it.univpm.advancedcode.cri.services.UserService;
import it.univpm.advancedcode.cri.test.DataServiceConfigTest;

public class TestDocumentazioneService {

	private AnnotationConfigApplicationContext ctx;
	private CarService carService;
	private DocumentazioneService documentazioneService;
	private UserService userService;
	

	@BeforeEach
	void openContext() {
		ctx = new AnnotationConfigApplicationContext(DataServiceConfigTest.class);    
		carService = ctx.getBean("carService", CarService.class);
		documentazioneService = ctx.getBean("documentazioneService", DocumentazioneService.class);
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


	@Test
	public void createAndDelete() throws java.text.ParseException {
		User user1 = userService.create("mario98", "12345678", "Mario", "Rossi", "admin");
		String dataS1= "07/08/2023";
		Date data1 = new SimpleDateFormat("dd/MM/yyyy").parse(dataS1); 
		Car c1 = carService.create("AX311TY", "FIAT", "DUCATO", "X1LS22111", 3000, "Emergenza", 2, "DIESEL");
		Set<Car> cars = new HashSet<Car>(); 
		cars.add(c1);
		
		Documentazione doc1 = documentazioneService.create("TITOLO", user1, "DESCRIZIONE", data1, (float) 900.10, cars);
		try {
			assertEquals(documentazioneService.getAll().size(),1);
		} catch(Exception e) {
			fail("Exception not excepted: "+e.getMessage());
		}


		documentazioneService.delete(doc1);

		try {
			assertEquals(documentazioneService.getAll().size(),0);
			assertNull(documentazioneService.getById(1));
		} catch(Exception e) {
			fail("Exception not excepted: "+e.getMessage());
		}
	}

	@Test
	public void createAndFind() throws java.text.ParseException {
		User user1 = userService.create("mario98", "12345678", "Mario", "Rossi", "admin");	
		String dataS1= "07/08/2023";
		Date data1 = new SimpleDateFormat("dd/MM/yyyy").parse(dataS1); 
		Car c1 = carService.create("AX311TY", "FIAT", "DUCATO", "X1LS22111", 3000, "Emergenza", 2, "DIESEL");
		Set<Car> cars = new HashSet<Car>(); 
		cars.add(c1);
		Documentazione doc1 = documentazioneService.create("TITOLO", user1, "DESCRIZIONE", data1, (float) 900.10,cars);

		try {
			documentazioneService.getById(doc1.getDocumento_id()); 
		} catch(Exception e) {
			fail("Exception not expected: " + e.getMessage());
		}
		try {
			Documentazione notFound=documentazioneService.getById(999);
			assertNull(notFound);
		} catch(Exception e) {
			assertTrue(true);
		}
		List<Documentazione> allDocuments=documentazioneService.getAll();
		assertEquals(allDocuments.size(), 1);
	}

	@Test
	public void createAndUpdate() throws java.text.ParseException {
		User user1 = userService.create("mario98", "12345678", "Mario", "Rossi", "admin");
		User user2 = userService.create("mario18", "12345678", "Mario2", "Rossi", "driver");
		String dataS1= "07/08/2023";
		Date data1 = new SimpleDateFormat("dd/MM/yyyy").parse(dataS1); 
		Car c1 = carService.create("AX311TY", "FIAT", "DUCATO", "X1LS22111", 3000, "Emergenza", 2, "DIESEL");
		Set<Car> cars = new HashSet<Car>(); 
		cars.add(c1);
		Documentazione doc1 = documentazioneService.create("TITOLO", user1, "DESCRIZIONE", data1, (float) 900.10,cars);
		assertEquals(documentazioneService.getAll().size(),1);

		doc1.setAutoreUtente(user2);
		documentazioneService.update(doc1);
		assertEquals(doc1.getAutoreUtente(),user2);
	}


}




