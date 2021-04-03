package it.univpm.advancedcode.cri.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import it.univpm.advancedcode.cri.model.entities.Car;
import it.univpm.advancedcode.cri.model.entities.User;
import it.univpm.advancedcode.cri.services.CarService;
import it.univpm.advancedcode.cri.services.UserService;



@Controller
public class GuestController {
    private final Logger logger = LoggerFactory.getLogger(GuestController.class);
    private CarService carService;
    private UserService userService;

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
     * @param posts lista di post da paginare
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
        String strMessage;
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
        logger.info("Showing a new car...");
        String strMessage;
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
            // if (selectedCar.getPrenotazioni().size() == 0) {
            //     this.carService.delete(selectedCar);
            //     strMessage = "Il veicolo targato \"" + selectedCar.getTarga() + "\" %C3%A8 stato cancellato correttamente!";
            //     return "redirect:/cars/?successMessage=" + strMessage;
            // } else {
            //     strMessage = "Il veicolo targato \"" + selectedCar.getTarga() + "\" risulta essere prenotato... " +
            //             "Non pu%C3%B2 essere cancellato!";
            //     return "redirect:/cars/?errorMessage=" + strMessage;
            // }
        }else{
            strMessage = "Il veicolo targato non e' stato cancellato, ci sono problemi"+
            "Non pu%C3%B2 essere cancellato!";
            return "redirect:/cars/?errorMessage=" + strMessage;
        }
	}



    /**
	 * Metodo " POST " per il salvataggio del veicolo
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
		return "users.list";
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
		return "users.new";
	}
















}
