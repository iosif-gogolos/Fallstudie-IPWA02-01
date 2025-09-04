package beans;

import entities.*;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class GeisternetzController implements Serializable {

    @Inject
    private GeisternetzService geisternetzService;

    @Inject
    private UserService userService;

    private Double breitengrad;
    private Double laengengrad;
    private Double geschaetzteGroesse;
    private String beschreibung;

    private Long berichtendePersonId;
    private Long bergendePersonId;
    private Long beliebigePersonId;

    private Long selectedGeisternetzId;

    public String meldeGeisternetz() {
        GpsKoordinate k = new GpsKoordinate(breitengrad, laengengrad);
        geisternetzService.meldeGeisternetz(
                k,
                geschaetzteGroesse != null ? geschaetzteGroesse : 0.0,
                berichtendePersonId,
                beschreibung
        );
        return null; // Ajax: auf der gleichen View bleiben
    }

    public void eintragenZurBergung(Long geisternetzId) {
        geisternetzService.eintragenZurBergung(geisternetzId, bergendePersonId);
    }

    public List<Geisternetz> getNochZuBergen() {
        return geisternetzService.findeNochZuBergen();
    }

    public void alsGeborgenMelden(Long geisternetzId) {
        geisternetzService.alsGeborgenMelden(geisternetzId, bergendePersonId);
    }

    public void alsVerschollenMelden(Long geisternetzId) {
        geisternetzService.alsVerschollenMelden(geisternetzId, beliebigePersonId);
    }

    public List<Geisternetz> getAlle() { return geisternetzService.findAll(); }

    public Double getBreitengrad() { return breitengrad; }
    public void setBreitengrad(Double v) { this.breitengrad = v; }
    public Double getLaengengrad() { return laengengrad; }
    public void setLaengengrad(Double v) { this.laengengrad = v; }
    public Double getGeschaetzteGroesse() { return geschaetzteGroesse; }
    public void setGeschaetzteGroesse(Double v) { this.geschaetzteGroesse = v; }
    public String getBeschreibung() { return beschreibung; }
    public void setBeschreibung(String s) { this.beschreibung = s; }
    public Long getBerichtendePersonId() { return berichtendePersonId; }
    public void setBerichtendePersonId(Long id) { this.berichtendePersonId = id; }
    public Long getBergendePersonId() { return bergendePersonId; }
    public void setBergendePersonId(Long id) { this.bergendePersonId = id; }
    public Long getBeliebigePersonId() { return beliebigePersonId; }
    public void setBeliebigePersonId(Long id) { this.beliebigePersonId = id; }
    public Long getSelectedGeisternetzId() { return selectedGeisternetzId; }
    public void setSelectedGeisternetzId(Long id) { this.selectedGeisternetzId = id; }
}
