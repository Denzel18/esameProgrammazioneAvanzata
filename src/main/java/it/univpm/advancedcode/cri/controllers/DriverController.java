package it.univpm.advancedcode.cri.controllers;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import it.univpm.advancedcode.cri.model.entities.Prenotazione;
import it.univpm.advancedcode.cri.model.entities.User;
import it.univpm.advancedcode.cri.services.CarService;
import it.univpm.advancedcode.cri.services.PrenotazioneService;
import it.univpm.advancedcode.cri.services.UserService;



@Controller
public class DriverController {

    private final Logger logger = LoggerFactory.getLogger(DriverController.class);
    private UserService userService;
    private PrenotazioneService prenotazioneService; 
    private CarService carService; 

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
     * Setter per la proprietà riferita al Service dell'entità Prenotazione
     * @param prenotazioneService Service dell'entità User da settare
     */
    @Autowired
    public void setCarService(CarService carService) {
    	this.carService = carService;
    }


    //------------------ PROFILE ------------------------//

	/**
	 * Metodo per la richiesta GET di visualizzazione dettagli profilo
	 *
	 * @param auth informazioni dell'autenticazione
	 * @param errorMessage eventuale messaggio di errore
	 * @param successMessage eventuale messaggio di successo
	 * @param uiModel	porzione del modello da passare alla vista
	 * @return			nome della vista da renderizzare
	 */
	@GetMapping(value = "/profile")
	public String showUserProfile (Authentication auth, Model uiModel,
								   @RequestParam(value = "successMessage", required = false) String successMessage,
								   @RequestParam(value = "errorMessage", required = false) String errorMessage) {
		logger.info("Showing Profile...");

		if (auth != null) {
			User userLoggedIn = this.userService.findUserByUsername(auth.getName());
			uiModel.addAttribute("user", userLoggedIn);
			uiModel.addAttribute("successMessage", successMessage);
			uiModel.addAttribute("errorMessage", errorMessage);
			return "user.profile";
		}

		else {
			String noAuthMessage = "Nessun utente autenticato.";
			return "redirect:/?errorMessage=" + noAuthMessage;
		}
	}


    //------------------- PRENOTAZIONI --------------//
    /**
	 * Metodo " GET " per la visualizzazione della lista di tutte le prenotazioni dell'utente
	 * @param errorMessage eventuale messaggio di errore
	 * @param successMessage eventuale messaggio di successo
	 * @param uiModel modello associato alla vista
	 * @return nome della vista da visualizzare
	 */
	@GetMapping(value = "/myprenotazioni")
	public String showDriverPrenotazioni(@RequestParam(value = "successMessage", required = false) String successMessage,
			@RequestParam(value = "errorMessage", required = false) String errorMessage,
			Model uiModel, Authentication auth) {
		logger.info("Listing all the prenotazioni...");

		List<Prenotazione> allBookings = this.prenotazioneService.getPrenotazioneByUtente(auth.getName());
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
	public String deletePrenotazione(@PathVariable("id") Long id, Authentication authentication) {
		logger.info("Deleting booking with id \"" + id + "\"...");
        User loggedUser = userService.findUserByUsername(authentication.getName());
        String redirect = "redirect:/prenotazioni/"; 
        if(loggedUser.getRuolo().equals("driver")){
            redirect =  "redirect:/myprenotazioni/";
        }
        if(loggedUser.getRuolo().equals("account")){
            redirect = "redirect:/prenotazioni/"; 
        }

        Prenotazione selectedPrenotazione = this.prenotazioneService.getById(id); 
		String strMessage;
        if(selectedPrenotazione != null){
            this.prenotazioneService.delete(selectedPrenotazione);
            strMessage = "La prenotazione relativa alla data:  \"" + selectedPrenotazione.getDataInizio() + "\" %C3%A8 stato cancellata correttamente!";
            return redirect+"?successMessage=" + strMessage;
            // fare controlli su prenotazione ...
        }else{
            strMessage = "La prenotazione non può essere cancellata, ci sono problemi"+
            "Non pu%C3%B2 essere cancellata!";
            return redirect+"?successMessage=" + strMessage;
        }
	}


    /**
	 * Metodo " GET " per la creazione di una prenotazione 
	 * @param uiModel modello associato
     * @param authentication x accedere a utente logato
	 * @return nome della vista
	 */
	@GetMapping(value = "/prenotazione/new")
	public String newPrenotazione(Model uiModel, Authentication authentication) {
		logger.info("Creating a new prenotazione...");
        List<Car> allCars = carService.getAll(); 
        User user = userService.findUserByUsername(authentication.getName());
		uiModel.addAttribute("prenotazione", new Prenotazione());
        uiModel.addAttribute("allCars", allCars);
        uiModel.addAttribute("user", user.getUsername());
        uiModel.addAttribute("titlePageForm", "Inserisci un nuova prenotazione");
		return "prenotazione.new";
	}

	@PostMapping(value = "/prenotazione/new/save")
    public String savePrenotazione(@ModelAttribute("prenotazione") Prenotazione prenotazione, BindingResult bindingResult, Model uiModel, 
    @RequestParam(value = "veicolo", required = false) long veicolo_id, 
    //-------DATA INIZIO------//
    @RequestParam(value = "dataInizio", required = true) String dataInizio,
    //-------DATA FINE------//
    @RequestParam(value = "dataFine", required = true) String dataFine,
    //-------ORA INIZIO------//    
    @RequestParam(value = "oraInizio", required = true) String oraInizio,
    //-------ORA INIZIO------//    
    @RequestParam(value = "oraFine", required = true) String oraFine,
    @RequestParam(value = "utente", required = false) String username, Authentication authentication) {

        User utente = userService.findUserByUsername(username);
        Car veicolo = carService.getById(veicolo_id);
        logger.info("Saving a new prenotazione...");

        User loggedUser = userService.findUserByUsername(authentication.getName());
        String redirect = "redirect:/prenotazioni/"; 
        if(loggedUser.getRuolo().equals("driver")){
            redirect =  "redirect:/myprenotazioni/";
        }
        if(loggedUser.getRuolo().equals("account")){
            redirect = "redirect:/prenotazioni/"; 
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");
        LocalDate data_Inizio = LocalDate.parse(dataInizio,formatter);
        LocalDate data_Fine = LocalDate.parse(dataFine,formatter);	
        LocalTime ora_Inizio = LocalTime.parse(oraInizio, formatterTime);
        LocalTime ora_Fine = LocalTime.parse(oraFine, formatterTime);

        prenotazione.setDataInizio(data_Inizio);
        prenotazione.setDataFine(data_Fine);
        prenotazione.setOraInizio(ora_Inizio);
        prenotazione.setOraFine(ora_Fine);

        if ((prenotazione.getDataInizio() == null) ||
            (prenotazione.getDataFine() == null) ||
            (prenotazione.getOraFine() == null) ||
            (prenotazione.getOraInizio() == null) ||
            (prenotazione.getDescrizione().equals("") || prenotazione.getDescrizione() == null )){    
            String strMessage = "Non hai inserito i campi obbligatori !"+prenotazione.toString(); 
            return redirect+"?errorMessage=" + strMessage;    
        }else if(veicolo == null || utente == null){
            String strMessage = "Non hai inserito i campi obbligatori _!"; 
            return redirect+"?errorMessage=" + strMessage;  
        }else if(Period.between(prenotazione.getDataInizio(),prenotazione.getDataFine()).getDays() < 0){
            String strMessage = "Le date inserite non sono corrette !"; 
            return redirect+"?errorMessage=" + strMessage; 
        }else if(Duration.between(prenotazione.getOraInizio(),prenotazione.getOraFine()).isNegative()){
            String strMessage = "Gli orari inseriti non sono corretti !"; 
            return redirect+"?errorMessage=" + strMessage; 
        }

        try {
            this.prenotazioneService.create(prenotazione.getId(), data_Inizio, data_Fine, ora_Inizio, ora_Fine, 
            prenotazione.getDescrizione(), veicolo,utente);
            String strMessage = "La prenotazione :  \"" + prenotazione.getDescrizione() + "\" %C3%A8 stata salvato correttamente!";
            return redirect+"?successMessage=" + strMessage;
        }catch (RuntimeException e) {
            return redirect+"?errorMessage=" + e.getMessage();
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
	public String saveEditPrenotazione(@ModelAttribute("prenotazione") Prenotazione prenotazione, BindingResult br, Model uiModel,
    @RequestParam(value = "veicolo", required = false) long veicolo_id, 
    @RequestParam(value = "utente", required = false) String username,
        //-------DATA INIZIO------//
        @RequestParam(value = "dataInizio", required = true) String dataInizio,
        //-------DATA FINE------//
        @RequestParam(value = "dataFine", required = true) String dataFine,
        //-------ORA INIZIO------//    
        @RequestParam(value = "oraInizio", required = true) String oraInizio,
        //-------ORA INIZIO------//    
        @RequestParam(value = "oraFine", required = true) String oraFine,
    Authentication authentication) {

        User utente = userService.findUserByUsername(username);
        Car veicolo = carService.getById(veicolo_id); 

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");
        LocalDate data_Inizio = LocalDate.parse(dataInizio,formatter);
        LocalDate data_Fine = LocalDate.parse(dataFine,formatter);	
        LocalTime ora_Inizio = LocalTime.parse(oraInizio, formatterTime);
        LocalTime ora_Fine = LocalTime.parse(oraFine, formatterTime);

        User loggedUser = userService.findUserByUsername(authentication.getName());
        String redirect = "redirect:/prenotazioni/"; 
        if(loggedUser.getRuolo().equals("driver")){
            redirect =  "redirect:/myprenotazioni/";
        }
        if(loggedUser.getRuolo().equals("account")){
            redirect = "redirect:/prenotazioni/"; 
        }

		logger.info("Saving the edited prenotazione...");
		try {
            prenotazione.setVeicolo(veicolo);
            prenotazione.setUtente(utente);
            prenotazione.setDataInizio(data_Inizio);
            prenotazione.setDataFine(data_Fine);
            prenotazione.setOraInizio(ora_Inizio);
            prenotazione.setOraFine(ora_Fine);
			this.prenotazioneService.update(prenotazione);
			String strMessage = "La prenotazione %C3%A8 stata salvato correttamente!";
			return redirect+"?successMessage=" + strMessage;
		} catch (RuntimeException e) {
			return redirect+"?errorMessage=" + e.getMessage();
		}
	}

}
