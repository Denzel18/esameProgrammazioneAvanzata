package it.univpm.advancedcode.cri.test.unit.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import it.univpm.advancedcode.cri.model.entities.Car;
import it.univpm.advancedcode.cri.model.entities.Documentazione;
import it.univpm.advancedcode.cri.model.entities.Manutenzione;
import it.univpm.advancedcode.cri.services.CarService;
import it.univpm.advancedcode.cri.services.ManutenzioneService;
import it.univpm.advancedcode.cri.test.DataServiceConfigTest;

public class TestManutenzioneService {
	private AnnotationConfigApplicationContext ctx;
	private CarService carService;
	private ManutenzioneService manutenzioneService;

	@AfterEach
	void closeContext() {

		ctx.close();
	}


	@Test
	public void createAndDelete() {
		Car car = carService.create((long)1, "AX311TY", "FIAT", "DUCATO", "X1LS22111", 3000, "Emergenza", 2, "DIESEL");
		Manutenzione m1 = manutenzioneService.create(1, "STRAORDINARIA", (float)90.05 , car);
		try {
			assertEquals(manutenzioneService.getAll().size(),1);
		} catch(Exception e) {
			fail("Exception not excepted: "+e.getMessage());
		}


		manutenzioneService.delete(m1);

		try {
			assertEquals(manutenzioneService.getAll().size(),0);
			assertNull(manutenzioneService.getById(1));
		} catch(Exception e) {
			fail("Exception not excepted: "+e.getMessage());
		}
	}

	@Test
	void createAndFind() {
		Car car = carService.create((long)1, "AX311TY", "FIAT", "DUCATO", "X1LS22111", 3000, "Emergenza", 2, "DIESEL");
		Manutenzione m1 = manutenzioneService.create(1, "STRAORDINARIA", (float)90.05 , car);
		try {
			manutenzioneService.getById(m1.getId());
		} catch(Exception e) {
			System.out.println("Exception : "+e.getMessage() );
		}
		try {
			Manutenzione notFound=manutenzioneService.getById(999);
			assertNull(notFound);
		} catch(Exception e) {
			assertTrue(true);
		}
		List<Manutenzione> allcomments=manutenzioneService.getAll();
		assertEquals(allcomments.size(), 1);
	}

	@Test
	void findManutenzioneById() {
		Car car = carService.create((long)1, "AX311TY", "FIAT", "DUCATO", "X1LS22111", 3000, "Emergenza", 2, "DIESEL");
		Manutenzione m1 = manutenzioneService.create(1, "STRAORDINARIA", (float)90.05 , car);
		try {
			assertEquals(manutenzioneService.getAll().size(),1);
			assertEquals(manutenzioneService.getById(1).getId(),m1.getId());
		} catch(Exception e) {
			fail("Exception not excepted: "+e.getMessage());
		}
	}

	@Test
	void noManutenzioniAtBeginning() {
		assertEquals(manutenzioneService.getAll().size(), 0);
	}

	@BeforeEach
	void openContext() {
		ctx = new AnnotationConfigApplicationContext(DataServiceConfigTest.class);    
		carService = ctx.getBean("carService", CarService.class);
		manutenzioneService = ctx.getBean("manutenzioneService", ManutenzioneService.class);
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


	@Test
	void createAndUpdate() {
		Car car = carService.create((long)1, "AX311TY", "FIAT", "DUCATO", "X1LS22111", 3000, "Emergenza", 2, "DIESEL");
		Manutenzione m1 = manutenzioneService.create(1, "STRAORDINARIA", (float)90.05 , car);
		try {
			assertEquals(manutenzioneService.getAll().size(),1);
		} catch(Exception e) {
			fail("Exception not excepted: "+e.getMessage());
		}


		m1.setTipoManutenzione("Manutenzione Modificato");
		manutenzioneService.update(m1);

		try {
			assertEquals(manutenzioneService.getAll().size(),1);
			assertEquals(manutenzioneService.getById(1).getTipoManutenzione(), "Manutenzione Modificato");
		} catch(Exception e) {
			fail("Exception not excepted: "+e.getMessage());
		}
	}
}
