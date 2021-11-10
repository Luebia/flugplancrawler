package de.bund.bva.flugplancrawler.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Bianca
 * Diese Entität ermöglicht es das aktuelle Suchintervall in der 
 * DB zu speichern. Die Annotations werden vom Spring Framework 
 * zur Erstellung der DB benötigt.
 * 
 */
@Entity
@Table(name = "schedulerConfig")
public class SchedulerConfig {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private int minutes;
	
	//Konstruktor
	public SchedulerConfig() {
		
	}

	// Getter und Setter
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
	
}
