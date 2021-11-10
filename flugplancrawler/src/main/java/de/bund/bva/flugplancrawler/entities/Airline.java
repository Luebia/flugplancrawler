package de.bund.bva.flugplancrawler.entities;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



/**
 * Diese Klasse stammt von Burak. Ich habe nur die Annotations hinzugef�gt. Diese werden vom 
 * Spring Framework zur Erstellung der DB ben�tigt.
 * 
 * 
 * 
 * Dies ist die Klasse für die Fluggesellschaften.
 */

@Entity
@Table(name = "airline")
public class Airline {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
    private String airlineName;
	
    private String airlineInitials;
	
    private String flightNo;
	
	private String flightType;
	
	@OneToMany(mappedBy = "airline",
			   cascade = {CascadeType.ALL})
	private List<Departure> listOfDepartures;
	
	@OneToMany(mappedBy = "airline",
			   cascade = {CascadeType.ALL})
	private List<Arrival> listOfArrivals; 
    
    
    /**
     * Sobald der Konstruktor aufgerufen wird, werden die Variablenm automatisch mit "Keine Angabe" deklariert.
     */
    public Airline() {
        this.airlineName = "Keine Angabe";
        this.airlineInitials = "Keine Angabe";
        this.flightNo = "Keine Angabe";
        this.flightType = "Keine Angabe";
    }

    /**
     * Wenn der Konstruktor mit der Flugnummer aufgerufen wird, wird darauß die Initialien der Fluggesellschaft ausgefiltert.
     * @param flightNo Die Flugnummer besteht meist aus 2 Buchstaben und darauf folgenden 1 - 4 Ziffern.
     */
    public Airline(String flightNo) {
        setFlightNo(flightNo);
        setAirlineInitials(flightNo);
        setAirlineName(getAirlineInitials());
        setFlightType("Keine Angabe");
    }

    /**
     * Getter für den Namen der Fluggesellschaft.
     * @return Gibt den Namen der Fluggesellschaft zurück.
     */
    public String getAirlineName() {
        return airlineName;
    }

    /**
     * Setter für den Namen der Fluggesellschaft.
     * Hier zu braucht es nur die ersten 2 Buchstaben der Flugnummer. In der Methode wird aus den Initalien der Name der Fluggsellschaft gesetzt.
     * @param airlineInitials Die Initialien der Fluggesellschaft. In der Regel besteht sie aus 2 Buchstaben.
     */
    public void setAirlineName(String airlineInitials) {
        String airlineName;
        switch (airlineInitials.toUpperCase()) {
            case "EW":
                airlineName = "Eurowings";
                break;
            case "X3":
                airlineName = "TUIFLY.COM";
                break;
            case "LH":
                airlineName = "Deutsche Lufthansa AG";
                break;
            case "W6":
                airlineName = "WIZZ AIR";
                break;
            case "TK":
                airlineName = "Turkish Airlines Inc.";
                break;
            case "FR":
                airlineName = "Ryanair";
                break;
            case "PC":
                airlineName = "Pegasus Hava Tasimaciligi AS";
                break;
            case "0B":
                airlineName = "BLUE AIR";
                break;
            case "3O":
                airlineName = "Air Arabia Maroc";
                break;
            case "XQ":
                airlineName = "Sun Express";
                break;
            case "IR":
                airlineName = "Iran Air The Airline of Islamic Rep. of Iran";
                break;
            case "NO":
                airlineName = "Neos Airlines";
                break;
            case "KL":
                airlineName = "Royal Dutch Airlines";
                break;
            case "BGA":
                airlineName = "BGA";
                break;
            case "XR":
                airlineName = "Corendon Airlines Europe";
                break;
            case "LGL":
                airlineName = "Luxair";
                break;
            case "XC":
                airlineName = "Corendon Airlines";
                break;
            default:
                System.out.println("--> Flug konnte keiner Fluggesellschaft zugeordnet werden.");
                System.out.println("--> Initialien: " + airlineInitials);
                airlineName = airlineInitials;
                break;
        }
        this.airlineName = airlineName.trim();
    }

    /**
     * Getter für die Initialien der Fluggesellschaft.
     * @return Gibt die Initialien als String zurück.
     */
    public String getAirlineInitials() {
        return airlineInitials;
    }

    /**
     * Setter für die Initialien.
     * Aus der Flugnummer werden die Initalien ausgefiltert.
     * Mit einem regulären Ausdruck werden die ersten 2 (bei sonderfällen 3) Buchstaben ausgefiltert.
     * @param flightNo Die Flugnummer besteht in der Regel aus 2 Buchstaben und darauf folgend mit 1+4 Ziffern.
     */
    public void setAirlineInitials(String flightNo) {
//        Pattern pattern = Pattern.compile("^(\\w\\w\\w?)");
        Pattern pattern = Pattern.compile("^([A-Z][A-Z][A-Z]?)");
        Matcher matcher = pattern.matcher(flightNo);
        if (matcher.find())
        {
            this.airlineInitials = matcher.group(1).trim();
        }
    }

    /**
     * Getter für die Flugnummer.
     * @return Gibt die Flugnummer als String zurück.
     */
    public String getFlightNo() {
        return flightNo;
    }

    /**
     * Setter für die Flugnummer.
     * Meistens steht die Flugnummer bereits in der Flugplantabelle. Sonst muss mann eine Methode Schreiben die diese ausfiltert.
     * @param flightNo Die Flugnummer besteht in der Regel aus 2 Buchstaben und darauf folgend 1-4 Ziffern.
     */
    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo.trim();
    }

    /**
     * Getter für den Flugtypen.
     * @return  Gibt den Flugtypen als String zurück.
     */
    public String getFlightType() {
        return flightType;
    }

    /**
     * Setter für den Flugtypen.
     * Falls der Flugplan eine Spalte mit dem Flugtypen hat kann man diese mit dieser Methode setzen.
     * @param flightType Der Flugtyp ist ein String.
     */
    public void setFlightType(String flightType) {
        this.flightType = flightType.trim();
    }
    
    // Getter und Setter f�r die ID ( F�r die Datenbank als primary key )
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
    public String toString() {
        return airlineName+";";
    }
}
