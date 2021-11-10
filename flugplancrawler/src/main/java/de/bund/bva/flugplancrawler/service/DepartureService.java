package de.bund.bva.flugplancrawler.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import de.bund.bva.flugplancrawler.entities.Departure;
import de.bund.bva.flugplancrawler.repository.DepartureRepository;

/**
 * @author Bianca
 * Der Service enthält die Logik für den DepartureController
 */
@Service
public class DepartureService{

	@Autowired
	private DepartureRepository departureRepository;	
	
	/**
	 * @return Liste mit Departures
	 * das Repository holt alle Abflug Objekte aus der DB
	 */
	public List<Departure> getAllDepartures() {
		
		return departureRepository.findAll();
	}

	/**
	 * @param departure
	 * der übergebene Abflug wird mittels des Repositprys in DB gespeichert
	 */
	public void saveDeparture(Departure departure) {
		this.departureRepository.save(departure);
		
	}

	/**
	 * @param id
	 * @return Abflug Objekt
	 * das Repository holt ein Abflug Objekt anhand seiner ID aus der DB
	 */
	public Departure getDepartureById(Integer id) {
		Optional<Departure> optional = departureRepository.findById(id);
		Departure departure = null;
		if (optional.isPresent()) {
			departure = optional.get();
		} else {
			throw new RuntimeException(" Abflug nicht gefunden für ID :: " + id);
		}
		return departure;
	}

	/**
	 * @param id
	 * das Repository löscht ein Abflug Objekt anhand seiner ID aus der DB
	 */
	public void deleteDepartureById(Integer id) {
		this.departureRepository.deleteById(id);
		
	}

}
