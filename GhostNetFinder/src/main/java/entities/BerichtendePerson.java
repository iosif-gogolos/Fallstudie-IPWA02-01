package entities;

import jakarta.persistence.Entity;

/**
 * Meldende Person kann anonym sein. In dem Fall darf die Telefonnummer fehlen.
 * (Hinweis: Name bleibt trotzdem Pflicht laut Aufgabenstellung.)
 */
@Entity
public class BerichtendePerson extends Person {

    private boolean anonym;

    public BerichtendePerson() {}

    public boolean isAnonym() { return anonym; }
    public void setAnonym(boolean anonym) { this.anonym = anonym; }
}
