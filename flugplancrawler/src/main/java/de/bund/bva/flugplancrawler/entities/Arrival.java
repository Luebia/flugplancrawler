package de.bund.bva.flugplancrawler.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Diese Klasse wurde von Burak gemacht. Ich habe nur die Annotations hinzugef�gt um die Entit�t auf die 
 * Speicherung in der DB vorzubereiten. Ebenfalls zur Vorbereitung habe ich ein Repository und einen Controller
 * f�r die Arrivals vorbereitet.
 * 
 * 
 * Klasse für die Ankünfte
 */

@Entity
@Table(name = "arrival")
public class Arrival {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
    private String flightNo;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "airline_id")
    private Airline airline;
    private String originFrom;
    private String via;
    private String arrivalTime;
    private String expectedTime;
    private String status;
    private String terminal;
    private String codeshare;
    private String details;


    /**
     * Standard Konstruktor für die Ankünfte.
     * Sobald der Konstruktor aufgerufen wird, werden die Variablen standarmäßig mit "Keine Angabe" initialisiert.
     */
    public Arrival() {
        this.flightNo = "Keine Angabe";
        this.originFrom = "Keine Angabe";
        this.via = "Keine Angabe";
        this.arrivalTime = "Keine Angabe";
        this.expectedTime = "Keine Angabe";
        this.status = "Keine Angabe";
        this.terminal = "Keine Angabe";
        this.codeshare = "Keine Angabe";

    }

    /**
     * Getter für den Codeshare
     *
     * @return Gibt den Codeshare als String wieder.
     */
    public String getCodeshare() {
        return codeshare;
    }

    /**
     * Setter für den Codeshare.
     * Falls der Parameter leer oder null ist wird "Keine Angabe" gesetzt.
     *
     * @param codeshare Codeshare aus dem Flugplan als String.
     */
    public void setCodeshare(String codeshare) {
        if (codeshare == null || codeshare.isEmpty()) {
            this.codeshare = "Keine Angabe";
        } else {
            this.codeshare = codeshare;
        }
    }

    /**
     * Getter für die Flugnummer.
     *
     * @return Gibt die Flugnummer als String wieder.
     */
    public String getFlightNo() {
        return flightNo;
    }

    /**
     * Setter für die Flugnummer.
     * Falls der Parameter leer oder null ist wird "Keine Angabe" gesetzt.
     *
     * @param flightNo Flugnummer aus dem Flugplan als String.
     */
    public void setFlightNo(String flightNo) {
        if (flightNo == null || flightNo.isEmpty()) {
            this.flightNo = "Keine Angabe";
        } else {
            this.flightNo = flightNo.trim();
        }
    }

    /**
     * Getter für die Fluggesellschaft.
     *
     * @return Gibt die Fluggesellschaft als Objekt wieder.
     */
    public Airline getAirline() {
        return airline;
    }

    /**
     * Setter für die Fluggesellschaft.
     * Falls der Parameter leer oder null ist wird die Fluggsellschaft erzeugt aber ohne Angaben.
     *
     * @param flightNo Flugnummer wird als String übergeben.
     */
    public void setAirline(String flightNo) {
        if (flightNo == null || flightNo.isEmpty()) {
            this.airline = new Airline();
        } else {
            this.airline = new Airline(flightNo);
        }
    }

    /**
     * Getter für die Details.
     *
     * @return Gibt die Details der Ankünfte als String wieder.
     */
    public String getDetails() {
        return details;
    }

    /**
     * Setter für die Details.
     * Falls der Parameter leer oder null ist wird "Keine Angabe" gesetzt.
     *
     * @param details Details werden als String übergeben.
     */
    public void setDetails(String details) {
        if (details == null || details.isEmpty()) {
            this.details = "Keine Angabe";
        } else {
            this.details = details.trim();
        }
    }

    /**
     * Getter für 'von' aus dem Flugplan.
     *
     * @return Gibt den Ort wieder aus den der Flug kommt.
     */
    public String getFrom() {
        return originFrom;
    }

    /**
     * Setter für den Quellflughafen aus dem der Flug kommt.
     * Falls der Parameter leer oder null ist wird "Keine Angabe" gesetzt.
     *
     * @param from String
     */
    public void setFrom(String from) {
        if (from == null || from.isEmpty()) {
            this.originFrom = "Keine Angabe";
        } else {
            this.originFrom = from.trim();
        }
    }

    /**
     * Getter für VIA
     *
     * @return Gibt VIA aus dem Flugplan wieder.
     */
    public String getVia() {
        return via;
    }

    /**
     * Setter für VIA
     * Falls der Parameter leer oder null ist wird "Keine Angabe" gesetzt.
     *
     * @param via String.
     */
    public void setVia(String via) {
        if (via == null || via.isEmpty()) {
            this.via = "Keine Angabe";
        } else {
            this.via = via.trim();
        }
    }

    /**
     * Getter für die Ankunftszeit
     *
     * @return Gibt die Ankunftszeit als String wieder.
     */
    public String getArrivalTime() {
        return arrivalTime;
    }

    /**
     * Setter für die Ankunftszeit
     * Falls der Parameter leer oder null ist wird "Keine Angabe" gesetzt.
     *
     * @param arrivalTime Die Ankunftszeit aus dem Flugplan wird als String übergeben.
     */
    public void setArrivalTime(String arrivalTime) {
        if (arrivalTime == null || arrivalTime.isEmpty()) {
            this.arrivalTime = "Keine Angabe";
        } else {
            this.arrivalTime = arrivalTime.trim();
        }
    }

    /**
     * Getter für die Erwartete Zeit
     *
     * @return Gibt die Erwartete Zeit als String wieder.
     */
    public String getExpectedTime() {
        return expectedTime;
    }

    /**
     * Setter für die Erwartete Zeit.
     *
     * @param expectedTime Die Erwartete Zeit aus dem Flugplan wird als parameter übergeben.
     */
    public void setExpectedTime(String expectedTime) {
        if (expectedTime == null || expectedTime.isEmpty()) {
            this.expectedTime = "Keine Angabe";
        } else {
            this.expectedTime = expectedTime.trim();
        }
    }

    /**
     * Getter für den Status des Flugs
     *
     * @return Gibt den Status des Flugs als String wieder.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Setter für den Status des Flugs.
     * Falls der Parameter leer oder null ist wird "Keine Angabe" gesetzt.
     *
     * @param status Status des Flugs wird als String übergeben
     */
    public void setStatus(String status) {
        if (status == null || status.isEmpty()) {
            this.status = "Keine Angabe";
        } else {
            this.status = status.trim();
        }
    }

    /**
     * Getter für den Terminal.
     *
     * @return Gibt den Terminal wieder.
     */
    public String getTerminal() {
        return terminal;
    }

    /**
     * Setter für den Terminal.
     * Falls der Parameter leer oder null ist wird "Keine Angabe" gesetzt.
     * @param terminal Der Terminal wird als String übergeben.
     */
    public void setTerminal(String terminal) {
        if (terminal == null || terminal.isEmpty()) {
            this.terminal = "Keine Angabe";
        } else {
            this.terminal = terminal.trim();
        }
    }
    
    

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	@Override
    public String toString() {
        return "Arrival{" +
                "flightNo='" + flightNo + '\'' +
                ", airline=" + airline +
                ", from='" + originFrom + '\'' +
                ", via='" + via + '\'' +
                ", arrivalTime='" + arrivalTime + '\'' +
                ", expectedTime='" + expectedTime + '\'' +
                ", status='" + status + '\'' +
                ", terminal='" + terminal + '\'' +
                ", codeshare='" + codeshare + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
