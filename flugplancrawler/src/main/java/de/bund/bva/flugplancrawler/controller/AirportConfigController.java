package de.bund.bva.flugplancrawler.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import de.bund.bva.flugplancrawler.entities.AirportConfig;
import de.bund.bva.flugplancrawler.service.AirportConfigService;

/**
 * @author Bianca
 * Dieser Controller steuert die Funktionalit�ten auf der Flughafen Seite der Weboberfl�che
 */
@Controller
public class AirportConfigController {
	
    @Autowired
    private AirportConfigService fpService;
	
    /**
     * @param model
     * @return Flughafen Seite wird geladen
     * Diese Methode ist nicht per Button auszuw�hlen. Sie muss manuell im Browser eingegeben werden. Dies sollte nur bei der 1. Benutzung
     * gemacht werden. Alle bisherigen Flugh�fen werden mit den Abflug Informationen in der DB gespeichert.
     */
    @GetMapping("/seeding")
    public String triggerSeeding(Model model) {
    	fpService.initSeeding();
		model.addAttribute("listConfigs",fpService.getAllAirportConfigs());
		return "redirect:/airports";
    }
    

	/**
	 * @param id
	 * @param model
	 * @return Seite zur Bearbeitung eines Flughafen
	 * Diese Methode �ffnet eine Seite, die die Daten des aktuellen Flughafens anzeigt. �ber eine Checkbox kann
	 * der Flughafen aktiviert oder deaktiviert werden.
	 */
	@GetMapping("/showFormForAirportUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") Integer id, Model model) {
		// set departure as a model attribute to pre-populate the form
		model.addAttribute("config", fpService.getAirportConfigById(id));
		return "update_airport";
	}
    
	/**
	 * @param id
	 * @param model
	 * @return Seite um einen Flughafen zu l�schen
	 * Diese Methode �ffnet eine Seite, die die Daten des aktuellen Flughafens anzeigt. Darunter kann
	 * der Flughafen mit einem Button gel�scht werden.
	 */
	@GetMapping("/deleteAirport/{id}")
	public String deleteConfig(@PathVariable (value = "id") Integer id, Model model) {
		// set departure as a model attribute to pre-populate the form
		model.addAttribute("config", fpService.getAirportConfigById(id));
		return "/airport_confirm_delete";
	}
	
	/**
	 * @param id
	 * @return Flughafen Seite
	 * Diese Methode l�scht einen Flughafen endg�ltig aus der Liste.
	 */
	@GetMapping("/airportConfirmDelete/{id}")
	public String ConfirmDeleteConfig(@PathVariable (value = "id") Integer id) {
		fpService.deleteAirportConfigById(id);
		return "redirect:/airports";
	}
	
	/**
	 * @param config
	 * @return Flughafen Seite
	 * Diese Methode speichert die �nderungen an einem Flughafen in DB
	 */
	@PostMapping("/saveAirport")
	public String saveConfig(@ModelAttribute("config") AirportConfig config) {
	
		AirportConfig oldConfig = fpService.getAirportConfigById(config.getId());
		oldConfig.setAktiv(config.isAktiv());
		fpService.saveAirportConfig(oldConfig);
		return "redirect:/airports";
	}
	
	
    /**
     * @param model
     * @return Flughafen Seite
     * Diese Methode l�dt alle Flugh�fen aus der DB
     */
    @GetMapping("/airports")
   	public String airports(Model model) {
   		List<AirportConfig> configs = fpService.getAllAirportConfigs();
   		model.addAttribute("listConfigs", configs);
   		return "airport_index";
   	}
    



}
