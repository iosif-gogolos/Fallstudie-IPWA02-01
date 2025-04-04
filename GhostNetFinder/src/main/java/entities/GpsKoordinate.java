package entities;

import jakarta.persistence.Embeddable;

@Embeddable
public class GpsKoordinate {
	// Attribute
	private double breitengrad;
	private double laengengrad;
	
	// Default-Kostruktor -> notwedig für JPA
	public GpsKoordinate() {
	}

	public GpsKoordinate(double breitengrad, double laengengrad) {
		this.laengengrad = breitengrad;
		this.laengengrad = laengengrad;
	}
	
	// Getter- und Setter-Methoden

	public double getBreitengrad() {
		return breitengrad;
	}

	public void setBreitengrad(double breitengrad) {
		this.breitengrad = breitengrad;
	}

	public double getLaengengrad() {
		return laengengrad;
	}

	public void setLaengengrad(double laengengrad) {
		this.laengengrad = laengengrad;
	}
}