

import entities.GpsKoordinate;
import entities.Geisternetz;
import entities.GeisternetzStatus;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class GeisternetzController {
    private double breitengrad;
    private double laengengrad;

    // Getter und Setter für latitude und longitude
    public double getBreitengrad() {
        return breitengrad;
    }

    public void setLBreitengrad(double breitengrad) {
        this.breitengrad = breitengrad;
    }

    public double getLaengengrad() {
        return laengengrad;
    }

    public void setLaengengrad(double laengengrad) {
        this.laengengrad = laengengrad;
    }

    public String meldeGeisternetz() {
        GpsKoordinate standort = new GpsKoordinate(breitengrad, laengengrad);
        Geisternetz geisternetz = new Geisternetz(standort, 0.0, GeisternetzStatus.BERICHTET);
        // Speichern Sie das Geisternetz in der Datenbank (Logik hier hinzufügen)
        return "index"; // Zurück zur Startseite
    }
}
