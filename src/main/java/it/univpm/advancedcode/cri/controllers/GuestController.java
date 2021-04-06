package it.univpm.advancedcode.cri.controllers;

import java.io.File;
import java.time.Duration;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.swing.text.Document;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.univpm.advancedcode.cri.model.entities.Allegato;
import it.univpm.advancedcode.cri.model.entities.Car;
import it.univpm.advancedcode.cri.model.entities.Documentazione;
import it.univpm.advancedcode.cri.model.entities.Link;
import it.univpm.advancedcode.cri.model.entities.Manutenzione;
import it.univpm.advancedcode.cri.model.entities.Prenotazione;
import it.univpm.advancedcode.cri.model.entities.User;

import it.univpm.advancedcode.cri.services.AllegatoService;
import it.univpm.advancedcode.cri.services.CarService;
import it.univpm.advancedcode.cri.services.DocumentazioneService;
import it.univpm.advancedcode.cri.services.FileService;
import it.univpm.advancedcode.cri.services.LinkService;
import it.univpm.advancedcode.cri.services.ManutenzioneService;
import it.univpm.advancedcode.cri.services.PrenotazioneService;
import it.univpm.advancedcode.cri.services.UserService;



@Controller
public class GuestController {
    private final Logger logger = LoggerFactory.getLogger(GuestController.class);
    private CarService carService;
    private UserService userService;
    private PrenotazioneService prenotazioneService; 
    private ManutenzioneService manutenzioneService; 
    private DocumentazioneService documentazioneService; 
    private FileService fileService; 
    private LinkService linkService; 
    private AllegatoService allegatoService; 

    @Autowired
    private HttpServletRequest request;
    
    /**
     * Metodo per il login
     */
    @GetMapping(value = "/login")
    public String loginPage(Authentication authentication, @RequestParam(value = "error", required = false) String error,
                            Model model) {
        if(authentication != null) {
            // user is already logged in
            String message = "Sei gi%C3%A0 autenticato!";
            return "redirect:/?successMessage=" + message;
        }
        String errorMessage = null;
        if(error != null) {
        	errorMessage = "Username o Password errati !!";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }
                
    /**
     * Setter per la proprietà riferita al Service dell'entità Car
     * @param carService
     */
    @Autowired
    public void setCarService(CarService carService) {
        this.carService = carService;
    }
    /**
     * Setter per la proprietà riferita al Service dell'entità User
     * @param userService Service dell'entità User da settare
     */
    @Autowired
    public void setUserService(UserService userService) {
    	this.userService = userService;
    }
    /**
     * Setter per la proprietà riferita al Service dell'entità Prenotazione
     * @param prenotazioneService Service dell'entità User da settare
     */
    @Autowired
    public void setPrenotazioneService(PrenotazioneService prenotazioneService) {
    	this.prenotazioneService = prenotazioneService;
    }
    /**
     * Setter per la proprietà riferita al Service dell'entità Manutenzione
     * @param manutenzioneService Service dell'entità User da settare
     */
    @Autowired
    public void setManutenzioneService(ManutenzioneService manutenzioneService) {
    	this.manutenzioneService = manutenzioneService;
    }
    /**
     * Setter per la proprietà riferita al Service dell'entità File
     * @param fileService Service dell'entità User da settare
     */
    @Autowired
    public void setFileService(FileService fileService) {
    	this.fileService = fileService;
    }
    /**
     * Setter per la proprietà riferita al Service dell'entità Link
     * @param linkService Service dell'entità User da settare
     */
    @Autowired
    public void setLinkService(LinkService linkService) {
    	this.linkService = linkService;
    }
	/**
     * Setter per la proprietà riferita al Service dell'entità Link
     * @param linkService Service dell'entità User da settare
     */
    @Autowired
    public void setAllegatoService(AllegatoService allegatoService) {
    	this.allegatoService = allegatoService;
    }





    /**
     * Setter per la proprietà riferita al Service dell'entità Documentazione
     * @param userService Service dell'entità User da settare
     */
    @Autowired
    public void setDocumentazioneService(DocumentazioneService documentazioneService) {
    	this.documentazioneService = documentazioneService;
    }
    
    /**
     * Metodo per la visualizzazione della pagina statica about_us
     * 
     */
    @GetMapping(value="/about")
    public String showAboutUs() {
    	return "about_us";
    }
    
    /**
     * Metodo per la visualizzazione della pagina statica contact_us
     * 
     */
    @GetMapping(value="/contacts")
    public String showContatcUs() {
    	return "contact_us";
    }

    /**
     * Metodo per la visualizzazione della pagina statica homepage
     * 
     */

    @GetMapping(value="/cri")
    public String showWebPage(@RequestParam(value = "successMessage", required = false) String successMessage,
                           @RequestParam(value = "errorMessage", required = false) String errorMessage,
                           @RequestParam(required = false) Integer page,
                           Model uiModel) {
    	 
    	logger.info("Listing all the cars...");
    	
	    List<Car> allCars = this.carService.getAll();    	

	    carsPagination(page, uiModel, allCars);
	    uiModel.addAttribute("cars", allCars);
	    uiModel.addAttribute("numCars",allCars.size());
	    uiModel.addAttribute("CarsTitle","Tutti i veicoli ha disposizione");
	    uiModel.addAttribute("successMessage", successMessage);
        uiModel.addAttribute("errorMessage", errorMessage);
	
        return "home";
    }

    /**
     * Metodo per la visualizzazione della pagina statica privacy_policy
     */
    @GetMapping(value="/disclaimer")
    public String showPrivacyPolicy() {
    	return "disclaimer";
    }

    /**
     * Metodo utilizzato per sfruttare la paginazione nella home.
     *
     * @param page numero della pagina
     * @param uiModel modello associato alla vista
     * @param cars lista di veicoli da paginare
     */
    private void carsPagination(@RequestParam(required = false) Integer page, Model uiModel, List<Car>
            cars) {

        PagedListHolder<Car> pagedListHolder = new PagedListHolder<>(cars);
        pagedListHolder.setPageSize(2);

        if(page == null || page < 1 || page > pagedListHolder.getPageCount()) page = 1;

        if(page <= pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(page - 1);
            uiModel.addAttribute("cars", pagedListHolder.getPageList());
        }

        uiModel.addAttribute("page", page);
        uiModel.addAttribute("maxPages", pagedListHolder.getPageCount());
    }

    //----------------- CARS ----------------//
    /**
     * Metodo " GET " lista dei veicoli
     * @param successMessage
     * @param errorMessage
     * @param uiModel
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
		return "car.list";
	}

    /**
	 * Metodo " GET " per la creazione di un veicolo 
	 * @param uiModel modello associato
	 * @return nome della vista
	 */
	@GetMapping(value = "/car/new")
	public String newCar(Model uiModel) {
		logger.info("Creating a new car...");
		uiModel.addAttribute("car", new Car());
		return "car.new";
	}

    /**
     * Metodo " GET " per la visualizzazione del un veicolo
     * @param targa
     * @param uiModel
     * @return nome della vista
     */
	@GetMapping(value = "/car/{targa}")
	public String showCar(@PathVariable("targa") String targa, Model uiModel) {
        Car selectedCar = this.carService.getByTarga(targa);
        logger.info("Showing a new car...");
        if (selectedCar != null){
            uiModel.addAttribute("car", selectedCar);
            return "car.show"; 
        }else{
            logger.info("Listing all the cars...");
            List<Car> cars = this.carService.getAll();
            uiModel.addAttribute("cars", cars);
            uiModel.addAttribute("numCars", cars.size());
            uiModel.addAttribute("successMessage", "");
            uiModel.addAttribute("errorMessage", "veicolo non trovato");
            return "car.list";
        }
	}


    /**
     * Metodo " GET " per la modifica del un veicolo
     * @param targa
     * @param uiModel
     * @return nome della vista
     */
	@GetMapping(value = "/car/edit/{targa}")
	public String editCar(@PathVariable("targa") String targa, Model uiModel) {
        Car selectedCar = this.carService.getByTarga(targa);
        logger.info("Edit a car...");
        if (selectedCar != null){
            uiModel.addAttribute("car", selectedCar);
            return "car.edit"; 
        }else{
            logger.info("Listing all the cars...");
            List<Car> cars = this.carService.getAll();
            uiModel.addAttribute("cars", cars);
            uiModel.addAttribute("numCars", cars.size());
            uiModel.addAttribute("successMessage", "");
            uiModel.addAttribute("errorMessage", "veicolo non trovato");
            return "car.list";
        }
	}

    /**
	 * Metodo " GET " per eliminare un veicolo
	 * @param targa targa del veicolo da cancellare
	 * @return nome della vista da visualizzare
	 */
	@GetMapping(value = "/car/delete/{targa}")
	public String deleteCar(@PathVariable("targa") String targa) {
		logger.info("Deleting car with with license plate \"" + targa + "\"...");

		Car selectedCar = this.carService.getByTarga(targa);
		String strMessage;
        if(selectedCar != null){
            this.carService.delete(selectedCar);
            strMessage = "Il veicolo targato \"" + selectedCar.getTarga() + "\" %C3%A8 stato cancellato correttamente!";
            return "redirect:/cars/?successMessage=" + strMessage;
            // fare controlli su prenotazione ...
        }else{
            strMessage = "Il veicolo targato non e' stato cancellato, ci sono problemi"+
            "Non pu%C3%B2 essere cancellato!";
            return "redirect:/cars/?errorMessage=" + strMessage;
        }
	}



    /**
	 * Metodo " Documentazione " per il salvataggio del veicolo
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
            this.carService.create(car.getId(), car.getTarga(), car.getMarca(), car.getModello(), 
            car.getNumeroTelaio(), car.getMassa(), car.getDestinazioneUso(), car.getNumeroAssi(), car.getAlimentazione());
			String strMessage = "Il veicolo targa \"" + car.getTarga() + "\" %C3%A8 stato salvato correttamente!";
			return "redirect:/cars/?successMessage=" + strMessage;
		} catch (RuntimeException e) {
			return "redirect:/cars/?errorMessage=" + e.getMessage();
		}
	}

    /**
     * Metodo update car 
     * @param car car to update
     * @param br binding result
     * @param uiModel
     * @return nome della vista da visualizzare 
     */

    @PostMapping(value = "/car/edit/save", consumes = "multipart/form-data")
	public String saveEditCar(@ModelAttribute("carToEdit") Car car, BindingResult br, Model uiModel) {
		logger.info("Saving the edited car...");
		try {
			this.carService.update(car);
			String strMessage = "Il veicolo %C3%A8 stato salvato correttamente!";
			return "redirect:/cars?successMessage=" + strMessage;
		} catch (RuntimeException e) {
			return "redirect:/cars?errorMessage=" + e.getMessage();
		}
	}

    //------------------ USERS ------------------------//
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
		return "user.list";
	}

        /**
     * Metodo " GET " per la visualizzazione di un utente
     * @param username
     * @param uiModel
     * @return nome della vista
     */
	@GetMapping(value = "/user/{username}")
	public String showUser(@PathVariable("username") String username, Model uiModel) {
        User selectedUser = this.userService.findUserByUsername(username); 
        logger.info("Showing a user...");
        if (selectedUser != null){
            uiModel.addAttribute("user", selectedUser);
            return "user.profile"; 
        }else{
            logger.info("Listing all the users...");
            List<User> users = this.userService.findAll();
            uiModel.addAttribute("users", users);
            uiModel.addAttribute("numUsers", users.size());
            uiModel.addAttribute("successMessage", "");
            uiModel.addAttribute("errorMessage", "utente non trovato");
            return "user.list";
        }
	}


    /**
	 * Metodo " GET " per la creazione di un utente 
	 * @param uiModel modello associato
	 * @return nome della vista
	 */
	@GetMapping(value = "/user/new")
	public String newUsers(Model uiModel) {
		logger.info("Creating a new user...");
		uiModel.addAttribute("user", new User());
		return "user.new";
	}

        /**
     * Metodo " GET " per la modifica di un utente
     * @param targa
     * @param uiModel
     * @return nome della vista
     */
	@GetMapping(value = "/user/edit/{username}")
	public String editUser(@PathVariable("username") String username, Model uiModel) {
        User selectedUser = this.userService.findUserByUsername(username);
        logger.info("Edit a user...");
        if (selectedUser != null){
            uiModel.addAttribute("user", selectedUser);
            return "user.edit"; 
        }else{
            logger.info("Listing all the users...");
            List<User> users = this.userService.findAll();
            uiModel.addAttribute("users", users);
            uiModel.addAttribute("numUsers", users.size());
            uiModel.addAttribute("successMessage", "");
            uiModel.addAttribute("errorMessage", "utente non trovato");
            return "user.list";
        }
	}


        /**
	 * Metodo " GET " per eliminare un utente
	 * @param username  username del utente da cancellare
	 * @return nome della vista
	 */
	@GetMapping(value = "/user/delete/{username}")
	public String deleteUser(@PathVariable("username") String username) {
		logger.info("Deleting user \"" + username + "\"...");

		User selectedUser = this.userService.findUserByUsername(username);
		String strMessage;
        if(selectedUser != null){
            this.userService.delete(selectedUser);
            strMessage = "L'utente : \"" + selectedUser.getUsername() + "\" %C3%A8 stato cancellato correttamente!";
            return "redirect:/users/?successMessage=" + strMessage;
            //fare dovuti controlli
        }else{
            strMessage = "L'utente non e' stato cancellato, ci sono problemi"+
            "Non pu%C3%B2 essere cancellato!";
            return "redirect:/users/?errorMessage=" + strMessage;
        }
	}


    /**
	 * Metodo " Documentazione " per il salvataggio del utente
	 * @param car           car restituita dalla richiesta
	 * @param bindingResult eventuali errori di validazione
	 * @param uiModel       modello associato alla vista
	 * @return nome della vista da visualizzare
	 */


	@PostMapping(value = "/user/new/save")
	public String saveUser(@ModelAttribute("user") User user, BindingResult bindingResult, Model uiModel) {
		logger.info("Saving a new user...");
        if((user.getFirstname() == null || user.getFirstname().equals("")) || 
            (user.getLastname() == null || user.getLastname().equals("")) || 
            (user.getUsername() == null || user.getUsername().equals("")) || 
            (user.getRuolo() == null || user.getRuolo().equals("")) ||
            (user.getPassword() == null || user.getPassword().equals(""))){
            String strMessage = "Non hai inserito i campi obbligatori !"; 
            return "redirect:/users/?errorMessage=" + strMessage; 
        }
        
		try {
            this.userService.create(user.getUsername(), user.getPassword(), user.getFirstname(), user.getLastname(), user.getRuolo());
			String strMessage = "L'utente :  \"" + user.getUsername() + "\" %C3%A8 stato salvato correttamente!";
			return "redirect:/users/?successMessage=" + strMessage;
		} catch (RuntimeException e) {
			return "redirect:/users/?errorMessage=" + e.getMessage();
		}
    }

    /**
     * Metodo update user
     * @param user user to update 
     * @param br binding result
     * @param uiModel
     * @param file
     * @return nome della vista da visualizzare 
     */

    @PostMapping(value = "/user/edit/save", consumes = "multipart/form-data")
	public String saveProfile(@ModelAttribute("userToEdit") User user, BindingResult br, Model uiModel,
							  @RequestParam("image") MultipartFile file) {
		logger.info("Saving the edited user...");
		if (!file.isEmpty()) {
			String nameOfFile = null;
			try {
				String uploadsDir = "/WEB-INF/files/profile_pictures/";
				String realPathtoUploads = request.getServletContext().getRealPath(uploadsDir);
				if (!new java.io.File(realPathtoUploads).exists()) {
					logger.info("creating the directory...");
					if (!new java.io.File(realPathtoUploads).mkdir()) {
						String strMessage = "ERRORE, impossibile creare la cartella nel server!";
						return "redirect:/users?errorMessage=" + strMessage;
					}
				}
				logger.info("realPathtoUploads = {}", realPathtoUploads);
				// rename uploaded file with the username
				String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
				nameOfFile = user.getUsername() + "." + fileExtension;
				String filePath = realPathtoUploads + nameOfFile;
				java.io.File dest = new File(filePath);
				// controllo che sia un file immagine
				String mimetype = new MimetypesFileTypeMap().getContentType(dest);
				String type = mimetype.split("/")[0];
				if (!type.equals("image")) {
					String strMessage = "ERRORE, il file specificato non %C3%A8 un'immagine!";
					return "redirect:/users?errorMessage=" + strMessage;
				}
				// sposto il file sulla cartella destinazione
				file.transferTo(dest);
			} catch (Exception e) {
				e.printStackTrace();
			}

			user.setImageProfile(nameOfFile);
		}
		try {
			this.userService.update(user);
			String strMessage = "Il tuo profilo utente %C3%A8 stato salvato correttamente!";
			return "redirect:/users?successMessage=" + strMessage;
		} catch (RuntimeException e) {

			return "redirect:/users?errorMessage=" + e.getMessage();

		}
	}


    //------------------- PRENOTAZIONI --------------//
    /**
	 * Metodo " GET " per la visualizzazione della lista di tutte le prenotazioni
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
	 * Metodo " GET " per eliminare una prenotazione
	 * @param id id della prenotazione da cancellare
	 * @return nome della vista da visualizzare
	 */
	@GetMapping(value = "/prenotazione/delete/{id}")
	public String deletePrenotazione(@PathVariable("id") Long id) {
		logger.info("Deleting booking with id \"" + id + "\"...");
        Prenotazione selectedPrenotazione = this.prenotazioneService.getById(id); 
		String strMessage;
        if(selectedPrenotazione != null){
            this.prenotazioneService.delete(selectedPrenotazione);
            strMessage = "La prenotazione relativa alla data:  \"" + selectedPrenotazione.getDataInizio() + "\" %C3%A8 stato cancellata correttamente!";
            return "redirect:/prenotazioni/?successMessage=" + strMessage;
            // fare controlli su prenotazione ...
        }else{
            strMessage = "La prenotazione non può essere cancellata, ci sono problemi"+
            "Non pu%C3%B2 essere cancellata!";
            return "redirect:/prenotazioni/?errorMessage=" + strMessage;
        }
	}


    /**
	 * Metodo " GET " per la creazione di una prenotazione 
	 * @param uiModel modello associato
	 * @return nome della vista
	 */
	@GetMapping(value = "/prenotazione/new")
	public String newPrenotazione(Model uiModel) {
		logger.info("Creating a new prenotazione...");
		uiModel.addAttribute("prenotazione", new Prenotazione());
		return "prenotazione.new";
	}


	@PostMapping(value = "/prenotazione/new/save")
	public String savePrenotazione(@ModelAttribute("prenotazione") Prenotazione prenotazione, BindingResult bindingResult, Model uiModel) {
		logger.info("Saving a new prenotazione...");
        if(
            (prenotazione.getDataInizio().toString() == null) || 
            (prenotazione.getDataFine().toString() == null) ||
            (prenotazione.getOraInizio().toString() == null) ||
            (prenotazione.getOraFine().toString() == null) //||
            //(prenotazione.getDescrizione().equals("") || prenotazione.getDescrizione() == null ) ||
            //(prenotazione.getVeicolo() == null) ||
            //(prenotazione.getUtente() == null)){
        ){    
            String strMessage = "Non hai inserito i campi obbligatori !"; 
            return "redirect:/prenotazioni/?errorMessage=" + strMessage;                 


            
        }else if(Period.between(prenotazione.getDataInizio(),prenotazione.getDataFine()).getDays() < 0){
            String strMessage = "Le date inserite non sono corrette !"; 
            return "redirect:/prenotazioni/?errorMessage=" + strMessage; 
        }else if(Duration.between(prenotazione.getOraInizio(),prenotazione.getOraFine()).isNegative()){
            String strMessage = "Gli orari inseriti non sono corretti !"; 
            return "redirect:/prenotazioni/?errorMessage=" + strMessage; 
        }

		try {
            this.prenotazioneService.create(prenotazione.getId(), prenotazione.getDataInizio(), 
            prenotazione.getDataFine(), prenotazione.getOraInizio(), prenotazione.getOraFine(), 
            prenotazione.getDescrizione(), prenotazione.getVeicolo(), prenotazione.getUtente());
			String strMessage = "La prenotazione :  \"" + prenotazione.getId() + "\" %C3%A8 stata salvato correttamente!";
			return "redirect:/prenotazioni/?successMessage=" + strMessage;
		} catch (RuntimeException e) {
			return "redirect:/prenotazioni/?errorMessage=" + e.getMessage();
		}
    }
    
    /**
     * Metodo " GET " per la visualizzazione di una prenotazione
     * @param username
     * @param uiModel
     * @return nome della vista
     */
	@GetMapping(value = "/prenotazione/{id}")
	public String showPrenotazione(@PathVariable("id") Long id, Model uiModel) {
        Prenotazione selectedPrenotazione = this.prenotazioneService.getById(id); 
        logger.info("Showing a prenotazione...");
        if (selectedPrenotazione != null){
            uiModel.addAttribute("prenotazione", selectedPrenotazione);
            return "prenotazione.show"; 
        }else{
            logger.info("Listing all prenotazioni...");
            List<Prenotazione> allBookings = this.prenotazioneService.getAll();
            uiModel.addAttribute("prenotazioni", allBookings);
            uiModel.addAttribute("numPrenotazioni", allBookings.size());
            uiModel.addAttribute("successMessage", "OK");
            uiModel.addAttribute("errorMessage", "ERROR");
            return "prenotazioni.list";
        }
	}

    /**
     * Metodo " GET " per la modifica di una prenotazione
     * @param id
     * @param uiModel
     * @return nome della vista
     */
	@GetMapping(value = "/prenotazione/edit/{id}")
	public String editPrenotazione(@PathVariable("id") Long id, Model uiModel) {
        Prenotazione selectedPrenotazione = this.prenotazioneService.getById(id);
        logger.info("Edit a prenotazione...");
        if (selectedPrenotazione != null){
            uiModel.addAttribute("prenotazione", selectedPrenotazione);
            return "prenotazione.edit"; 
        }else{
            logger.info("Listing all prenotazioni...");
            List<Prenotazione> allBookings = this.prenotazioneService.getAll();
            uiModel.addAttribute("prenotazioni", allBookings);
            uiModel.addAttribute("numPrenotazioni", allBookings.size());
            uiModel.addAttribute("successMessage", "OK");
            uiModel.addAttribute("errorMessage", "ERROR");
            return "prenotazioni.list";
        }
	}

    
    /**
     * Metodo update prenotazione 
     * @param prenotazione prenotazione to update
     * @param br binding result
     * @param uiModel
     * @return nome della vista da visualizzare 
     */

    @PostMapping(value = "/prenotazione/edit/save", consumes = "multipart/form-data")
	public String saveEditPrenotazione(@ModelAttribute("prenotazione") Prenotazione prenotazione, BindingResult br, Model uiModel) {
		logger.info("Saving the edited prenotazione...");
		try {
			this.prenotazioneService.update(prenotazione);
			String strMessage = "La prenotazione %C3%A8 stata salvato correttamente!";
			return "redirect:/prenotazioni?successMessage=" + strMessage;
		} catch (RuntimeException e) {
			return "redirect:/prenotazioni?errorMessage=" + e.getMessage();
		}
	}

    //------------------- MANUTENZIONI --------------//
    /**
	 * Metodo " GET " per la visualizzazione della lista di tutte le manutenzioni
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
	 * Metodo " GET " per eliminare una manutenzione
	 * @param id id della manutenzione da cancellare
	 * @return nome della vista da visualizzare
	 */
	@GetMapping(value = "/manutenzione/delete/{id}")
	public String deleteManutenzione(@PathVariable("id") Long id) {
		logger.info("Deleting manutentizioni with id \"" + id + "\"...");
        Manutenzione selectedManutenzione = this.manutenzioneService.getById(id); 
		String strMessage;
        if(selectedManutenzione != null){
            this.manutenzioneService.delete(selectedManutenzione);
            strMessage = "La manutenzione relativa alla data:  \"" + selectedManutenzione.getId() + "\" %C3%A8 stata cancellata correttamente!";
            return "redirect:/manutenzioni/?successMessage=" + strMessage;
            // fare controlli su prenotazione ...
        }else{
            strMessage = "La manutenzione non può essere cancellata, ci sono problemi"+
            "Non pu%C3%B2 essere cancellata!";
            return "redirect:/manutenzioni/?errorMessage=" + strMessage;
        }
	}


    /**
	 * Metodo " GET " per la creazione di una manutenzione 
	 * @param uiModel modello associato
	 * @return nome della vista
	 */
	@GetMapping(value = "/manutenzione/new")
	public String newManutenzione(Model uiModel) {
		logger.info("Creating a new manutenzione...");
		uiModel.addAttribute("manutenzione", new Manutenzione());
		return "manutenzione.new";
	}




    /**
	 * Metodo " Documentazione " per il salvataggio del veicolo
	 * @param car           car restituita dalla richiesta
	 * @param bindingResult eventuali errori di validazione
	 * @param uiModel       modello associato alla vista
	 * @return nome della vista da visualizzare
	 */
	@PostMapping(value = "/manutenzione/new/save")
	public String saveManutenzione(@ModelAttribute("manutenzione") Manutenzione manutenzione, BindingResult bindingResult, Model uiModel) {
		logger.info("Saving a new manutenzione...");
		if(manutenzione.getCostoManutenzione() == 0) {
			String strMessage = "Non hai inserito i campi obbligatori!";
			return "redirect:/manutenzioni/?errorMessage=" + strMessage;
		}

		try {
            this.manutenzioneService.create(manutenzione.getId(), manutenzione.getTipoManutenzione(), manutenzione.getCostoManutenzione(), manutenzione.getVeicolo());
            String strMessage = "La manutenzione, id: \"" + manutenzione.getId() + "\" %C3%A8 stata salvato correttamente!";
			return "redirect:/manutenzioni/?successMessage=" + strMessage;
		} catch (RuntimeException e) {
			return "redirect:/manutenzioni/?errorMessage=" + e.getMessage();
		}
	}

        /**
     * Metodo " GET " per la modifica di una prenotazione
     * @param id
     * @param uiModel
     * @return nome della vista
     */
	@GetMapping(value = "/manutenzione/edit/{id}")
	public String editManutenzione(@PathVariable("id") Long id, Model uiModel) {
        Manutenzione selectedManutenzione = this.manutenzioneService.getById(id);
        logger.info("Edit a manutenzione...");
        if (selectedManutenzione != null){
            uiModel.addAttribute("manutenzione", selectedManutenzione);
            return "manutenzione.edit"; 
        }else{
            logger.info("Listing all the manutenzioni...");
            List<Manutenzione> allManutenzioni = this.manutenzioneService.getAll();
            uiModel.addAttribute("manutenzioni", allManutenzioni);
            uiModel.addAttribute("numManutenzioni", allManutenzioni.size());
            uiModel.addAttribute("successMessage", "OK");
            uiModel.addAttribute("errorMessage", "ERRORE");
            return "manutenzioni.list";
        }
	}

    
    /**
     * Metodo update prenotazione 
     * @param prenotazione prenotazione to update
     * @param br binding result
     * @param uiModel
     * @return nome della vista da visualizzare 
     */

    @PostMapping(value = "/manutenzione/edit/save", consumes = "multipart/form-data")
	public String saveEditManutenzione(@ModelAttribute("manutenzione") Manutenzione manutenzione, BindingResult br, Model uiModel) {
		logger.info("Saving the edited manutenzione...");
		try {
			this.manutenzioneService.update(manutenzione);
			String strMessage = "La manutenzione %C3%A8 stata salvata correttamente!";
			return "redirect:/manutenzioni?successMessage=" + strMessage;
		} catch (RuntimeException e) {
			return "redirect:/manutenzioni?errorMessage=" + e.getMessage();
		}
	}

    //---------------DOCUMENTAZIONI -----------------//


    /**
	 * Metodo " GET " per la visualizzazione della lista di tutte le documentazioni
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

	/**
	 * Metodo per la richiesta GET di modifica di un documento
	 *
	 * @param authentication informazioni per 'autenticazione corrente
	 * @param documento_id	id del documento da modificare
	 * @param uiModel	porzione di modello da passare alla vista
	 * @return			nome della vista da renderizzare
	 */
	@GetMapping(value="/documentazione/edit/{documento_id}")
	public String editDocumentazione(Authentication authentication, @PathVariable("documento_id") long documento_id, Model uiModel) {
		logger.info("Modifying a documento");

		Documentazione documento = documentazioneService.getById(documento_id);
		List<User> allUsers = userService.findAll();
		List<Car> allCars = carService.getAll();
		if(documento != null) {

			uiModel.addAttribute("documentazione", documento);
			uiModel.addAttribute("allUsers", allUsers);
			uiModel.addAttribute("allCars", allCars);
			uiModel.addAttribute("titlePageForm", "Modifica il documento \"" + documento.getTitolo() + "\"");
			return "documentazione.edit";

		} else {
			String message = "Errore, documento ...";
			return "redirect:/documentazioni?errorMessage=" + message;
		}
	}

	/**
	 * Metodo per la richiesta GET di eliminazione documento
	 * @param id		id del commento da rimuovere
	 * @param auth informazioni dell'autenticazione corrente
	 * @return			redirect all'indirizzo cui fare richiesta
	 */
	@GetMapping(value = "/documentazione/delete/{documento_id}")
	public String deleteDocumentazione (@PathVariable("documento_id") long id, Authentication auth) {
		logger.info("Deleting a documentazione...");

		Documentazione documento = documentazioneService.getById(id);
		if(documento == null) {
			String message = "Errore, documento ... !";
			return "redirect:/documentazioni?errorMessage=" + message;
		}

		this.documentazioneService.delete(documento);
		String message = "Documento \"" + documento.getTitolo() + "\" eliminato correttamente!";
		return "redirect:" + "/documentazioni/?successMessage=" + message;
	}

	/**
	 * Metodo per la richiesta GET della gestione degli allegati di un documento
	 *
	 * @param id ID del documento
	 * @param uiModel modello associato alla vista
	 * @param auth informazioni dell'autenticazione corrente
	 * @return nome della vista da visualizzare
	 */
	@GetMapping(value="/documentazione/{documento_id}/allegati")
	public String documentazioneAllegati(@PathVariable("documento_id") long id, Model uiModel, Authentication auth) {

		Documentazione documento = documentazioneService.getById(id);
		if(documento == null) {
			String message = "Errore, documento ... !";
			return "redirect:/documentazioni?errorMessage=" + message;
		}

		// get all file allegati
        List<it.univpm.advancedcode.cri.model.entities.File> filesDocumentazione = new ArrayList<>();
		for (Allegato allegato:documento.getAllegati()) {
			it.univpm.advancedcode.cri.model.entities.File file = fileService.getById(allegato.getId());
			if( file != null) {
				filesDocumentazione.add(file);
			}
		}


		// get all link allegati
		List<Link> linksDocumentazione = new ArrayList<>();
		for (Allegato allegato:documento.getAllegati()) {
			Link link = linkService.getById(allegato.getId());
			if( link != null) {
				linksDocumentazione.add(link);
			}
		}

		uiModel.addAttribute("filesDocumentazione", filesDocumentazione);
		uiModel.addAttribute("linksDocumentazione", linksDocumentazione);
		uiModel.addAttribute("documento_id", id);

		return "documentazione.allegati";
	}

	/**
	 * Metodo per la richiesta GET di creazione di un nuovo allegato di tipo link.
	 *
	 * @param documento_id ID del documento
	 * @param uiModel modello associato alla vista
	 * @param auth informazioni dell'autenticazione corrente
	 * @return nome della vista da visualizzare
	 */
	@GetMapping(value="/documentazione/edit/{documento_id}/allegati/link/new")
	public String newLink(@PathVariable("documento_id") long documento_id, Model uiModel, Authentication auth) {

		Documentazione documento = documentazioneService.getById(documento_id);
		if(documento == null) {
			String message = "Errore link, documento ...!";
			return "redirect:/allegati?errorMessage=" + message;
		}

		uiModel.addAttribute("link", new Link());
		uiModel.addAttribute("documento_id", documento_id);
		uiModel.addAttribute("pageTitle","Inserisci Link");
		return "link.new";
	}

	/**
	 * Metodo per la richiesta GET di creazione di un nuovo allegato di tipo file.
	 * @param documento_id ID del documento
	 * @param uiModel modello associato alla vista
	 * @param auth informazioni dell'autenticazione corrente
	 * @return nome della vista da visualizzare
	 */
	@GetMapping(value="/documentazione/edit/{documento_id}/allegati/file/new")
	public String newFile(@PathVariable("documento_id") long documento_id, Model uiModel, Authentication auth) {

		Documentazione documento = documentazioneService.getById(documento_id);
		if(documento == null) {
			String message = "Errore link, documento ...!";
			return "redirect:/allegati?errorMessage=" + message;
		}

		uiModel.addAttribute("file", new it.univpm.advancedcode.cri.model.entities.File());
		uiModel.addAttribute("documento_id", documento_id);
		uiModel.addAttribute("pageTitle","Inserisci File");
		return "file.new";
	}

	/**
	 * Metodo per la richiesta GET di modifica di un link.
	 * @param documento_id ID del documento
	 * @param linkId ID del link da modificare
	 * @param uiModel modello associato alla vista
	 * @param auth informazioni dell'autenticazione corrente
	 * @return nome della vista da visualizzare
	 */
	@GetMapping(value="/documentazione/edit/{documento_id}/allegati/link/{linkID}/edit")
	public String editLink(@PathVariable("documento_id") long documento_id, @PathVariable("linkID") long linkId, Model uiModel,
						   Authentication auth) {

        Documentazione documento = documentazioneService.getById(documento_id);
		Allegato allegato = allegatoService.getById(linkId);
		if(documento == null || allegato == null) {
			String message = "Errore link, documento ...!";
			return "redirect:/allegati?errorMessage=" + message;
		}

		uiModel.addAttribute("link", linkService.getById(linkId));
		uiModel.addAttribute("documento_id", documento_id);

		return "link.edit";
	}

	/**
	 * Metodo per la richiesta GET di modifica di un file.
	 * @param documento_id ID del documento
	 * @param fileId ID del file da modificare
	 * @param uiModel modello associato alla vista
	 * @param auth informazioni dell'autenticazione corrente
	 * @return nome della vista da visualizzare
	 */
	@GetMapping(value="/documentazione/edit/{documento_id}/allegati/file/{fileID}/edit")
	public String editFile(@PathVariable("documento_id") long documento_id, @PathVariable("fileID") long fileId, Model uiModel,
						   Authentication auth) {
        Documentazione documento = documentazioneService.getById(documento_id);
        Allegato allegato = allegatoService.getById(fileId);
		if(documento == null || allegato == null ) {
			String message = "Errore file, documento ...!";
			return "redirect:/allegati?errorMessage=" + message;
		}

		uiModel.addAttribute("file", fileService.getById(fileId));
		uiModel.addAttribute("documento_id", documento_id);

		return "file.edit";
	}

	/**
	 * Metodo per la richiesta POST di salvataggio di un link.
	 * @param link link da salvare
	 * @param documento_id ID del documento
	 * @return nome della vista da visualizzare
	 */
	@PostMapping(value = "/documentazione/edit/{documento_id}/allegati/link/save")
	public String saveLink (@ModelAttribute("link") Link link, @PathVariable("documento_id") long documento_id) {
		logger.info("Saving a link allegati...");
		try {
			link.setDocumento(this.documentazioneService.getById(documento_id)); 
			this.linkService.update(link);
			String message = "Il link \"" + link.getDescrizione() + "\" %C3%A8 stato salvato correttamente!";
			return "redirect:" + "/documentazioni/?successMessage=" + message;

		} catch (RuntimeException e) {
			return "redirect:" + "/documentazioni/?errorMessage=" + e.getMessage();
		}
	}

	/**
	 * Metodo per la richiesta POST di salvataggio di un nuovo file.
	 *
	 * @param newFile nuovo file
	 * @param documento_id ID del documento
	 * @param uploadedFile file caricato nel form
	 * @return nome della vista da visualizzare
	 */
	@PostMapping(value = "/documentazione/edit/{documento_id}/allegati/file/new/save", consumes = "multipart/form-data")
	public String saveNewFile (@ModelAttribute("file") it.univpm.advancedcode.cri.model.entities.File newFile,
							   @PathVariable("documento_id") long documento_id, @RequestParam("fileAllegato") MultipartFile
									   uploadedFile) {
		logger.info("Saving the new file...");

		if (!uploadedFile.isEmpty()) {
			String nameOfFile = null;
			try {
				String uploadsDir = "/WEB-INF/files/documentazione_allegati/";
				String realPathtoUploads = request.getServletContext().getRealPath(uploadsDir);
				if (!new java.io.File(realPathtoUploads).exists()) {
					logger.info("creating the directory...");
					if (!new java.io.File(realPathtoUploads).mkdir()) {
						String strMessage = "ERRORE, impossibile creare la cartella nel server!";
						return "redirect:/documentazioni?errorMessage=" + strMessage;
					}
				}

				logger.info("realPathtoUploads = {}", realPathtoUploads);
				nameOfFile = "documento" + documento_id + "_" + uploadedFile.getOriginalFilename();
				String filePath = realPathtoUploads + nameOfFile;
				java.io.File dest = new File(filePath);
				// sposto il file sulla cartella destinazione
				uploadedFile.transferTo(dest);
			} catch (Exception e) {
				e.printStackTrace();
			}

			newFile.setName(nameOfFile);
		} else {
			String message = "ERRORE, non hai caricato alcun file!";
			return "redirect:/documentazioni?errorMessage=" + message;
		}

		try {
			newFile.setDocumento(documentazioneService.getById(documento_id)); 
			this.fileService.update(newFile);
			String strMessage = "Il file \"" + newFile.getDescrizione() + "\" %C3%A8 stato salvato correttamente!";
			return "redirect:/documentazioni?successMessage=" + strMessage;
		} catch (RuntimeException e) {
			return "redirect:/documentazioni?errorMessage=" + e.getMessage();
		}
	}

	/**
	 * Metodo per la richiesta Documentazione di salvataggio di un file modificato.
	 * @param file file da salvare
	 * @param documento_id ID del documento
	 * @return nome della vista da visualizzare
	 */
	@PostMapping(value = "/documentazione/edit/{documento_id}/allegati/file/save")
	public String saveEditedFile (@ModelAttribute("file") it.univpm.advancedcode.cri.model.entities.File file,
								  @PathVariable("documento_id") long documento_id) {
		logger.info("Saving the edited file...");
		try {
			file.setDocumento(this.documentazioneService.getById(documento_id));
			this.fileService.update(file);
			String message = "Il file \"" + file.getDescrizione() + "\" %C3%A8 stato salvato correttamente!";

			return "redirect:" + "/documentazioni/?successMessage=" + message;

		} catch (RuntimeException e) {
			return "redirect:" + "/documentazioni/?errorMessage=" + e.getMessage();
		}
	}




}
