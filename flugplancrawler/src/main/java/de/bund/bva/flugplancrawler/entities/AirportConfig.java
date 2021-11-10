package de.bund.bva.flugplancrawler.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author Bianca
 * Entität für die zu dursuchenden Flughäfen
 */
@Entity
@Table(name = "airportconfig")
public class AirportConfig {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String airportName;
	private String icao;
	private String iata;
	@Column(columnDefinition = "TEXT") 
	private String pattern;
	private String url;
	private String idWholeFlightPlan;
	private String idTableheaderFlightplan;
	private String idInnerFlightTable;
	private String flightPlanType;
	private boolean aktiv;
	
	public AirportConfig() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAirportName() {
		return airportName;
	}

	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}

	public String getIcao() {
		return icao;
	}

	public void setIcao(String icao) {
		this.icao = icao;
	}

	public String getIata() {
		return iata;
	}

	public void setIata(String iata) {
		this.iata = iata;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIdWholeFlightPlan() {
		return idWholeFlightPlan;
	}

	public void setIdWholeFlightPlan(String idWholeFlightPlan) {
		this.idWholeFlightPlan = idWholeFlightPlan;
	}

	public String getIdTableheaderFlightplan() {
		return idTableheaderFlightplan;
	}

	public void setIdTableheaderFlightplan(String idTableheaderFlightplan) {
		this.idTableheaderFlightplan = idTableheaderFlightplan;
	}

	public String getIdInnerFlightTable() {
		return idInnerFlightTable;
	}

	public void setIdInnerFlightTable(String idInnerFlightTable) {
		this.idInnerFlightTable = idInnerFlightTable;
	}

	public String getFlightPlanType() {
		return flightPlanType;
	}

	public void setFlightPlanType(String flightPlanType) {
		this.flightPlanType = flightPlanType;
	}

	public boolean isAktiv() {
		return aktiv;
	}

	public void setAktiv(boolean aktiv) {
		this.aktiv = aktiv;
	}
	
}
