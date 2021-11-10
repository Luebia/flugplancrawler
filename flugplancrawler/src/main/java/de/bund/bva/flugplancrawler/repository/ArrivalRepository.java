package de.bund.bva.flugplancrawler.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.bund.bva.flugplancrawler.entities.Arrival;

/**
 * @author Bianca
 * Das Repository habe ich bereits vorbereitet, damit die Arrivals auch irgendwann in der DB gespeichert werden.
 */
public interface ArrivalRepository extends JpaRepository<Arrival, Integer>{

}
