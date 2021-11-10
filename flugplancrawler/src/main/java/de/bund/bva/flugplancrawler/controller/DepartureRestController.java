package de.bund.bva.flugplancrawler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.bund.bva.flugplancrawler.entities.Departure;
import de.bund.bva.flugplancrawler.service.DepartureService;

/**
 * @author Bianca
 * Dieser WebService wird vom JavaScript Framework JQuery benötigt um die Abflug Tabelle darzustellen
 */
@RestController
@RequestMapping("/api/departure/")
public class DepartureRestController {
	
	@Autowired
	private DepartureService departureService;
	
	@RequestMapping(value = "findall", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<Departure>> findAll() {
		try {
			return new ResponseEntity<Iterable<Departure>>(departureService.getAllDepartures(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Iterable<Departure>>(HttpStatus.BAD_REQUEST);
		}
	}

}
