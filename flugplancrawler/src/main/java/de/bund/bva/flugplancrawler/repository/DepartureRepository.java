package de.bund.bva.flugplancrawler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.bund.bva.flugplancrawler.entities.Departure;

/**
 * @author Bianca
 * Das Reporitory dient der Kommunikation mit der DB
 */
@Repository
public interface DepartureRepository extends JpaRepository<Departure, Integer> {

}
