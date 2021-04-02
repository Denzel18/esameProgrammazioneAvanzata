package it.univpm.advancedcode.cri.controllers;

import java.io.File;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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

		return "cars.list";
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
