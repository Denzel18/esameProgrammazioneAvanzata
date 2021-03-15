package it.univpm.advancedcode.cri.test.unit.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
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
		String dataS1= "07/08/2023";
		Date data1 = new SimpleDateFormat("dd/MM/yyyy").parse(dataS1);

		String dataS2= "07/08/2023";
		Date data2 = new SimpleDateFormat("dd/MM/yyyy").parse(dataS2);


		Time t1 = Time.valueOf("20:45:20");
		Time t2 = Time.valueOf("19:45:20");

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
		String dataS1= "07/08/2023";
		Date data1 = new SimpleDateFormat("dd/MM/yyyy").parse(dataS1);

		String dataS2= "07/08/2023";
		Date data2 = new SimpleDateFormat("dd/MM/yyyy").parse(dataS2);

		String time = "10:30";
		@SuppressWarnings("deprecation")
		Time t1 = new Time(20,30, 0);
		Time t2 = new Time(20,50, 0);

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
		Date data1 = new SimpleDateFormat("dd/MM/yyyy").parse(dataS1);

		String dataS2= "07/08/2023";
		Date data2 = new SimpleDateFormat("dd/MM/yyyy").parse(dataS2);

		String time = "10:30";
		@SuppressWarnings("deprecation")
		Time t1 = new Time(20,30, 0);
		Time t2 = new Time(20,50, 0);

		Prenotazione p = prenotazioneService.create(1, data1, data2, t1, t2, "DESCRIZIONE", user1);

		assertEquals(prenotazioneService.getAll().size(),1);

		p.setDescrizione("DESCRIZIONE NUOVA");
		prenotazioneService.update(p);
		assertEquals(p.getDescrizione(),"DESCRIZIONE NUOVA");
	}



}




