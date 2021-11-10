package de.bund.bva.flugplancrawler.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.bund.bva.flugplancrawler.entities.Departure;
import de.bund.bva.flugplancrawler.entities.FlightPlan;
import de.bund.bva.flugplancrawler.entities.AirportConfig;
import de.bund.bva.flugplancrawler.repository.DepartureRepository;
import de.bund.bva.flugplancrawler.repository.AirportConfigRepository;

/**
 * @author Bianca
 * Der ScrapperService übernimmt die eigentliche Aufgabe des Scrappers. Er holt sich die zu durchsuchenden Flughäfen aus der DB
 * und sucht auf den Websiten nach Flugplänen. Bisher geschieht dies nur für die Departures. Hier würde später noch eine Methode 
 * hinzugefügt werden für die Arrivals (ScrappAndStoreArrivals)
 */
@Service
public class ScrapperService  {

	
	@Autowired
	private DepartureRepository repoDepatures;
	
	@Autowired
	private AirportConfigRepository repoConfig;
	

	public void scrappAndStoreDepatures() {
		List<Departure> departures = new ArrayList<>();
		
		for(AirportConfig config : repoConfig.findAll()) {
			if(config.isAktiv()) {
				System.out.println("\n"+config.getAirportName());
				FlightPlan plan = new FlightPlan();
				plan.setPatternFlightPlan(config.getPattern());
				plan.setUrl(config.getUrl());
				plan.flightPlanHandler(config.getIdWholeFlightPlan(), config.getIdTableheaderFlightplan(), config.getIdInnerFlightTable(), config.getFlightPlanType());
				List<Departure> tmpDep = plan.getDepartures();
				if (tmpDep!=null){
		            departures.addAll(tmpDep);
		        }
		        System.out.println("\n --------------- \n");
			}	
		}
		repoDepatures.saveAll(departures);
	}
	
	
	


	


}
