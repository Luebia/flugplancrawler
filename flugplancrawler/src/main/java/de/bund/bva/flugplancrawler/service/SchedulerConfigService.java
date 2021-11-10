package de.bund.bva.flugplancrawler.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.bund.bva.flugplancrawler.entities.SchedulerConfig;
import de.bund.bva.flugplancrawler.repository.SchedulerConfigRepository;
import de.bund.bva.flugplancrawler.scheduler.FlightScheduler;

/**
 * @author Bianca
 * Der Service enthält die Logik für den SchedulerConfigController
 */
@Service
public class SchedulerConfigService {
	
	@Autowired
	private SchedulerConfigRepository repoSC;
	
    @Autowired
    private FlightScheduler scheduler;
	
	/**
	 * @param id
	 * @return SchedulerConfig Objekt
	 * findet das Aktualisierungsintervall in DB anhand der ID 
	 */
	public SchedulerConfig getSchedulerConfigById(Integer id) {
		return repoSC.getById(id);
	}
	
	/**
	 * @param config
	 * speichert Aktualisierungsintervall in der DB
	 */
	public void saveSchedulerConfig (SchedulerConfig config) {
		repoSC.save(config);
	}
	
	/**
	 * @param minutes
	 * greift auf eine Methode im FlightScheduler zu
	 */
	public void reSchedule(int minutes) {
		scheduler.updateSchedule(minutes);
	}
	
	/**
	 * @return Liste mit Aktualisierungsintervallen
	 * greift auf die DB zu und holt alle Suchintervalle raus. Allerdings 
	 * besteht die Liste immer nur
	 * aus einem Eintrag.
	 */
	public List<SchedulerConfig> getAllSchedulerConfigs(){
		return repoSC.findAll();
	}
	
	/**
	 * @return nächste Suchlauf Zeit
	 * greift auf eine Methode im FlightScheduler zu
	 */
	public Date getNextSyncExecutionTime() {
		return scheduler.getNextSyncExecutionTime();
	}
	
	/**
	 * greift auf eine Methode im FlightScheduler zu
	 */
	public void triggerScheduler() {
		scheduler.trigger();
	}
	

}
