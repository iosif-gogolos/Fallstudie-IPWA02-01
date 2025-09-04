package beans;

import entities.*;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

@Named
@RequestScoped
public class UserService {

    @Inject
    EntityManager em;

    public BerichtendePerson registriereBerichtende(String name, String telefon, boolean anonym) {
        if (!anonym && (telefon == null || telefon.isBlank())) {
            throw new IllegalArgumentException("Telefonnummer ist Pflicht, wenn nicht anonym gemeldet wird.");
        }

        BerichtendePerson p = new BerichtendePerson();
        p.setAnonym(anonym);
        // Je nachdem wie deine Basisklasse Person aussieht:
        p.setEmail(name);     // oder p.setVorname/Nachname, wenn vorhanden
        if (!anonym) {
            p.setTelefon(telefon);
        }

        return persist(p);
    }

    public BergendePerson registriereBergende(String name, String telefon) {
        if (telefon == null || telefon.isBlank()) {
            throw new IllegalArgumentException("Bergende Person benötigt eine Telefonnummer.");
        }

        BergendePerson p = new BergendePerson();
        // auch hier: an die Felder in Person anpassen
        p.setEmail(name);
        p.setTelefon(telefon);

        return persist(p);
    }

    private <T extends Person> T persist(T p) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(p);
            tx.commit();
            return p;
        } catch (RuntimeException ex) {
            if (tx.isActive()) tx.rollback();
            throw ex;
        }
    }

    public Person findPerson(Long id) { return em.find(Person.class, id); }
    public List<Person> findAllPersons() {
        return em.createQuery("select p from Person p", Person.class).getResultList();
    }
}
