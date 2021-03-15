package it.univpm.advancedcode.cri.test.unit.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import it.univpm.advancedcode.cri.model.entities.Car;
import it.univpm.advancedcode.cri.services.CarService;
import it.univpm.advancedcode.cri.test.DataServiceConfigTest;

public class TestCarService {
	private AnnotationConfigApplicationContext ctx;
	private CarService carService;

	@BeforeEach
	void openContext() {
		ctx = new AnnotationConfigApplicationContext(DataServiceConfigTest.class);    
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
	public void createAndDeleteByTarga() {
		Car c1 = carService.create("AX311TY", "FIAT", "DUCATO", "X1LS22111", 3000, "Emergenza", 2, "DIESEL");
		
		System.out.println("Nel parco mezzi ci sono n. " +carService.getAll().size() + " veicoli \n" );

		assertEquals(carService.getAll().size(), 1);

		carService.delete(c1);

		assertEquals(carService.getAll().size(), 0);
		assertNull(carService.getByTarga("AX311TY"));
	}

	@Test
	public void createAndUpdate() throws java.text.ParseException {
		Car c1 = carService.create("AX311TY", "FIAT", "DUCATO", "X1LS22111", 3000, "Emergenza", 2, "DIESEL");

		assertEquals(carService.getAll().size(), 1);

		c1.setTarga("CC000TY");
		carService.update(c1);
		assertEquals(carService.getByTarga("CC000TY"),  c1); 
	}

	@Test
	public void noCarsAtBeginning() {
		assertEquals(carService.getAll().size(), 0);
	}



	@Test public void testGetByTarga() { 
		Car c1 = carService.create("AX311TY","FIAT", "DUCATO", "X1LS22111", 3000, "Emergenza", 2, "DIESEL");
		try { 
			assertEquals(carService.getAll().size(), 1);
			assertEquals(carService.getByTarga("AX311TY"), c1); 
		} catch (Exception e) {
			fail("Exception not excepted: " + e.getMessage()); 
		}
	}

}
