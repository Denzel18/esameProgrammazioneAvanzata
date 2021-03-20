package it.univpm.advancedcode.cri.test.unit.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


import java.text.SimpleDateFormat;
import java.time.*;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import it.univpm.advancedcode.cri.model.entities.Prenotazione;
import it.univpm.advancedcode.cri.model.entities.User;
import it.univpm.advancedcode.cri.services.CarService;
import it.univpm.advancedcode.cri.services.PrenotazioneService;
import it.univpm.advancedcode.cri.services.UserService;
import it.univpm.advancedcode.cri.test.DataServiceConfigTest;

public class TestPrenotazioneService {

	private AnnotationConfigApplicationContext ctx;
	private CarService carService;
	private PrenotazioneService prenotazioneService;
	private UserService userService;

	@BeforeEach
	void openContext() {
		ctx = new AnnotationConfigApplicationContext(DataServiceConfigTest.class);    
		carService = ctx.getBean("carService", CarService.class);
		prenotazioneService = ctx.getBean("prenotazioneService", PrenotazioneService.class);
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
		LocalDate data1 = LocalDate.of(2021,11,20); 
		LocalDate data2 = LocalDate.of(2021,11,20);			
		LocalTime t1 = LocalTime.of(17, 39);
		LocalTime t2 = LocalTime.of(18, 39);

		Prenotazione p = prenotazioneService.create(1, data1, data2, t1, t2, "DESCRIZIONE", user1);
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

	@Test
	public void createAndFind() throws java.text.ParseException {

		User user1 = userService.create("mario98", "12345678", "Mario", "Rossi", "admin");			
		LocalDate data1 = LocalDate.of(2021,11,20); 
		LocalDate data2 = LocalDate.of(2021,11,20);			
		LocalTime t1 = LocalTime.of(17, 39);
		LocalTime t2 = LocalTime.of(18, 39);

		Prenotazione p = prenotazioneService.create(1, data1, data2, t1, t2, "DESCRIZIONE", user1);


		try {
			prenotazioneService.getById(p.getId());
		} catch(Exception e) {
			fail("Exception not expected: " + e.getMessage());
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

	@Test
	public void createAndUpdate() throws java.text.ParseException {
		User user1 = userService.create("mario98", "12345678", "Mario", "Rossi", "admin");			
		String dataS1= "07/08/2023";
		LocalDate data1 = LocalDate.of(2021,11,20); 
		LocalDate data2 = LocalDate.of(2021,11,20);			
		LocalTime t1 = LocalTime.of(17, 39);
		LocalTime t2 = LocalTime.of(18, 39);

		Prenotazione p = prenotazioneService.create(1, data1, data2, t1, t2, "DESCRIZIONE", user1);

		assertEquals(prenotazioneService.getAll().size(),1);

		p.setDescrizione("DESCRIZIONE NUOVA");
		prenotazioneService.update(p);
		assertEquals(p.getDescrizione(),"DESCRIZIONE NUOVA");
	}



}




