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
import it.univpm.advancedcode.cri.model.entities.Link;
import it.univpm.advancedcode.cri.model.entities.User;
import it.univpm.advancedcode.cri.services.CarService;
import it.univpm.advancedcode.cri.services.DocumentazioneService;
import it.univpm.advancedcode.cri.services.LinkService;
import it.univpm.advancedcode.cri.services.UserService;
import it.univpm.advancedcode.cri.test.DataServiceConfigTest;

public class TestLinkService {
	private AnnotationConfigApplicationContext ctx;	
	private UserService userService;
	private DocumentazioneService documentazioneService;
	private LinkService linkService;
	private CarService carService;

	@AfterEach
	void closeContext() {
		ctx.close();
	}

//	@Test
//	void createAndDelete() throws ParseException {
//		User user1 = userService.create("mario98", "12345678", "Mario", "Rossi", "admin");	
//		String dataS1= "07/08/2023";
//		Date data1 = new SimpleDateFormat("dd/MM/yyyy").parse(dataS1); 
//		Car c1 = carService.create("AX311TY", "FIAT", "DUCATO", "X1LS22111", 3000, "Emergenza", 2, "DIESEL");
//		Set<Car> cars = new HashSet<Car>(); 
//		cars.add(c1);
//		Documentazione doc1 = documentazioneService.create("TITOLO", user1, "DESCRIZIONE", data1, (float) 900.10, cars);
//		Link link1=linkService.create("DESCRIZIONE", doc1, "https://www.univpm.it");
//
//		assertEquals(linkService.getAll().size(),1);
//
//		linkService.delete(link1);
//
//		assertEquals(linkService.getAll().size(),0);
//	}
//
//	@Test
//	void createAndFind() throws ParseException {
//		User user1 = userService.create("mario98", "12345678", "Mario", "Rossi", "admin");	
//		String dataS1= "07/08/2023";
//		Date data1 = new SimpleDateFormat("dd/MM/yyyy").parse(dataS1); 
//		Car c1 = carService.create("AX311TY", "FIAT", "DUCATO", "X1LS22111", 3000, "Emergenza", 2, "DIESEL");
//		Set<Car> cars = new HashSet<Car>(); 
//		cars.add(c1);
//		Documentazione doc1 = documentazioneService.create("TITOLO", user1, "DESCRIZIONE", data1, (float) 900.10,
//				null, cars);
//		Link link1=linkService.create("DESCRIZIONE", doc1, "https://www.univpm.it");
//
//		try {
//			linkService.getById(link1.getId());
//		} catch(Exception e) {
//			fail("Exception not expected: " + e.getMessage());
//		}
//		try {
//			Link notFound=linkService.getById(999);
//			assertEquals(notFound,null);
//		} catch(Exception e) {
//			assertTrue(true);
//		}
//		List<Link> allFiles=linkService.getAll();
//		assertEquals(allFiles.size(), 1);
//	}
//
//	@Test
//	void createAndUpdate() throws ParseException {
//		User user1 = userService.create("mario98", "12345678", "Mario", "Rossi", "admin");	
//		String dataS1= "07/08/2023";
//		Date data1 = new SimpleDateFormat("dd/MM/yyyy").parse(dataS1); 
//		Car c1 = carService.create("AX311TY", "FIAT", "DUCATO", "X1LS22111", 3000, "Emergenza", 2, "DIESEL");
//		Set<Car> cars = new HashSet<Car>(); 
//		cars.add(c1);
//		Documentazione doc1 = documentazioneService.create("TITOLO", user1, "DESCRIZIONE", data1, (float) 900.10, cars);
//		Link link1=linkService.create("DESCRIZIONE", doc1, "https://www.univpm.it");
//
//		assertEquals(linkService.getAll().size(),1);
//
//		link1.setLink("https://www.google.com");
//		linkService.update(link1);
//
//		assertEquals(linkService.getAll().size(),1);
//		assertEquals(linkService.getById(1).getLink(),"https://www.google.com");
//	}



	@BeforeEach
	void openContext() {
		ctx = new AnnotationConfigApplicationContext(DataServiceConfigTest.class);    
		carService = ctx.getBean("carService", CarService.class);
		documentazioneService = ctx.getBean("documentazioneService", DocumentazioneService.class);
		linkService = ctx.getBean("linkService", LinkService.class);
		userService = ctx.getBean("userService", UserService.class);
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}	
}
