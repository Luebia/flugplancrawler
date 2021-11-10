package de.bund.bva.flugplancrawler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import de.bund.bva.flugplancrawler.entities.Departure;
import de.bund.bva.flugplancrawler.service.DepartureService;

/**
 * @author Bianca
 * Der Controller dient der Steuerung der Funktionen auf der Abflug Seite im Browser
 */
@Controller
public class DepartureController {

	
	@Autowired
	private DepartureService departureService;
	
	/**
	 * @param model
	 * @return Abflug Seite
	 * diese dient auch als Startseite
	 */
	@GetMapping("/departures")
	public String viewHomePage(Model model) {
		return "departures_index";
	}
	
	/**
	 * @param departure
	 * @return Abflug Seite
	 * ruft eine Methode im DepartureService auf
	 */
	@PostMapping("/saveDeparture")
	public String saveDeparture(@ModelAttribute("departure") Departure departure) {
		departureService.saveDeparture(departure);
		return "redirect:/departures";
	}
	
	/**
	 * @param id
	 * @param model
	 * @return Seite zur Bearbeitung eines Abflugs
	 * lädt das Objekt anhand der ID aus der DB 
	 */
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") Integer id, Model model) {
		Departure departure = departureService.getDepartureById(id);
		model.addAttribute("departure", departure);
		return "update_departure";
	}
	
	/**
	 * @param id
	 * @param model
	 * @return Seite zum Löschen eines Abflugs
	 * lädt das Objekt anhand der ID aus der DB
	 */
	@GetMapping("/deleteDeparture/{id}")
	public String deleteDeparture(@PathVariable (value = "id") Integer id, Model model) {
		
		Departure departure = departureService.getDepartureById(id);
		model.addAttribute("departure", departure);
		return "/departure_confirm_delete";
	}
	
	
	/**
	 * @param id
	 * @return Abflug Seite
	 * das anhand der ID geladene Objekt wird aus der DB gelöscht
	 */
	@GetMapping("/departureConfirmDelete/{id}")
	public String confirmDeleteDeparture(@PathVariable (value = "id") Integer id) {
		this.departureService.deleteDepartureById(id);
		return "/departures_index";
	}
	
	

	
	
}
