package entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Geisternetz {
	
	// Attribute
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Embedded
	private GpsKoordinate standort;
	private double geschaetzeGroesse;

	private GeisternetzStatus status;
	@Temporal(TemporalType.TIMESTAMP)
	private Date berichtsDatum;
	private Date letzteStatusVeränderung;
	
	// A ghost net may have an assigned recovering person (max one)
    @ManyToOne
    @JoinColumn(name = "bergende_person_id")
    private BergendePerson bergendePerson;

    public Geisternetz() {
        this.berichtsDatum = new Date();
    }

    public Geisternetz(GpsKoordinate standort, double geschaetzeGroesse, GeisternetzStatus status) {
        this.standort = standort;
        this.geschaetzeGroesse = geschaetzeGroesse;
        this.status = status;
        this.berichtsDatum = new Date();
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public GpsKoordinate getStandort() {
        return standort;
    }
   
    public void setStandort(GpsKoordinate standort) {
        this.standort = standort;
    }

    public double getGeschaetzteGroesse() {
        return geschaetzeGroesse;
    }

    public void setGeschaetzeGroesse(double geschaetzeGroesse) {
        this.geschaetzeGroesse = geschaetzeGroesse;
    }

    public GeisternetzStatus getStatus() {
        return status;
    }

    public void setStatus(GeisternetzStatus status) {
        this.status = status;
    }

    public Date getBerichtsdatum() {
        return berichtsDatum;
    }

    public BergendePerson getBergendePerson() {
        return bergendePerson;
    }

    public void setBergendePerson(BergendePerson bergendePerson) {
        this.bergendePerson = bergendePerson;
    }
	
}