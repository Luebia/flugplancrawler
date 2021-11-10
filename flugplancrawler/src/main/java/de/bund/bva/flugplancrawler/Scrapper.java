package de.bund.bva.flugplancrawler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * Diese Klasse soll HTML Seiten für den Scrapper aufbereiten. (Burak)
 */
/**
 * @author Bianca
 * Diese Klasse wurde von Burak erzeugt. Ich (Bianca) habe �nderungen daran vorgenommen.
 * Der gesamte Inhalt wurde ausgelagert und die Flugh�fen in der Datenbank gespeichert.
 * Der alte Code befindet sich auskommentiert in dieser Klasse.
 * Zur Speicherung der Daten in der Datenbank habe ich die Entit�t AirportConfig erstellt.
 * �ber den AirportConfigController kann mit "/seeding" im Browser eine Methode aus dem AirportConfigServive aufgerufen werden,
 * die f�r alle bisherigen Flugh�fen, die Burak gemacht hatte, die Daten zur Suche der Abfl�ge in der Datenbank speichert. Dies sollte nur 1x bei der 1. Benutzung
 * des Programms und der Datenbank gemacht werden.
 */
@SpringBootApplication
@EnableScheduling
public class Scrapper {
	
	// Bianca: Diese Methode startet das Programm
    public static void main(String[] args) {
    	SpringApplication.run(Scrapper.class, args);
    }
    

}


//Alter Code:
//
//public void scrappAndStoreDepaturesManuell() {
//	ArrayList<Departure> departures = new ArrayList<>();
//
//    System.out.println("\nFlughafen Köln Bonn");
//    FlightPlan flightPlanKoelnBonnDeparture = new FlightPlan();
//    flightPlanKoelnBonnDeparture.setPatternFlightPlan("(?>Flugziel Check-In Gate Flug\u00AD?nummer Status "
//    		+ "Ab\u00AD?flug\u00AD?zeit)?([a-zA-Zöäüöäü]*\\W?[a-zA-Zöäüöäü]*\\W?[a-zA-Zöäüöäü]"
//    		+ "*\\W?\\(\\w\\w\\w\\)\\s)(\\w\\d\\/\\w\\d\\d\\-\\w\\d\\d\\s)?(\\w\\d\\d\\s)?(\\w\\w\\s\\d+\\s)(?>Terminal"
//    		+ "\\s\\d\\s)?(\\d?\\.?\\s?[a-zA-ZÖÄÜöäü]+\\s?[a-zA-ZÖÄÜöäü]*\\W?[a-zA-ZÖÄÜöäü]*\\s?\\/?\\s?"
//    		+ "[a-zA-ZÖÄÜöäü]*\\s?\\/?\\s?[a-zA-ZÖÄÜöäü]*)?(\\d\\d\\:\\d\\d\\s?\\d?\\d?\\W?\\d?\\d?)");
//    flightPlanKoelnBonnDeparture.setUrl("https://www.koeln-bonn-airport.de/fluege/abflug-ankunft.html");
//    flightPlanKoelnBonnDeparture.flightPlanHandler(
//            "flights",
//            "panel flight-header",
//            "panel flight",
//            "Abflug"
//    );
//    if (flightPlanKoelnBonnDeparture.getDepartures()!=null){
//        departures.addAll(flightPlanKoelnBonnDeparture.getDepartures());
//    }
//    System.out.println("\n --------------- \n");
//
//
//
//
//    System.out.println("\nFlughafen Bremen");
//    FlightPlan flightPlanBREarrival = new FlightPlan();
//    flightPlanBREarrival.setPatternFlightPlan("(\\w\\w\\w?\\W?\\d+\\w?\\W)(\\w\\w\\w?\\W)([A-Za-zöäü]+\\W)()(\\d\\d\\:\\d\\d\\W)(\\d\\d\\:\\d\\d\\W)?([A-Za-z]+\\W)?(Terminal\\W\\d)()");
//    flightPlanBREarrival.setUrl("https://www.bremen-airport.com/fluginfo/fluege/tagesflugplan/");
//    flightPlanBREarrival.flightPlanHandler(
//            "arrivalTab",
//            "c-flights-info_head",
//            "c-flights-info-detail",
//            "Ankunft"
//    );
//    System.out.println("\n --------------- \n");
//
//
//    FlightPlan flightPlanBREdeparture = new FlightPlan();
//    flightPlanBREdeparture.setPatternFlightPlan("(\\w\\w\\w?\\s?\\d+\\w?\\s)(?>Airline\\W)?(\\w\\w\\w?\\s)([a-zA-ZöäüÖÄÜ]+\\s)()(?>Abflug\\W*)?(\\d\\d\\:\\d\\d\\s)(\\d\\d\\:\\d\\d\\s)?([a-zA-ZÖÄÜ]+\\s)?(Terminal\\W\\d\\s|BELUGA\\WXL\\s)(?>Terminal\\W*X\\s)?(?>Counter\\W*)?(\\d+\\W\\-\\W\\d+\\s|\\d\\W\\-\\W\\d)?(?>Gate\\W*)?(\\w\\d\\d)?");
//    flightPlanBREdeparture.setUrl("https://www.bremen-airport.com/fluginfo/fluege/tagesflugplan/");
//    flightPlanBREdeparture.flightPlanHandler(
//            "departureTab",
//            "c-flights-info_head",
//            "c-flights-info-detail",
//            "Abflug"
//    );
//    if (flightPlanBREdeparture.getDepartures()!=null){
//        departures.addAll(flightPlanBREdeparture.getDepartures());
//    }
//    System.out.println("\n --------------- \n");
//
//    System.out.println("Flughafen Dortmund");
//    FlightPlan flightPlanDortmundArrival = new FlightPlan();
//    flightPlanDortmundArrival.setUrl("https://www.dortmund-airport.de/ankunft");
//    flightPlanDortmundArrival.setPatternFlightPlan("(?>[A-Za-zöäüÖÄÜ ]*Start:\\s)([A-Za-zÜÄÖöäü]+\\W?[A-Za-zÜÄÖöäü]*\\W?(?:\\W[A-Za-z]*\\W)?\\s)(?>Flugnummer: )(\\w\\d\\s\\d+\\s)(?>Geplante\\sAnkunft\\s?)(\\d\\d\\:\\d\\d\\s)(?>Erwartete Ankunft)\\W*(\\d\\d\\:\\d\\d\\s)?(?>Status: )([A-Za-z]*\\s)?");
//    flightPlanDortmundArrival.flightPlanHandler(
//            "airport-schedule-table-4054a8e6",
//            "header",
//            "ul",
//            "Ankunft"
//    );
//    System.out.println("\n --------------- \n");
//
//    FlightPlan flightPlanDortmundDepartures = new FlightPlan();
//    flightPlanDortmundDepartures.setUrl("https://www.dortmund-airport.de/abflug");
//    flightPlanDortmundDepartures.setPatternFlightPlan("(?>\\weitere\\W\\wnformationen\\W\\wnzeigen\\W\\wiel\\W\\s)([A-Za-zÜÄÖöäü]+\\W?[A-Za-zÜÄÖöäü]*\\W?[A-Za-zÜÄÖöäü]*\\W?\\s)(?>\\wlugnummer\\W\\s)(\\w\\d\\s\\d+\\s)(?>\\weplanter\\W\\wbflug\\W?)(\\d\\d\\W\\d\\d\\s)(?>\\wrwarteter\\W\\wbflug\\W)(\\d\\d\\W\\d\\d\\s)?(?>\\s?\\wtatus\\W\\W)(?>\\wchalter\\W\\W)(\\d\\d\\W)(?>\\wontrolle\\W\\W)(\\w\\W)(?>\\wate\\W\\W)(\\d\\d\\W)(?>\\wtatus\\W)(\\s[A-Za-zÖÄÜ]+)?");
//    flightPlanDortmundDepartures.flightPlanHandler(
//            "airport-schedule-table-0ddf0104",
//            "header",
//            "ul",
//            "Abflug"
//    );
//    if (flightPlanDortmundDepartures.getDepartures()!=null){
//        departures.addAll(flightPlanDortmundDepartures.getDepartures());
//    }
//    System.out.println("\n --------------- \n");
//
//
//
//    System.out.println("Flughafen Erfurt-Weimar");
//    FlightPlan flightPlanErfurtWeimarDepartures = new FlightPlan();
//    flightPlanErfurtWeimarDepartures.setUrl("https://www.flughafen-erfurt-weimar.de/reisen-aviation/flugplan.html#winter-2020-21");
//    flightPlanErfurtWeimarDepartures.setPatternFlightPlan("([A-Za-zÖÄÜöäü]+\\W?[A-Za-zÖÄÜöäü]*\\W?[A-Za-zÖÄÜöäü]*\\s)(\\w\\w\\w\\s)(Montag\\s|Dienstag\\s|Mittwoch\\s|Donnerstag\\s|Freitag\\s|Samstag\\s|Sonntag\\s|Täglich\\s)([A-Za-z]+\\W?[A-Za-z]*\\s)(\\d\\d\\.\\d\\d\\.\\d\\d\\d\\d\\s?\\d?\\d?\\.?\\d?\\d?\\.?\\d?\\d?\\d?\\d?\\s|[A-Za-zÖÄÜöäü]+\\Wbis\\W[A-Za-zÖÄÜöäü]+)");
//    flightPlanErfurtWeimarDepartures.flightPlanHandler(
//            "contenttable",
//            "thead",
//            "tbody",
//            "abflug"
//    );
//    if (flightPlanErfurtWeimarDepartures.getDepartures()!=null){
//        departures.addAll(flightPlanErfurtWeimarDepartures.getDepartures());
//    }
//    System.out.println("\n --------------- \n");
//
//    System.out.println("\nFlughafen Hannover");
//    FlightPlan flightPlanHannoverArrival = new FlightPlan();
//    flightPlanHannoverArrival.setUrl("https://www.hannover-airport.de/rund-ums-fliegen/ankunft/");
//    flightPlanHannoverArrival.setPatternFlightPlan("(\\w\\d\\W\\d+\\W)([A-Za-zöäüÖÄÜ]+\\W)(\\([A-Za-zöäüÖÄÜ]+\\)\\W)(\\d\\d\\:\\d\\d\\W)(\\d\\d\\:\\d\\d\\W)?(\\w\\d\\d)([A-Za-zöäüÖÄÜ]+\\W)?");
//    flightPlanHannoverArrival.flightPlanHandler(
//            "completeFlugplanTable",
//            "thead",
//            "tbody",
//            "ankunft"
//    );
//    System.out.println("\n --------------- \n");
//
//    FlightPlan flightPlanHannoverDeparture = new FlightPlan();
//    flightPlanHannoverDeparture.setUrl("https://www.hannover-airport.de/rund-ums-fliegen/abflug/");
//    flightPlanHannoverDeparture.setPatternFlightPlan("(\\w\\d\\W\\d+\\W)([A-Za-zöäüÖÄÜ]+\\W)(\\([A-Za-zöäüÖÄÜ]+\\)\\W)(\\d\\d\\:\\d\\d\\W)(\\d\\d\\:\\d\\d\\W)?(\\w\\d\\d)([A-Za-zöäüÖÄÜ]+\\W)?");
//    flightPlanHannoverDeparture.flightPlanHandler(
//            "completeFlugplanTable",
//            "thead",
//            "tbody",
//            "abflug"
//    );
//    if (flightPlanHannoverDeparture.getDepartures()!=null){
//        departures.addAll(flightPlanHannoverDeparture.getDepartures());
//    }
//    System.out.println("\n --------------- \n");
//
//    System.out.println("\nFlugplan Saarbrücken");
//    FlightPlan flightPlanSaarbuckenArrival = new FlightPlan();
//    flightPlanSaarbuckenArrival.setUrl("https://www.flughafen-saarbruecken.de/passagiere-besucher/fluginformation/aktuelle-tagesflugplaene/?no_cache=1");
//    flightPlanSaarbuckenArrival.setPatternFlightPlan("(\\w+\\W\\d+\\W)([A-Za-zÜÄÖüäö]+\\W)(\\d+\\:\\d+\\W\\whr)()");
//    flightPlanSaarbuckenArrival.flightPlanHandler(
//            "tableankunft",
//            "flugplan_labels col-xs-12 noP",
//            "flight_odd col-xs-12 noP",
//            "ankunft"
//    );
//    System.out.println("\n --------------- \n");
//
//    System.out.println("\n Flughafen Rostock");
//    FlightPlan flightPlanRostockDeparture = new FlightPlan();
//    flightPlanRostockDeparture.setUrl("https://www.rostock-airport.de/flugplan.html");
//    flightPlanRostockDeparture.setPatternFlightPlan("([A-Za-zÜÄÖöäü]+\\W+)(\\d+\\:\\d+\\W)(\\d+\\:\\d+\\W(?>\\(\\W\\w+\\)\\W)?)((?>\\w\\w\\W)+)(\\w\\w\\w?\\d+\\W)(\\d+\\.\\d+\\.\\d+\\W\\-\\W\\d+\\.\\d+\\.\\d+\\W)");
//    flightPlanRostockDeparture.flightPlanHandler(
//            "flugplan",
//            "flug_row row headline",
//            "flug_content",
//            "abflug"
//    );
//    if (flightPlanRostockDeparture.getDepartures()!=null){
//        departures.addAll(flightPlanRostockDeparture.getDepartures());
//    }
//    System.out.println("\n --------------- \n");
//
//    System.out.println("\n Flughafen Weezwe");
//    FlightPlan flightPlanWeezeDeparture = new FlightPlan();
//    flightPlanWeezeDeparture.setUrl("https://airport-weeze.com/de/abflug_ankunft.html");
//    flightPlanWeezeDeparture.setPatternFlightPlan("()(\\w\\w\\s\\d+\\W)([A-Za-z]+\\W)(\\d+\\:\\d+\\W)([A-Za-zöäü]+\\W)?([A-Za-zöäü]+)?");
//    flightPlanWeezeDeparture.flightPlanHandler(
//            "tblDepartures",
//            "tr",
//            "tblDepartures",
//            "abflug"
//    );
//    if (flightPlanWeezeDeparture.getDepartures()!=null){
//        departures.addAll(flightPlanWeezeDeparture.getDepartures());
//    }
//    System.out.println("\n --------------- \n");
//
//    System.out.println(departures.toString());
//    
//    for(Departure d : departures) {
//    	repoDepatures.save(d);
//    }
//	
//}