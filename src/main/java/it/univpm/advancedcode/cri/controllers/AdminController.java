package it.univpm.advancedcode.cri.controllers;

import java.io.File;
import java.time.Duration;
import java.time.LocalDate;
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
public class AdminController {
	private final Logger logger = LoggerFactory.getLogger(AdminController.class);

	private AllegatoService allegatoService;
	private CarService carService;
	private DocumentazioneService documentazioneService;
	private ManutenzioneService manutenzioneService;
	private PrenotazioneService prenotazioneService;
	private UserService userService;

    @Autowired
    private HttpServletRequest request;

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



    //----------------- CARS ----------------//
    /**
     * Metodo " GET " lista dei veicoli
     * @param successMessage
     * @param errorMessage
     * @param uiModel
     * @return nome della vista
     */
    @GetMapping(value = "/cars_admin")
	public String showCars(@RequestParam(value = "successMessage", required = false) String successMessage,
			@RequestParam(value = "errorMessage", required = false) String errorMessage,
			Model uiModel) {
		logger.info("Listing all the cars...");
		List<Car> cars = this.carService.getAll();
		uiModel.addAttribute("cars", cars);
		uiModel.addAttribute("numCars", cars.size());
		uiModel.addAttribute("successMessage", successMessage);
		uiModel.addAttribute("errorMessage", errorMessage);
		return "car.list_admin";
	}

     /**
     * Metodo " GET " per la visualizzazione del un veicolo
     * @param targa
     * @param uiModel
     * @return nome della vista
     */
	@GetMapping(value = "/car_admin/{targa}")
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
            return "car.list_admin";
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

    //-------------PRENOTAZIONI--------------//
    /**
	 * Metodo " GET " per la visualizzazione della lista di tutte le prenotazioni
	 * @param errorMessage eventuale messaggio di errore
	 * @param successMessage eventuale messaggio di successo
	 * @param uiModel modello associato alla vista
	 * @return nome della vista da visualizzare
	 */
	@GetMapping(value = "/prenotazioni_admin")
	public String showPrenotazioni(@RequestParam(value = "successMessage", required = false) String successMessage,
			@RequestParam(value = "errorMessage", required = false) String errorMessage,
			Model uiModel) {
		logger.info("Listing all the prenotazioni...");

		List<Prenotazione> allBookings = this.prenotazioneService.getAll();
		uiModel.addAttribute("prenotazioni", allBookings);
		uiModel.addAttribute("numPrenotazioni", allBookings.size());
		uiModel.addAttribute("successMessage", successMessage);
		uiModel.addAttribute("errorMessage", errorMessage);
		return "prenotazioni.list_admin";
	}


    //------------------- MANUTENZIONI --------------//
    /**
	 * Metodo " GET " per la visualizzazione della lista di tutte le manutenzioni
	 * @param errorMessage eventuale messaggio di errore
	 * @param successMessage eventuale messaggio di successo
	 * @param uiModel modello associato alla vista
	 * @return nome della vista da visualizzare
	 */
	@GetMapping(value = "/manutenzioni_admin")
	public String showManutenzioni(@RequestParam(value = "successMessage", required = false) String successMessage,
			@RequestParam(value = "errorMessage", required = false) String errorMessage,
			Model uiModel) {
		logger.info("Listing all the manutenzioni...");
		List<Manutenzione> allManutenzioni = this.manutenzioneService.getAll();
		uiModel.addAttribute("manutenzioni", allManutenzioni);
		uiModel.addAttribute("numManutenzioni", allManutenzioni.size());
		uiModel.addAttribute("successMessage", successMessage);
		uiModel.addAttribute("errorMessage", errorMessage);
		return "manutenzioni.list_admin";
	}



    //---------------DOCUMENTAZIONI -----------------//
    /**
	 * Metodo " GET " per la visualizzazione della lista di tutte le documentazioni
	 * @param errorMessage eventuale messaggio di errore
	 * @param successMessage eventuale messaggio di successo
	 * @param uiModel modello associato alla vista
	 * @return nome della vista da visualizzare
	 */
	@GetMapping(value = "/documentazioni_admin")
	public String showDocumentazioni(@RequestParam(value = "successMessage", required = false) String successMessage,
			@RequestParam(value = "errorMessage", required = false) String errorMessage,
			Model uiModel) {
		logger.info("Listing all the documentazioni...");
		List<Documentazione> allDocumentazioni = this.documentazioneService.getAll();
		uiModel.addAttribute("documentazioni", allDocumentazioni);
		uiModel.addAttribute("numDocumentazioni", allDocumentazioni.size());
		uiModel.addAttribute("successMessage", successMessage);
		uiModel.addAttribute("errorMessage", errorMessage);
		return "documentazioni.list_admin";
	}
}