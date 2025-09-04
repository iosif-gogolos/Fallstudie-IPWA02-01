package beans;

import entities.BerichtendePerson;
import entities.BergendePerson;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class UserController {

    @Inject
    private UserService userService;

    // Bindings für Registrierung
    private String name;
    private String telefon;
    private boolean anonym; // nur für Berichtende
    private String geisternetzData; // New property

    // Registrierung für meldende Person (anonym optional)
    public String registriereBerichtende() {
        BerichtendePerson p = userService.registriereBerichtende(name, telefon, anonym);
        return "login.xhtml?faces-redirect=true";
    }

    // Registrierung für bergende Person (Telefon Pflicht)
    public String registriereBergende() {
        BergendePerson p = userService.registriereBergende(name, telefon);
        return "login.xhtml?faces-redirect=true";
    }

    // Getter/Setter
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getTelefon() { return telefon; }
    public void setTelefon(String telefon) { this.telefon = telefon; }
    public boolean isAnonym() { return anonym; }
    public void setAnonym(boolean anonym) { this.anonym = anonym; }
    public String getGeisternetzData() { return geisternetzData; } // Getter for geisternetzData
    public void setGeisternetzData(String geisternetzData) { this.geisternetzData = geisternetzData; } // Setter for geisternetzData
}
