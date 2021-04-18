package it.univpm.advancedcode.cri.test.unit.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import it.univpm.advancedcode.cri.model.entities.Prenotazione;
import it.univpm.advancedcode.cri.model.entities.User;
import it.univpm.advancedcode.cri.model.entities.Car;
import it.univpm.advancedcode.cri.services.PrenotazioneService;
import it.univpm.advancedcode.cri.services.CarService;
import it.univpm.advancedcode.cri.services.UserService;
import it.univpm.advancedcode.cri.test.DataServiceConfigTest;

public class TestPrenotazioneService {

	private AnnotationConfigApplicationContext ctx;
	private PrenotazioneService prenotazioneService;
	private CarService carService ;
	private UserService userService ; 

	@BeforeEach
	void openContext() {
		ctx = new AnnotationConfigApplicationContext(DataServiceConfigTest.class);    
		prenotazioneService = ctx.getBean("prenotazioneService", PrenotazioneService.class);
		carService = ctx.getBean("carService", CarService.class);
		userService = ctx.getBean("userService", UserService.class);
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
		LocalDate data1 = LocalDate.of(2021,11,20); 
		LocalDate data2 = LocalDate.of(2021,11,20);			
		LocalTime t1 = LocalTime.of(17, 39);
		LocalTime t2 = LocalTime.of(18, 39);
		User user = userService.create("denisberno", "berno", "denis", "bernovschi", "admin"); 
		Car car = carService.create((long)1, "AX311TY", "FIAT", "DUCATO", "X1LS22111", 3000, "Emergenza", 2, "DIESEL");
		Prenotazione p = prenotazioneService.create(1, data1, data2, t1, t2, "DESCRIZIONE", car, user );
		try {
			assertEquals(prenotazioneService.getAll().size(),1);
		} catch(Exception e) {
			fail("Exception not excepted: "+e.getMessage());
		}


		prenotazioneService.delete(p);

		try {
			assertEquals(prenotazioneService.getAll().size(),0);
			assertNull(prenotazioneService.getById(1));
		} catch(Exception e) {
			fail("Exception not excepted: "+e.getMessage());
		}
	}

	
	/** 
	 * @throws java.text.ParseException
	 */
	@Test
	public void createAndFind() throws java.text.ParseException {
		LocalDate data1 = LocalDate.of(2021,11,20); 
		LocalDate data2 = LocalDate.of(2021,11,20);			
		LocalTime t1 = LocalTime.of(17, 39);
		LocalTime t2 = LocalTime.of(18, 39);
		User user = userService.create("denisberno", "berno", "denis", "bernovschi", "admin"); 
		Car car = carService.create((long)1, "AX311TY", "FIAT", "DUCATO", "X1LS22111", 3000, "Emergenza", 2, "DIESEL");
		Prenotazione p = prenotazioneService.create(1, data1, data2, t1, t2, "DESCRIZIONE", car, user );


		try {
			prenotazioneService.getById(p.getId());
		} catch(Exception e) {
			System.out.println("Exception : "+e.getMessage() );
		}
		try {
			Prenotazione notFound=prenotazioneService.getById(999);
			assertNull(notFound);
		} catch(Exception e) {
			assertTrue(true);
		}
		List<Prenotazione> allDocuments=prenotazioneService.getAll();
		assertEquals(allDocuments.size(), 1);
	}

	
	/** 
	 * @throws java.text.ParseException
	 */
	@Test
	public void createAndUpdate() throws java.text.ParseException {
		LocalDate data1 = LocalDate.of(2021,11,20); 
		LocalDate data2 = LocalDate.of(2021,11,20);			
		LocalTime t1 = LocalTime.of(17, 39);
		LocalTime t2 = LocalTime.of(18, 39);
		System.out.println(prenotazioneService.getAll().toString()); 
		User user = userService.create("denisberno", "berno", "denis", "bernovschi", "admin"); 
		Car car = carService.create((long)1, "AX311TY", "FIAT", "DUCATO", "X1LS22111", 3000, "Emergenza", 2, "DIESEL");
		Prenotazione p = prenotazioneService.create(1, data1, data2, t1, t2, "DESCRIZIONE", car, user );		
		//assertEquals(prenotazioneService.getAll().size(),1);

		p.setDescrizione("DESCRIZIONE NUOVA");
		prenotazioneService.update(p);
		assertEquals(p.getDescrizione(),"DESCRIZIONE NUOVA");
	}
}




