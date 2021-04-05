package it.univpm.advancedcode.cri.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import it.univpm.advancedcode.cri.model.entities.Car;
import it.univpm.advancedcode.cri.model.entities.User;
import it.univpm.advancedcode.cri.services.CarService;
import it.univpm.advancedcode.cri.services.UserService;

import java.io.File;
import javax.activation.MimetypesFileTypeMap;
import org.springframework.web.multipart.MultipartFile;



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
	 * Metodo " POST " per il salvataggio del utente
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











}
