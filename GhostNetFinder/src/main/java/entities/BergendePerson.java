package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class BergendePerson extends Person {

    @OneToMany(mappedBy = "bergendePerson")
    private List<Geisternetz> zugeordneteNetze = new ArrayList<>();

    public BergendePerson() {
        // no-args
    }

    public List<Geisternetz> getZugeordneteNetze() {
        return zugeordneteNetze;
    }
    public void setZugeordneteNetze(List<Geisternetz> zugeordneteNetze) {
        this.zugeordneteNetze = zugeordneteNetze;
    }
}
