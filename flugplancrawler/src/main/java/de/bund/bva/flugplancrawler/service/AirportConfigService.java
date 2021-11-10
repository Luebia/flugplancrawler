package de.bund.bva.flugplancrawler.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.bund.bva.flugplancrawler.entities.AirportConfig;
import de.bund.bva.flugplancrawler.repository.AirportConfigRepository;

/**
 * @author Bianca
 * Diese Klasse enth�lt die Logik f�r den AirportConfigController
 */
@Service
public class AirportConfigService {
	
	@Autowired
	private AirportConfigRepository repoFP;
	
    /**
     * @return Liste mit Flugh�fen
     * alle Flugh�fen werden zur�ck gegeben
     */
    public List<AirportConfig> getAllAirportConfigs () {
    	return repoFP.findAll();
    }
    
    /**
     * @param id
     * @return Flughafen-Objekt
     * ein Flughafen-Objekt wird zur�ck gegeben
     */
    public AirportConfig getAirportConfigById(Integer id) {
    	return repoFP.getById(id);
    }
    
    /**
     * @param id
     * ein Flughafen wird anhand seiner ID aus der DB gel�scht
     */
    public void deleteAirportConfigById(Integer id) {
    	repoFP.deleteById(id);
    }
    
    /**
     * @param config
     * Flughafen�nderung wird in DB gespeichert
     */
    public void saveAirportConfig(AirportConfig config) {
    	repoFP.save(config);
    }
    
    /**
     * Initialisiert die Flugh�fen. Muss beim ersten Start des Programms manuell ausgef�hrt werden (siehe AirportConfigController)
     */
    public void initSeeding() {
    	// DB-L�schen
   	 repoFP.deleteAll();
   	
   	
   	// Liste mit allen bisherigen Configs
   	List<AirportConfig> configs = new ArrayList<AirportConfig>();
   	
   	// K�ln Abflug
   	AirportConfig configCGN = new AirportConfig();
		configCGN.setAirportName("K�ln Bonn Airport");
		configCGN.setIata("EDDK");
		configCGN.setIcao("CGN");
		configCGN.setAktiv(true);
		configCGN.setUrl("https://www.koeln-bonn-airport.de/fluege/abflug-ankunft.html");
		configCGN.setIdWholeFlightPlan("flights");
		configCGN.setIdTableheaderFlightplan("panel flight-header");
		configCGN.setIdInnerFlightTable("panel flight");
		configCGN.setFlightPlanType("Abflug");
		configCGN.setPattern("(?>Flugziel Check-In Gate Flug\u00AD?nummer Status "
   		+ "Ab\u00AD?flug\u00AD?zeit)?([a-zA-Zöäüöäü]*\\W?[a-zA-Zöäüöäü]*\\W?[a-zA-Zöäüöäü]"
   		+ "*\\W?\\(\\w\\w\\w\\)\\s)(\\w\\d\\/\\w\\d\\d\\-\\w\\d\\d\\s)?(\\w\\d\\d\\s)?(\\w\\w\\s\\d+\\s)(?>Terminal"
   		+ "\\s\\d\\s)?(\\d?\\.?\\s?[a-zA-ZÖÄÜöäü]+\\s?[a-zA-ZÖÄÜöäü]*\\W?[a-zA-ZÖÄÜöäü]*\\s?\\/?\\s?"
   		+ "[a-zA-ZÖÄÜöäü]*\\s?\\/?\\s?[a-zA-ZÖÄÜöäü]*)?(\\d\\d\\:\\d\\d\\s?\\d?\\d?\\W?\\d?\\d?)");
		configs.add(configCGN);
		
		//Bremen Abflug
		AirportConfig configBRE = new AirportConfig();
		configBRE.setAirportName("Bremen Airport Hans Koschnick");
		configBRE.setIata("EDDW");
		configBRE.setIcao("BRE");
		configBRE.setAktiv(true);
		configBRE.setUrl("https://www.bremen-airport.com/fluginfo/fluege/tagesflugplan/");
		configBRE.setIdWholeFlightPlan("departureTab");
		configBRE.setIdTableheaderFlightplan("c-flights-info_head");
		configBRE.setIdInnerFlightTable("c-flights-info-detail");
		configBRE.setFlightPlanType("Abflug");
		configBRE.setPattern("(\\\\w\\\\w\\\\w?\\\\s?\\\\d+\\\\w?\\\\s)(?>Airline\\\\W)?(\\\\w\\\\w\\\\w?\\\\s)([a-zA-ZöäüÖÄÜ]+\\\\s)()(?>Abflug\\\\W*)?(\\\\d\\\\d\\\\:\\\\d\\\\d\\\\s)(\\\\d\\\\d\\\\:\\\\d\\\\d\\\\s)?([a-zA-ZÖÄÜ]+\\\\s)?(Terminal\\\\W\\\\d\\\\s|BELUGA\\\\WXL\\\\s)(?>Terminal\\\\W*X\\\\s)?(?>Counter\\\\W*)?(\\\\d+\\\\W\\\\-\\\\W\\\\d+\\\\s|\\\\d\\\\W\\\\-\\\\W\\\\d)?(?>Gate\\\\W*)?(\\\\w\\\\d\\\\d)?");
		configs.add(configBRE);
				
		//Dortmund Abflug
		AirportConfig configDTM = new AirportConfig();
		configDTM.setAirportName("Dortmund");
		configDTM.setIata("EDLW");
		configDTM.setIcao("DTM");
		configDTM.setAktiv(true);
		configDTM.setUrl("https://www.dortmund-airport.de/abflug");
		configDTM.setIdWholeFlightPlan("airport-schedule-table-0ddf0104");
		configDTM.setIdTableheaderFlightplan("header");
		configDTM.setIdInnerFlightTable("ul");
		configDTM.setFlightPlanType("Abflug");
		configDTM.setPattern("(?>\\weitere\\W\\wnformationen\\W\\wnzeigen\\W\\wiel\\W\\s)([A-Za-zÜÄÖöäü]+\\W?[A-Za-zÜÄÖöäü]*\\W?[A-Za-zÜÄÖöäü]*\\W?\\s)(?>\\wlugnummer\\W\\s)(\\w\\d\\s\\d+\\s)(?>\\weplanter\\W\\wbflug\\W?)(\\d\\d\\W\\d\\d\\s)(?>\\wrwarteter\\W\\wbflug\\W)(\\d\\d\\W\\d\\d\\s)?(?>\\s?\\wtatus\\W\\W)(?>\\wchalter\\W\\W)(\\d\\d\\W)(?>\\wontrolle\\W\\W)(\\w\\W)(?>\\wate\\W\\W)(\\d\\d\\W)(?>\\wtatus\\W)(\\s[A-Za-zÖÄÜ]+)?");
		configs.add(configDTM);
				
		//Erfurt-Weimar Abflug
		AirportConfig configERF = new AirportConfig();
		configERF.setAirportName("Erfurt-Weimar");
		configERF.setIata("EDDE");
		configERF.setIcao("ERF");
		configERF.setAktiv(true);
		configERF.setUrl("https://www.flughafen-erfurt-weimar.de/reisen-aviation/flugplan.html#winter-2020-21");
		configERF.setIdWholeFlightPlan("contenttable");
		configERF.setIdTableheaderFlightplan("thead");
		configERF.setIdInnerFlightTable("tbody");
		configERF.setFlightPlanType("abflug");
		configERF.setPattern("([A-Za-zÖÄÜöäü]+\\W?[A-Za-zÖÄÜöäü]*\\W?[A-Za-zÖÄÜöäü]*\\s)(\\w\\w\\w\\s)(Montag\\s|Dienstag\\s|Mittwoch\\s|Donnerstag\\s|Freitag\\s|Samstag\\s|Sonntag\\s|Täglich\\s)([A-Za-z]+\\W?[A-Za-z]*\\s)(\\d\\d\\.\\d\\d\\.\\d\\d\\d\\d\\s?\\d?\\d?\\.?\\d?\\d?\\.?\\d?\\d?\\d?\\d?\\s|[A-Za-zÖÄÜöäü]+\\Wbis\\W[A-Za-zÖÄÜöäü]+)");
		configs.add(configERF);
				
		//Hannover-Langenhagen Abflug
		AirportConfig configHAJ = new AirportConfig();
		configHAJ.setAirportName("Hannover-Langenhagen");
		configHAJ.setIata("EDDV");
		configHAJ.setIcao("HAJ");
		configHAJ.setAktiv(true);
		configHAJ.setUrl("https://www.hannover-airport.de/rund-ums-fliegen/abflug/");
		configHAJ.setIdWholeFlightPlan("completeFlugplanTable");
		configHAJ.setIdTableheaderFlightplan("thead");
		configHAJ.setIdInnerFlightTable("tbody");
		configHAJ.setFlightPlanType("abflug");
		configHAJ.setPattern("(\\w\\d\\W\\d+\\W)([A-Za-zöäüÖÄÜ]+\\W)(\\([A-Za-zöäüÖÄÜ]+\\)\\W)(\\d\\d\\:\\d\\d\\W)(\\d\\d\\:\\d\\d\\W)?(\\w\\d\\d)([A-Za-zöäüÖÄÜ]+\\W)?");
		configs.add(configHAJ);
				
		//Rostock-Laage Abflug
		AirportConfig configRLG= new AirportConfig();
		configRLG.setAirportName("Rostock-Laage");
		configRLG.setIata("ETNL");
		configRLG.setIcao("RLG");
		configRLG.setAktiv(true);
		configRLG.setUrl("https://www.rostock-airport.de/flugplan.html");
		configRLG.setIdWholeFlightPlan("flugplan");
		configRLG.setIdTableheaderFlightplan("flug_row row headline");
		configRLG.setIdInnerFlightTable("flug_content");
		configRLG.setFlightPlanType("abflug");
		configRLG.setPattern("([A-Za-zÜÄÖöäü]+\\W+)(\\d+\\:\\d+\\W)(\\d+\\:\\d+\\W(?>\\(\\W\\w+\\)\\W)?)((?>\\w\\w\\W)+)(\\w\\w\\w?\\d+\\W)(\\d+\\.\\d+\\.\\d+\\W\\-\\W\\d+\\.\\d+\\.\\d+\\W)");
		configs.add(configRLG);
				
		//Niederrhein(Weeze) Abflug
		AirportConfig configNRN = new AirportConfig();
		configNRN.setAirportName("Niederrhein(Weeze)");
		configNRN.setIata("EDLV");
		configNRN.setIcao("NRN");
		configNRN.setAktiv(true);
		configNRN.setUrl("https://airport-weeze.com/de/abflug_ankunft.html");
		configNRN.setIdWholeFlightPlan("tblDepartures");
		configNRN.setIdTableheaderFlightplan("tr");
		configNRN.setIdInnerFlightTable("tblDepartures");
		configNRN.setFlightPlanType("abflug");
		configNRN.setPattern("()(\\w\\w\\s\\d+\\W)([A-Za-z]+\\W)(\\d+\\:\\d+\\W)([A-Za-zöäü]+\\W)?([A-Za-zöäü]+)?");
		configs.add(configNRN);
				
		// Config in die DB hauen 
		repoFP.saveAll(configs);
    }

}
