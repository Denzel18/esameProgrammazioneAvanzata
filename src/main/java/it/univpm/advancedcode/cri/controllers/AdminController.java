package it.univpm.advancedcode.cri.controllers;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.univpm.advancedcode.cri.model.entities.Allegato;
import it.univpm.advancedcode.cri.model.entities.Car;
import it.univpm.advancedcode.cri.model.entities.Documentazione;
import it.univpm.advancedcode.cri.model.entities.Manutenzione;
import it.univpm.advancedcode.cri.model.entities.Prenotazione;
import it.univpm.advancedcode.cri.model.entities.User;
import it.univpm.advancedcode.cri.services.AllegatoService;
import it.univpm.advancedcode.cri.services.CarService;
import it.univpm.advancedcode.cri.services.DocumentazioneService;
import it.univpm.advancedcode.cri.services.ManutenzioneService;
import it.univpm.advancedcode.cri.services.PrenotazioneService;
import it.univpm.advancedcode.cri.services.UserService;


@Controller
public class AdminController {
    private final Logger logger = LoggerFactory.getLogger(AdminController.class);

    private AllegatoService allegatoService;
    private CarService carService;
    private DocumentazioneService documentazioneService;
    private ManutenzioneService manutenzioneService;
    private PrenotazioneService prenotazioneService;
    private UserService userService;



	/**
	 * @param allegatoService the allegatoService to set
	 */
    @Autowired
	public void setAllegatoService(AllegatoService allegatoService) {
		this.allegatoService = allegatoService;
	}

	/**
	 * @param carService the carService to set
	 */
    @Autowired
	public void setCarService(CarService carService) {
		this.carService = carService;
	}

	/**
	 * @param documentazioneService the documentazioneService to set
	 */
    @Autowired
	public void setDocumentazioneService(DocumentazioneService documentazioneService) {
		this.documentazioneService = documentazioneService;
	}

	/**
	 * @param manutenzioneService the manutenzioneService to set
	 */
    @Autowired
	public void setManutenzioneService(ManutenzioneService manutenzioneService) {
		this.manutenzioneService = manutenzioneService;
	}

	/**
	 * @param prenotazioneService the prenotazioneService to set
	 */
    @Autowired
	public void setPrenotazioneService(PrenotazioneService prenotazioneService) {
		this.prenotazioneService = prenotazioneService;
	}

	/**
	 * @param userService the userService to set
	 */
    @Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

    /**
     * Metodo " GET " per visualizzare i veicoli 
     * @param uiModel modello associato
     * @return nome della vista
     */
    @GetMapping(value = "/cars")
    public String showCars(@RequestParam(value = "successMessage", required = false) String successMessage,
    		@RequestParam(value = "errorMessage", required = false) String errorMessage,
    		Model uiModel) {
    	logger.info("Listing all the cars...");

    	List<Car> cars = this.carService.getAll();

    	uiModel.addAttribute("cars", cars);
    	uiModel.addAttribute("numCars", cars.size());
    	uiModel.addAttribute("successMessage", successMessage);
    	uiModel.addAttribute("errorMessage", errorMessage);

    	return "cars.list";
    }


    /**
     * Metodo " GET " per la creazione di un veicolo 
     * @param uiModel modello associato
     * @return nome della vista
     */
    @GetMapping(value = "/cars/new")
    public String newCar(Model uiModel) {
    	logger.info("Creating a new car...");
    	uiModel.addAttribute("car", new Car());
    	return "car.new";
    }

    /**
     * Metodo " POST " per il salvataggio del veicolo
     *
     * @param car           car restituita dalla richiesta
     * @param bindingResult eventuali errori di validazione
     * @param uiModel       modello associato alla vista
     * @return nome della vista da visualizzare
     */
    @PostMapping(value = "/car/new/save")
    public String saveCar(@ModelAttribute("car") Car car, BindingResult bindingResult, Model uiModel) {
    	logger.info("Saving a new car...");
    	if((car.getTarga() == null || car.getTarga().equals("")) || 
    			(car.getMarca() == null || car.getMarca().equals("")) ||
    			(car.getModello() == null || car.getModello().equals("")) ||
    			(car.getNumeroTelaio() == null || car.getNumeroTelaio().equals("")) ||
    			(car.getMassa() == 0) ||
    			(car.getDestinazioneUso() == null || car.getDestinazioneUso().equals("")) ||
    			(car.getNumeroAssi()== 0) ||
    			(car.getAlimentazione() == null || car.getAlimentazione().equals(""))) {
    		String strMessage = "Non hai inserito i campi obbligatori!";
    		return "redirect:/cars/?errorMessage=" + strMessage;
    	}

    	try {
    		this.carService.create(1, car.getTarga(), car.getMarca(), car.getModello(), 
    				car.getNumeroTelaio(), car.getMassa(), car.getDestinazioneUso(),
    				car.getNumeroAssi(), car.getAlimentazione());

    		String strMessage = "Il veicolo targa \"" + car.getTarga() + "\" %C3%A8 stato salvato correttamente!";

    		return "redirect:/cars/?successMessage=" + strMessage;

    	} catch (RuntimeException e) {

    		return "redirect:/cars/?errorMessage=" + e.getMessage();
    	}
    }

    /**
     * Metodo " GET " per eliminare un veicolo
     *
     * @param targa targa del veicolo da cancellare
     * @return nome della vista da visualizzare
     */
    @GetMapping(value = "/car/delete/{targa}")
    public String deleteCar(@PathVariable("targa") String targa) {
    	logger.info("Deleting car with license plate \"" + targa + "\"...");

    	Car selectedCar = this.carService.getByTarga(targa);
    	String strMessage;

    	if (selectedCar.getPrenotazioni().size() == 0) {
    		this.carService.delete(selectedCar);
    		strMessage = "Il veicolo targato \"" + selectedCar.getTarga() + "\" %C3%A8 stato cancellato correttamente!";
    		return "redirect:/cars/?successMessage=" + strMessage;
    	} else {
    		strMessage = "Il veicolo targato \"" + selectedCar.getTarga() + "\" risulta essere prenotato... " +
    				"Non pu%C3%B2 essere cancellato!";
    		return "redirect:/cars/?errorMessage=" + strMessage;
    	}
    }


    /**
     * Metodo " GET " per la visualizzazione della lista di allegati.
     *
     * @param errorMessage eventuale messaggio di errore
     * @param successMessage eventuale messaggio di successo
     * @param uiModel modello associato alla vista
     * @return nome della vista
     */
    @GetMapping(value = "/allegati")
    public String showAllegati(@RequestParam(value = "successMessage", required = false) String successMessage,
    		@RequestParam(value = "errorMessage", required = false) String errorMessage,
    		Model uiModel) {
    	logger.info("Listing all the attachments...");
    	List<Allegato> allAllegati = this.allegatoService.getAll();
    	uiModel.addAttribute("allegati", allAllegati);
    	uiModel.addAttribute("numAllegati", allAllegati.size());
    	uiModel.addAttribute("successMessage", successMessage);
    	uiModel.addAttribute("errorMessage", errorMessage);
    	return "allegati.list";
    }


    /**
     * Metodo " GET " per mostrare un allegato.
     *
     * @param allegato_id ID dell'allegato da nascondere
     * @return nome della vista
     */
    @GetMapping(value = "/allegato/show/{allegato_id}")
    public String showAllegato(@PathVariable("allegato_id") String allegato_id) {
    	Allegato selectedAllegato = allegatoService.getById(Long.parseLong(allegato_id));
    	logger.info("Showing the attachment \"" + selectedAllegato.getDescrizione() + "\"...");     
    	return "redirect:/attachments/?successMessage=" + "MESSAGGIO DI SUCCESSO";
    }

    /**
     * Metodo " GET " per la visualizzazione della lista di tutti gli utenti.
     *
     * @param errorMessage eventuale messaggio di errore
     * @param successMessage eventuale messaggio di successo
     * @param uiModel modello associato alla vista
     * @return nome della vista da visualizzare
     */
    @GetMapping(value = "/users")
    public String showUsers(@RequestParam(value = "successMessage", required = false) String successMessage,
    		@RequestParam(value = "errorMessage", required = false) String errorMessage,
    		Model uiModel) {
    	logger.info("Listing all the users...");

    	List<User> allUsers = this.userService.findAll();

    	uiModel.addAttribute("users", allUsers);
    	uiModel.addAttribute("numUsers", allUsers.size());
    	uiModel.addAttribute("successMessage", successMessage);
    	uiModel.addAttribute("errorMessage", errorMessage);

    	return "users.list";
    }


    /**
     * Metodo " GET " per la creazione di un utente 
     * @param uiModel modello associato
     * @return nome della vista
     */
    @GetMapping(value = "/users/new")
    public String newUsers(Model uiModel) {
    	logger.info("Creating a new user...");
    	uiModel.addAttribute("user", new User());
    	return "user.new";
    }

    /**
     * Metodo " POST " per il salvataggio del utente
     *
     * @param utente        utente restituita dalla richiesta
     * @param bindingResult eventuali errori di validazione
     * @param uiModel       modello associato alla vista
     * @return nome della vista da visualizzare
     */
    @PostMapping(value = "/users/new/save")
    public String saveUser(@ModelAttribute("user") User user, BindingResult bindingResult, Model uiModel) {
    	logger.info("Saving a new user...");
    	if((user.getRuolo() == null || user.getRuolo().equals("")) ||
    			(user.getUsername() == null || user.getUsername().equals("")) ||
    			(user.getPassword() == null || user.getPassword().equals("")) ||	
    			(user.getFirstname() == null || user.getFirstname().equals("")) ||
    			(user.getLastname() == null || user.getLastname().equals(""))){
    		String strMessage = "Non hai inserito i campi obbligatori!";
    		return "redirect:/cars/?errorMessage=" + strMessage;
    	}
    	try {
    		this.userService.create(user.getUsername(), user.getPassword(), user.getFirstname(), user.getLastname(),user.getRuolo());
    		String strMessage = "l'utente con username \"" + user.getUsername() + "\" %C3%A8 stato salvato correttamente!";
    		return "redirect:/users/?successMessage=" + strMessage;
    	} catch (RuntimeException e) {
    		return "redirect:/users/?errorMessage=" + e.getMessage();
    	}
    }

    /**
     * Metodo " GET " per eliminare un veicolo
     *
     * @param targa targa del veicolo da cancellare
     * @return nome della vista da visualizzare
     */
    @GetMapping(value = "/users/delete/{username}")
    public String deleteUser(@PathVariable("username") String username) {
    	logger.info("Deleting user with username  \"" + username + "\"...");

    	User selectedUser = this.userService.findUserByUsername(username);
    	String strMessage;

    	this.userService.delete(selectedUser);
    	strMessage = "L'utente : \"" + selectedUser.getUsername() + "\" %C3%A8 stato cancellato correttamente!";
    	return "redirect:/users/?successMessage=" + strMessage;
    }


    /**
     * Metodo " GET " per la visualizzazione della lista di tutte le prenotazioni
     *
     * @param errorMessage eventuale messaggio di errore
     * @param successMessage eventuale messaggio di successo
     * @param uiModel modello associato alla vista
     * @return nome della vista da visualizzare
     */
    @GetMapping(value = "/prenotazioni")
    public String showPrenotazioni(@RequestParam(value = "successMessage", required = false) String successMessage,
    		@RequestParam(value = "errorMessage", required = false) String errorMessage,
    		Model uiModel) {
    	logger.info("Listing all the prenotazioni...");

    	List<Prenotazione> allBookings = this.prenotazioneService.getAll();

    	uiModel.addAttribute("prenotazioni", allBookings);
    	uiModel.addAttribute("numPrenotazioni", allBookings.size());
    	uiModel.addAttribute("successMessage", successMessage);
    	uiModel.addAttribute("errorMessage", errorMessage);

    	return "prenotazioni.list";
    }



    /**
     * Metodo " GET " per la visualizzazione della lista di tutte le manutenzioni
     *
     * @param errorMessage eventuale messaggio di errore
     * @param successMessage eventuale messaggio di successo
     * @param uiModel modello associato alla vista
     * @return nome della vista da visualizzare
     */
    @GetMapping(value = "/manutenzioni")
    public String showManutenzioni(@RequestParam(value = "successMessage", required = false) String successMessage,
    		@RequestParam(value = "errorMessage", required = false) String errorMessage,
    		Model uiModel) {
    	logger.info("Listing all the manutenzioni...");

    	List<Manutenzione> allManutenzioni = this.manutenzioneService.getAll();

    	uiModel.addAttribute("manutenzioni", allManutenzioni);
    	uiModel.addAttribute("numManutenzioni", allManutenzioni.size());
    	uiModel.addAttribute("successMessage", successMessage);
    	uiModel.addAttribute("errorMessage", errorMessage);

    	return "manutenzioni.list";
    }


    /**
     * Metodo " GET " per la visualizzazione della lista di tutte le manutenzioni
     *
     * @param errorMessage eventuale messaggio di errore
     * @param successMessage eventuale messaggio di successo
     * @param uiModel modello associato alla vista
     * @return nome della vista da visualizzare
     */
    @GetMapping(value = "/documentazioni")
    public String showDocumentazioni(@RequestParam(value = "successMessage", required = false) String successMessage,
    		@RequestParam(value = "errorMessage", required = false) String errorMessage,
    		Model uiModel) {
    	logger.info("Listing all the documentazioni...");

    	List<Documentazione> allDocumentazioni = this.documentazioneService.getAll();

    	uiModel.addAttribute("documentazioni", allDocumentazioni);
    	uiModel.addAttribute("numDocumentazioni", allDocumentazioni.size());
    	uiModel.addAttribute("successMessage", successMessage);
    	uiModel.addAttribute("errorMessage", errorMessage);

    	return "documentazioni.list";
    }
}
