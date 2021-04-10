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
import it.univpm.advancedcode.cri.model.entities.User;
import it.univpm.advancedcode.cri.model.entities.Car;
import it.univpm.advancedcode.cri.model.entities.Link;
import it.univpm.advancedcode.cri.services.DocumentazioneService;
import it.univpm.advancedcode.cri.services.LinkService;
import it.univpm.advancedcode.cri.services.UserService;
import it.univpm.advancedcode.cri.services.CarService;
import it.univpm.advancedcode.cri.test.DataServiceConfigTest;

public class TestLinkService {
	private AnnotationConfigApplicationContext ctx;	
	private DocumentazioneService documentazioneService;
	private LinkService linkService;
	private UserService userService;
	private CarService carService;	

	@AfterEach
	void closeContext() {
		ctx.close();
	}

	@Test
	void createAndDelete() throws ParseException {
		LocalDate data1 = LocalDate.of(2021, 10, 10); 		
		User user = userService.create("marioR", "marioR", "mario", "rossi", "admin");
		Car car = carService.create((long)1, "AX311TY", "FIAT", "DUCATO", "X1LS22111", 3000, "Emergenza", 2, "DIESEL");
		Documentazione doc1 = documentazioneService.create((long) 1, "TITOLO", user, "DESCRIZIONE", data1, (float) 900.10, car);
		Link link1=linkService.create("DESCRIZIONE", doc1, "https://www.univpm.it");

		assertEquals(linkService.getAll().size(),1);

		linkService.delete(link1);

		assertEquals(linkService.getAll().size(),0);
	}

	@Test
	void createAndFind() throws ParseException {
		LocalDate data1 = LocalDate.of(2021, 10, 10); 		
		User user = userService.create("marioR", "marioR", "mario", "rossi", "admin");
		Car car = carService.create((long)1, "AX311TY", "FIAT", "DUCATO", "X1LS22111", 3000, "Emergenza", 2, "DIESEL");
		Documentazione doc1 = documentazioneService.create((long) 1, "TITOLO", user, "DESCRIZIONE", data1, (float) 900.10, car);
		Link link1=linkService.create("DESCRIZIONE", doc1, "https://www.univpm.it");
		try {
			linkService.getById(link1.getId());
		} catch(Exception e) {
			fail("Exception not expected: " + e.getMessage());
		}
		try {
			Link notFound=linkService.getById(999);
			assertEquals(notFound,null);
		} catch(Exception e) {
			assertTrue(true);
		}
		List<Link> allFiles=linkService.getAll();
		assertEquals(allFiles.size(), 1);
	}

	@Test
	void createAndUpdate() throws ParseException {
		LocalDate data1 = LocalDate.of(2021, 10, 10); 		
		User user = userService.create("marioR", "marioR", "mario", "rossi", "admin");
		Car car = carService.create((long)1, "AX311TY", "FIAT", "DUCATO", "X1LS22111", 3000, "Emergenza", 2, "DIESEL");
		Documentazione doc1 = documentazioneService.create((long) 1, "TITOLO", user, "DESCRIZIONE", data1, (float) 900.10, car);
		Link link1=linkService.create("DESCRIZIONE", doc1, "https://www.univpm.it");
		
		System.out.println(" LINK HASHCODE " + linkService.hashCode()); 

		assertEquals(linkService.getAll().size(),1);

		link1.setLink("https://www.google.com");
		linkService.update(link1);

		assertEquals(linkService.getAll().size(),1);
		assertEquals(linkService.getById(1).getLink(),"https://www.google.com");
	}



	@BeforeEach
	void openContext() {
		ctx = new AnnotationConfigApplicationContext(DataServiceConfigTest.class);    
		documentazioneService = ctx.getBean("documentazioneService", DocumentazioneService.class);
		linkService = ctx.getBean("linkService", LinkService.class);
		userService = ctx.getBean("userService", UserService.class);
		carService = ctx.getBean("carService", CarService.class);
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}	
}
