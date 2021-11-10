package de.bund.bva.flugplancrawler.entities;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;



/**Diese Klasse stammt von Burak.
 * 
 * 
 * 
 * Klasse für die Flughäfen.
 */

public class Airport {
	
	
	private Integer id;
	
    private String name;
    private String icaoCode;
    private String iataCode;

    private Website website;
 
    private ArrayList<String> terminals;

    private ArrayList<String> hallen;

    private ArrayList<String> gates;

    private ArrayList<String> checkIns;
    
    
    

    public Airport() {
		
	}

	/**
     * Getter für den Namen des Flughafens.
     *
     * @return Gibt den Namen des Flughafens als String wieder.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter für den Namen des Flughafens.
     *
     * @param name Der Name des Flughafens als String.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter für den URL der Flughafen Webseite.
     * @return  Gibt die URL als String wieder.
     */
    public Website getWebsite() {
        return website;
    }

    /**
     * Setter für die URL der Flughafen Webseite.
     * @param website Der Übergabeparameter ist vom Objekttypen "Website".
     */
    public void setWebsite(Website website) {
        this.website = website;
    }

    /**
     * Getter für den ICAO Code des Flughafens.
     * @return Gibt den ICAO Code als String wieder.
     */
    public String getIcaoCode() {
        return icaoCode;
    }

    /**
     * Setter für den ICAO Code.
     * @param icaoCode Der Code wird als String als Übergabeparameter übergeben.
     */
    public void setIcaoCode(String icaoCode) {
        this.icaoCode = icaoCode;
    }

    /**
     * Getter für den IATA Code des Flughafens.
     * @return Gibt den IATA Code als String wieder.
     */
    public String getIataCode() {
        return iataCode;
    }

    /**
     * Setter für den IATA Code.
     * @param iataCode Der Code wird als String als Übergabeparameter übergeben.
     */
    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    /**
     * Getter für die Webseite des Flughafens.
     * @return Gibt die Webseite als Objekt wieder.
     */
    public Website getWebseite() {
        return website;
    }

    /**
     * Setter für die Webseite des Flughafens.
     * @param website Die Webseite wird als Objekt als übergabeparameter übergeben.
     */
    public void setWebseite(Website website) {
        this.website = website;
    }

    /**
     * Getter für die Terminals die es im Flughafen gibt.
     * @return Gibt die Terminals als Liste wieder.
     */
    public ArrayList<String> getTerminals() {
        return terminals;
    }

    /**
     * Setter für die Terminals im Flughafen.
     * @param terminals Die Terminals werden als ArrayList übergeben.
     */
    public void setTerminals(ArrayList<String> terminals) {
        this.terminals = terminals;
    }

    /**
     * Getter für die Hallen des Flughafens.
     * @return Gibt die Hallen des Flughafen als Liste wieder.
     */
    public ArrayList<String> getHallen() {
        return hallen;
    }

    /**
     * Setter für die Hallen des Flughafens.
     * @param hallen Die Hallen werden als Liste in die Methode übergeben.
     */
    public void setHallen(ArrayList<String> hallen) {
        this.hallen = hallen;
    }

    /**
     * Getter für die Gates im Flughafen.
     * @return Gibt die Gates des Flughafen als Liste zurück.
     */
    public ArrayList<String> getGates() {
        return gates;
    }

    /**
     * Setter für die Gates im Flughafen.
     * @param gates Die Gates werden als Liste in die Methode übergeben.
     */
    public void setGates(ArrayList<String> gates) {
        this.gates = gates;
    }

    /**
     * Getter für die Check-Ins des Flughafens.
     * @return Gibt die Check-Ins des Flughafens als Liste wieder.
     */
    public ArrayList<String> getCheckIns() {
        return checkIns;
    }

    /**
     * Setter für die Check-Ins des Flughafens.
     * @param checkIns ArrayList mit den Check-Ins.
     */
    public void setCheckIns(ArrayList<String> checkIns) {
        this.checkIns = checkIns;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
    
    
}
