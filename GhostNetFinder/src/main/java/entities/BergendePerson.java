package entities;

import jakarta.persistence.*;
import java.util.ArrayList;

@Entity
public class BergendePerson extends Person{
	
	/* Relation:
	 * Eine bergende Person kann mehrere Geisternetze bergen
	 * Ein Geisternetz ist höchtens einer Person zugeordnet
	 */
	@OneToMany(mappedBy = "bergendePerson", cascade = CascadeType.ALL /* orphanRemoval = true*/)
	private ArrayList<Geisternetz> zugeordneteNetze = new ArrayList<>();
	
	// Default-Kostruktor -> notwedig für JPA
	public BergendePerson() {
	}
	
	public BergendePerson(String name, String telefonNummer) {
		super(name, telefonNummer);
		this.zugeordneteNetze = new ArrayList<>();
	}
	
	// Getter- und Setter-Methoden
	public ArrayList<Geisternetz> getGeisternetze() {
		return zugeordneteNetze;
	}
	
	public void setGeisternetze(ArrayList<Geisternetz> zugeordneteNetze) {
		this.zugeordneteNetze = zugeordneteNetze;
	}
}