package it.univpm.advancedcode.cri.test.unit.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.util.List;

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
	private DocumentazioneService documentazioneService;
	private UserService userService;
	private CarService carService;	

	@BeforeEach
	void openContext() {
		ctx = new AnnotationConfigApplicationContext(DataServiceConfigTest.class);    
		documentazioneService = ctx.getBean("documentazioneService", DocumentazioneService.class);
		userService = ctx.getBean("userService", UserService.class);
		carService = ctx.getBean("carService", CarService.class);
	}

	
	/** 
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}
	
	/** 
	 * @throws Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	@AfterEach
	void closeContext() {
		ctx.close();
	}


	
	/** 
	 * @throws java.text.ParseException
	 */
	@Test
	public void createAndDelete() throws java.text.ParseException {
		LocalDate data1 = LocalDate.of(2021, 10, 10); 		
		User user = userService.create("marioR", "marioR", "mario", "rossi", "admin");
		Car car = carService.create((long)1, "AX311TY", "FIAT", "DUCATO", "X1LS22111", 3000, "Emergenza", 2, "DIESEL");
		Documentazione doc1 = documentazioneService.create((long) 1, "TITOLO", user, "DESCRIZIONE", data1, (float) 900.10, car);
		
		System.out.println("Lunghezza : "+documentazioneService.getAll().size());

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

	
	/** 
	 * @throws java.text.ParseException
	 */
	@Test
	public void createAndFind() throws java.text.ParseException {
		LocalDate data1 = LocalDate.of(2021, 10, 10); 		
		User user = userService.create("marioR", "marioR", "mario", "rossi", "admin");
		Car car = carService.create((long)1, "AX311TY", "FIAT", "DUCATO", "X1LS22111", 3000, "Emergenza", 2, "DIESEL");
		Documentazione doc1 = documentazioneService.create((long) 1, "TITOLO", user, "DESCRIZIONE", data1, (float) 900.10, car);

		try {
			documentazioneService.getById(doc1.getId()); 
		} catch(Exception e) {
			fail("Exception not excepted: "+e.getMessage());
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

	
	/** 
	 * @throws java.text.ParseException
	 */
	@Test
	public void createAndUpdate() throws java.text.ParseException {
		LocalDate data1 = LocalDate.of(2021, 10, 10); 		
		User user = userService.create("marioR", "marioR", "mario", "rossi", "admin");
		Car car = carService.create((long)1, "AX311TY", "FIAT", "DUCATO", "X1LS22111", 3000, "Emergenza", 2, "DIESEL");
		Documentazione doc1 = documentazioneService.create((long) 1, "TITOLO", user, "DESCRIZIONE", data1, (float) 900.10, car);
		assertEquals(documentazioneService.getAll().size(),1);
		doc1.setCosto((float) 900.30);
		documentazioneService.update(doc1);
		assertEquals(doc1.getCosto(),(float) 900.30);
	}
}




