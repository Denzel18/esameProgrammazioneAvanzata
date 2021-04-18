package it.univpm.advancedcode.cri.controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

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
public class AccountController {

	@Autowired
	private HttpServletRequest request;

	private UserService userService;
    private ManutenzioneService manutenzioneService; 
    private DocumentazioneService documentazioneService; 
    private FileService fileService; 
    private LinkService linkService; 
    private AllegatoService allegatoService; 
	private CarService carService;
	private PrenotazioneService prenotazioneService; 

	private final Logger logger = LoggerFactory.getLogger(AccountController.class);


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
     * Setter per la proprietà riferita al Service dell'entità Documentazione
     * @param userService Service dell'entità User da settare
     */
    @Autowired
    public void setDocumentazioneService(DocumentazioneService documentazioneService) {
    	this.documentazioneService = documentazioneService;
    }

	    /**
     * Setter per la proprietà riferita al Service dell'entità Prenotazione
     * @param prenotazioneService Service dell'entità User da settare
     */
    @Autowired
    public void setPrenotazioneService(PrenotazioneService prenotazioneService) {
    	this.prenotazioneService = prenotazioneService;
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
		List<Car> allCars = carService.getAll(); 
		uiModel.addAttribute("allCars", allCars);
		uiModel.addAttribute("manutenzione", new Manutenzione());
		return "manutenzione.new";
	}




    /**
	 * Metodo " POST " per il salvataggio della manutenzione
	 * @param manutenzione  manutenzione restituita dalla richiesta
	 * @param bindingResult eventuali errori di validazione
	 * @param uiModel       modello associato alla vista
	 * @return nome della vista da visualizzare
	 */
	@PostMapping(value = "/manutenzione/new/save")
	public String saveManutenzione(
		@ModelAttribute("manutenzione") Manutenzione manutenzione, 
		BindingResult bindingResult, Model uiModel,
		@RequestParam(value = "veicolo", required = false) long veicolo_id) {
		logger.info("Saving a new manutenzione...");
		if(manutenzione.getCostoManutenzione() == 0) {
			String strMessage = "Non hai inserito i campi obbligatori!";
			return "redirect:/manutenzioni/?errorMessage=" + strMessage;
		}

		try {
			Car veicolo = carService.getById(veicolo_id);
            this.manutenzioneService.create(manutenzione.getId(), manutenzione.getTipoManutenzione(), manutenzione.getCostoManutenzione(), veicolo);
            String strMessage = "La manutenzione, %C3%A8 stata salvata correttamente!";
			return "redirect:/manutenzioni/?successMessage=" + strMessage;
		} catch (RuntimeException e) {
			return "redirect:/manutenzioni/?errorMessage=" + e.getMessage();
		}
	}

        /**
     * Metodo " GET " per la modifica di una manutenzione
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
     * Metodo update manutenzione 
     * @param prenotazione manutenzione to update
     * @param br binding result
     * @param uiModel
     * @return nome della vista da visualizzare 
     */

    @PostMapping(value = "/manutenzione/edit/save", consumes = "multipart/form-data")
	public String saveEditManutenzione(@ModelAttribute("manutenzione") Manutenzione manutenzione, BindingResult br, Model uiModel,@RequestParam(value = "veicolo", required = false) String targa) {
		logger.info("Saving the edited manutenzione...");
		try {
			Car veicolo = carService.getByTarga(targa);
			manutenzione.setVeicolo(veicolo);
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
	 * Metodo per la richiesta GET di creazione nuovo documento
		* @param uiModel porzione di modello da passare alla vista
		* @param auth
		* @return nome della vista da renderizzare
	*/
	@GetMapping(value = "/documentazione/new")
	public String newDocumento(Model uiModel, Authentication auth) {
		logger.info("Creating a new documento");

		List<Car> cars = carService.getAll();
		//User user = userService.findUserByUsername(auth.getName());
		List<User> users = userService.findAll();
		uiModel.addAttribute("documentazione", new Documentazione());
		uiModel.addAttribute("allCars", cars);
		uiModel.addAttribute("allUsers", users);
		uiModel.addAttribute("titlePageForm", "Inserisci un nuovo documento");

		return "documentazione.new";
	}


	/**
	 * Metodo per la richiesta POST di salvataggio di un nuovo documento
	 * @param documento documento da salvare ottenutod alla form
	 * @param br		eventuali errori di validazione
	 * @param uiModel 	porzione di modello da passare alla vista
	 * @return			redirect all'indirizzo cui fare richiesta
	 */
	@PostMapping(value="/documentazione/save")
	public String saveDocumentazione(@ModelAttribute("documentazione") Documentazione documento, BindingResult br, Model uiModel,
	 @RequestParam(value = "veicolo_id", required = false) long veicolo_id, 
	 @RequestParam(value = "username", required = false) String username) {

			logger.info("Saving the documento");
			
			Documentazione found_documento = this.documentazioneService.getById(documento.getId());
			User autore = userService.findUserByUsername(username);
			Car veicolo = carService.getById(veicolo_id);
			
			
			try {
				documento.setUtente(autore);
				documento.setVeicolo(veicolo);			
				//creo
				if (found_documento==null) {
					this.documentazioneService.create(documento.getId(), documento.getTitolo(), autore, documento.getDescrizione(), documento.getDataScadenza(), 
					documento.getCosto(), veicolo); 			
				}
				//modifico
				else {
					this.documentazioneService.update(documento);
				}
				String strMessage = "Documento \"" + documento.getTitolo() + "\" salvato correttamente";
				return "redirect:/documentazioni?successMessage=" + strMessage;
	
			} catch (RuntimeException e) {
				String strMessage = "ERRORE: " + e.getMessage();
				return "redirect:/documentazioni?errorMessage=" + strMessage;
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
			String message = "Errore file, documento ...!";
			return "redirect:/documentazioni?errorMessage=" + message;
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

	/**
	 * Metodo per la richiesta GET di eliminazione di un allegato.
	 * @param auth informazioni dell'autenticazione corrente
	 * @param id ID dell'allegato da eliminare
	 * @return nome della vista da visualizzare
	 */
	@GetMapping(value = "/allegato/{allegato_id}/delete")
	public String deleteAttachment (Authentication auth, @PathVariable("allegato_id") long id) {
		logger.info("Cancellazione allegato...");

		Allegato allegato = allegatoService.getById(id);
		if(allegato == null) {
			String message = "Errore, allegato non trovato!";
			return "redirect:/documentazioni?errorMessage=" + message;
		}

		this.allegatoService.delete(allegato);
		String message = "L'allegato \"" + allegato.getDescrizione() + "\" %C3%A8 stato eliminato correttamente!";
		return "redirect:" + "/documentazioni/?successMessage=" + message;
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


}