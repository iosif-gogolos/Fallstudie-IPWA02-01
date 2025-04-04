package entities;

import jakarta.persistence.Entity;

@Entity
public class BerichtendePerson extends Person {
	
	private boolean anonym;
	
	public BerichtendePerson() {}
	
	public BerichtendePerson(String name, String telefonNummer, boolean anonym) {
		super(name, telefonNummer);
		this.anonym = anonym;
	}
	
	// Getter- und Setter-Methoden
	public boolean isAnonym() {
		return anonym;
	}
	
	public void setAnonym(boolean anonym) {
		this.anonym = anonym;
	}
}