package entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Geisternetz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private GpsKoordinate standort;

    private double geschaetzeGroesse;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GeisternetzStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date berichtsDatum;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "letzte_statusveraenderung") // vermeidet Probleme mit Umlauten
    private Date letzteStatusVeränderung;

    @ManyToOne
    @JoinColumn(name = "berichtende_person_id", nullable = false)
    private BerichtendePerson berichtendePerson;

    @ManyToOne
    @JoinColumn(name = "bergende_person_id")
    private BergendePerson bergendePerson;

    // OPTIONAL: falls du im Formular eine Beschreibung erfasst
    @Column(length = 1000)
    private String beschreibung;

    public Geisternetz() {
        this.berichtsDatum = new Date();
    }

    public Geisternetz(GpsKoordinate standort, double geschaetzeGroesse, GeisternetzStatus status) {
        this.standort = standort;
        this.geschaetzeGroesse = geschaetzeGroesse;
        this.status = status;
        this.berichtsDatum = new Date();
    }

    // --- Getter/Setter ---
    public Long getId() { return id; }

    public GpsKoordinate getStandort() { return standort; }
    public void setStandort(GpsKoordinate standort) { this.standort = standort; }

    public double getGeschaetzteGroesse() { return geschaetzeGroesse; }
    public void setGeschaetzeGroesse(double geschaetzeGroesse) { this.geschaetzeGroesse = geschaetzeGroesse; }

    public GeisternetzStatus getStatus() { return status; }
    public void setStatus(GeisternetzStatus status) { this.status = status; }

    public Date getBerichtsdatum() { return berichtsDatum; }
    public void setBerichtsdatum(Date d) { this.berichtsDatum = d; }

    public Date getLetzteStatusVeränderung() { return letzteStatusVeränderung; }
    public void setLetzteStatusVeränderung(Date d) { this.letzteStatusVeränderung = d; }

    public BerichtendePerson getBerichtendePerson() { return berichtendePerson; }
    public void setBerichtendePerson(BerichtendePerson berichtendePerson) { this.berichtendePerson = berichtendePerson; }

    public BergendePerson getBergendePerson() { return bergendePerson; }
    public void setBergendePerson(BergendePerson bergendePerson) { this.bergendePerson = bergendePerson; }

    public String getBeschreibung() { return beschreibung; }
    public void setBeschreibung(String beschreibung) { this.beschreibung = beschreibung; }
}
