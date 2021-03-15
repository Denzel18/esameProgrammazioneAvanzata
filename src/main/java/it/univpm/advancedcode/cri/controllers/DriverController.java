package it.univpm.advancedcode.cri.controllers;

import java.io.File;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.univpm.advancedcode.cri.model.entities.User;
import it.univpm.advancedcode.cri.services.FileService;
import it.univpm.advancedcode.cri.services.LinkService;
import it.univpm.advancedcode.cri.services.UserService;

@Controller
public class DriverController {

	private final Logger logger = LoggerFactory.getLogger(DriverController.class);

	private UserService userService;
	
	private LinkService linkService;
	private FileService fileService;
	
	
	
	@Autowired
	private HttpServletRequest request;

/**
	 * Setter per la proprietà userService, relativa alla classe di servizio degli Utenti
	 *
	 * @param userService Service dell'entità User da settare
	 */
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}


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
			return "users.profile";
		}

		else {
			String noAuthMessage = "Nessun utente autenticato.";
			return "redirect:/?errorMessage=" + noAuthMessage;
		}
	}


	/**
	 * Metodo per la richiesta GET di modifica profilo utente
	 *
	 * @param uiModel	porzione del modello da passare alla vista
	 * @return			nome della vista da renderizzare
	 */
	@GetMapping(value = "/profile/edit")
	public String editProfile (Authentication auth, Model uiModel) {


		if (auth != null) {
			User userLoggedIn = this.userService.findUserByUsername(auth.getName());
			uiModel.addAttribute("userToEdit", userLoggedIn);
			return "users.editProfile";
		}

		else {
			String message = "Nessun utente autenticato.";
			return "redirect:/?errorMessage=" + message;
		}

	}

	/**
	 * Metodo per al richiesta POST di salvataggio modifiche al profilo utente
	 *
	 * @param profile	profilo utente modificato
	 * @param br		eventuali errori di validazione
	 * @param uiModel	porzione del modello da passare alla vista
	 * @return			redirect all'indirizzo cui fare richiesta
	 */
	@PostMapping(value = "/profile/edit/save", consumes = "multipart/form-data")
	public String saveProfile(@ModelAttribute("userToEdit") User profile, BindingResult br, Model uiModel,
							  @RequestParam("image") MultipartFile file) {
		logger.info("Saving the edited profile...");
		if (!file.isEmpty()) {
			String nameOfFile = null;
			try {
				String uploadsDir = "/WEB-INF/files/profile_pictures/";
				String realPathtoUploads = request.getServletContext().getRealPath(uploadsDir);
				if (!new java.io.File(realPathtoUploads).exists()) {
					logger.info("creating the directory...");
					if (!new java.io.File(realPathtoUploads).mkdir()) {
						String strMessage = "ERRORE, impossibile creare la cartella nel server!";
						return "redirect:/profile?errorMessage=" + strMessage;
					}
				}

				logger.info("realPathtoUploads = {}", realPathtoUploads);
				// rename uploaded file with the username
				String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
				nameOfFile = profile.getUsername() + "." + fileExtension;
				String filePath = realPathtoUploads + nameOfFile;
				java.io.File dest = new File(filePath);
				// controllo che sia un file immagine
				String mimetype = new MimetypesFileTypeMap().getContentType(dest);
				String type = mimetype.split("/")[0];
				if (!type.equals("image")) {
					String strMessage = "ERRORE, il file specificato non %C3%A8 un'immagine!";
					return "redirect:/profile?errorMessage=" + strMessage;
				}
				// sposto il file sulla cartella destinazione
				file.transferTo(dest);
			} catch (Exception e) {
				e.printStackTrace();
			}

			profile.setImageProfile(nameOfFile);
		}
		try {
			this.userService.update(profile);
			String strMessage = "Il tuo profilo utente %C3%A8 stato salvato correttamente!";
			return "redirect:/profile?successMessage=" + strMessage;
		} catch (RuntimeException e) {

			return "redirect:/profile?errorMessage=" + e.getMessage();

		}
	}
	
	@GetMapping(value="/profile/delete")
	public String deleteProfile(Authentication auth, Model uiModel) {
		if (auth != null) {
			User userLoggedIn = this.userService.findUserByUsername(auth.getName());
			this.userService.delete(userLoggedIn);
			return "redirect:/logout";
		}

		else {
			String message = "Nessun utente autenticato.";
			return "redirect:/?errorMessage=" + message;
		}
	}
}
