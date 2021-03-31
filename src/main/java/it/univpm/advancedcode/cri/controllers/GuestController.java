package it.univpm.advancedcode.cri.controllers;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;



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
     * Metodo utilizzato per sfruttare la paginazione nella home.
     *
     * @param page numero della pagina
     * @param uiModel modello associato alla vista
     * @param cars lista di veicoli da paginare
     */
    private void carsPagination(@RequestParam(required = false) Integer page, Model uiModel, List<Car> cars) {

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
     * Metodo per la registrazione
     * 
     * @param uiModel modello associato alla vista
     * @return nome della vista da visualizzare
     */
    @GetMapping(value="/sign_up")
    	public String newUser(Model uiModel, @RequestParam(value="message", required = false) String message) {
    	logger.info("Creating a new user...");
    	
    	
    	uiModel.addAttribute("user", new User());
    	uiModel.addAttribute("message", message);
    		
    	return "sign_up";
    	}
            
    /**
     * Metodo per la richiesta POST di salvataggio nuovo utente
     * 
     * @param newUser utente registrato
     * @return redirect alla vista con la lista di tutti i posts
     */
    @PostMapping(value="/sign_up/save", consumes = "multipart/form-data")
    public String newUserSave(@ModelAttribute("newUser") User newUser, @RequestParam("image") MultipartFile file) {
    	logger.info("Saving a new user");

    	User u = this.userService.findUserByUsername(newUser.getUsername());

    	if( u != null) {
    		String notAvailable = "Username non disponibile, scegline un altro!";
    		return "redirect:/sign_up?message=" + notAvailable;
    	}
    	
    	
        if (file.isEmpty()) {
    	this.userService.create(newUser.getUsername(), newUser.getPassword(), newUser.getFirstname(), newUser.getLastname(), newUser.getRuolo());   	
    	} else {
            String nameOfFile = null;
            try {
                String uploadsDir = "/WEB-INF/files/profile_pictures/";
                String realPathtoUploads = request.getServletContext().getRealPath(uploadsDir);
                if (!new File(realPathtoUploads).exists()) {
                    logger.info("creating the directory...");
                    if(!new File(realPathtoUploads).mkdir()){
                        String strMessage = "ERRORE, impossibile creare la cartella nel server!";
                        return "redirect:/sign_up?message=" + strMessage;
                    }
                }

                logger.info("realPathtoUploads = {}", realPathtoUploads);
                // rename uploaded file with the username
                String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
                nameOfFile = newUser.getUsername() + "." + fileExtension;
                String filePath = realPathtoUploads + nameOfFile;
                File dest = new File(filePath);
                // controllo che sia un file immagine
                String mimetype= new MimetypesFileTypeMap().getContentType(dest);
                String type = mimetype.split("/")[0];
                if(!type.equals("image")) {
                    String strMessage = "ERRORE, il file specificato non %C3%A8 un'immagine!";
                    return "redirect:/sign_up?message=" + strMessage;
                }
                // sposto il file sulla cartella destinazione
                file.transferTo(dest);
            } catch (Exception e){
                e.printStackTrace();
            }

            this.userService.create(newUser.getUsername(), newUser.getPassword(), newUser.getFirstname(), newUser.getLastname(), nameOfFile);
        }

    	return "redirect:/login";
    }
    
    /**
     * Setter per la proprietà riferita al Service dell'entità Manutenzione.
     *
     * @param manutenzioneService
     */
    @Autowired
    public void setCarService(CarService carService) {
        this.carService = carService;
    }
    /**
     * Setter per la proprietà riferita al Service dell'entità User
     * 
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
     * Metodo per la visualizzazione della pagina statica privacy_policy
     * 
     */
    @GetMapping(value="/disclaimer")
    public String showPrivacyPolicy() {
    	
    	return "disclaimer";
    }

    /**
     * Metodo per la richiesta GET per la visualizzazione della lista di tags.
     *
     * @param errorMessage eventuale messaggio di errore
     * @param successMessage eventuale messaggio di successo
     * @param uiModel modello associato alla vista
     * @return nome della vista da visualizzare
     */
    @GetMapping(value = "/veicoli")
    public String showCars(@RequestParam(value = "successMessage", required = false) String successMessage,
                           @RequestParam(value = "errorMessage", required = false) String errorMessage, Model uiModel) {
        logger.info("Listing all the cars...");

        List<Car> allCarss = this.carService.getAll();

        uiModel.addAttribute("cars", allCarss);
        uiModel.addAttribute("numCars", allCarss.size());
        uiModel.addAttribute("successMessage", successMessage);
        uiModel.addAttribute("errorMessage", errorMessage);

        return "cars.list";
    }
}
