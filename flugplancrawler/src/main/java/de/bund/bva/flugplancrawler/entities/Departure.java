package de.bund.bva.flugplancrawler.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**Diese Klasse stammt von Burak. Ich habe nur die Annotations hinzugefügt. Diese werden vom 
 * Spring Framework zur Erstellung der DB benötigt.
 * 
 * 
 */


@Entity
@Table(name = "departure")
public class Departure {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Transient
    public static final String KEINE_ANGABE = "-";
    private String checkIn;
    private String gate;
    private String terminal;
    private String status;
    private String expectedTime;
    private String departureTime;
    private String via;
    private String flightDestination;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="airline_id")
    private Airline airline;
    private String flightNo;
    private String codeshare;
    private String details;
    private String arrivalAtDestination;

    public Departure() {
        this.checkIn = KEINE_ANGABE;
        this.gate = KEINE_ANGABE;
        this.terminal = KEINE_ANGABE;
        this.status = KEINE_ANGABE;
        this.expectedTime = KEINE_ANGABE;
        this.departureTime = KEINE_ANGABE;
        this.via = KEINE_ANGABE;
        this.flightDestination = KEINE_ANGABE;
        this.flightNo = KEINE_ANGABE;
        this.codeshare = KEINE_ANGABE;
        this.arrivalAtDestination = KEINE_ANGABE;
    }

    public String getArrivalAtDestination() {
        return arrivalAtDestination;
    }

    public void setArrivalAtDestination(String arrivalAtDestination) {
        if (arrivalAtDestination == null || arrivalAtDestination.isEmpty()) {
            this.arrivalAtDestination = KEINE_ANGABE;
        } else {
            this.arrivalAtDestination = arrivalAtDestination;
        }
    }

    public String getCodeshare() {
        return codeshare;
    }

    public void setCodeshare(String codeshare) {
        if (codeshare == null || codeshare.isEmpty()) {
            this.codeshare = KEINE_ANGABE;
        } else {
            this.codeshare = codeshare;
        }
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        if (flightNo == null || flightNo.isEmpty()) {
            this.flightNo = KEINE_ANGABE;
        } else {
            this.flightNo = flightNo.trim();
        }
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        if (checkIn == null || checkIn.isEmpty()) {
            this.checkIn = KEINE_ANGABE;
        } else {
            this.checkIn = checkIn.trim();
        }
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        if (details == null || details.isEmpty()) {
            this.details = KEINE_ANGABE;
        } else {
            this.details = details.trim();
        }
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(String flightNo) {
        if (flightNo == null || flightNo.isEmpty()) {
            this.airline = new Airline();
        } else {
            this.airline = new Airline(flightNo);
        }
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        if (gate == null || gate.isEmpty()) {
            this.gate = KEINE_ANGABE;
        } else {
            this.gate = gate.trim();
        }
    }

    public String getFlightDestination() {
        return flightDestination;
    }

    public void setFlightDestination(String flightDestination) {
        if (flightDestination == null || flightDestination.isEmpty()) {
            this.flightDestination = null;
        } else {
            this.flightDestination = flightDestination.replaceAll("((\\wliegen)?\\s?(\\wn)?\\s?(\\walender)\\s?(eintragen))", "").trim();
        }
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        if (terminal == null || terminal.isEmpty()) {
            this.terminal = KEINE_ANGABE;
        } else {
            this.terminal = terminal.trim();
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status == null || status.isEmpty()) {
            this.status = KEINE_ANGABE;
        } else {
            this.status = status.trim();
        }
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        if (departureTime == null || departureTime.isEmpty()) {
            this.departureTime = KEINE_ANGABE;
        } else {
            this.departureTime = departureTime.trim();
        }
    }

    public String getExpectedTime() {
        return expectedTime;
    }

    public void setExpectedTime(String expectedTime) {
        if (expectedTime == null) {
            this.expectedTime = KEINE_ANGABE;
        } else {
            this.expectedTime = expectedTime.trim();
        }
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        if (via == null || via.isEmpty()) {
            this.via = KEINE_ANGABE;
        } else {
            this.via = via.trim();
        }
    }
    
    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
    public String toString() {
        return "\nDeparture{" +
                "flightDestination='" + flightDestination + '\'' +
                ", gate='" + gate + '\'' +
                ", terminal='" + terminal + '\'' +
                ", status='" + status + '\'' +
                ", expectedTime='" + expectedTime + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", via='" + via + '\'' +
                ", checkIn='" + checkIn + '\'' +
                ", airline=" + airline +
                ", flightNo='" + flightNo + '\'' +
                ", codeshare='" + codeshare + '\'' +
                ", details='" + details + '\'' +
                ", arrivalAtDestination='" + arrivalAtDestination + '\'' +
                '}';
    }
}
