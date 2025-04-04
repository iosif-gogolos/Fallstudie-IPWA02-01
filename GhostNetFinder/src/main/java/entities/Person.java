package entities;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)

public abstract class Person {
	// Attribute
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	// Für registrierte Benutzer, für meldende = Null
	private String telefonNummer;

	
	// Default-Kostruktor -> notwedig für JPA
	public Person() {
	}
	
	public Person(String name, String telefonNummer) {
		this.name = name;
		this.telefonNummer = telefonNummer;
	
	}
	
	// Getter- und Setter-Methoden
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getTelefonNummer() {
		return telefonNummer;
	}
	
	public void setTelefonNummer(String telefonNummer) {
		this.telefonNummer = telefonNummer;
	}

}