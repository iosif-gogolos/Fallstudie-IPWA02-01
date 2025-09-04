package beans;

import entities.*;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.Date;
import java.util.List;

@Named
@RequestScoped
public class GeisternetzService {

    @Inject
    private EntityManager em;

    // 1) MUST: Meldende Person erfasst Geisternetz (anonym möglich)
    public Geisternetz meldeGeisternetz(GpsKoordinate standort, double groesse,
                                        Long berichtendePersonId, String beschreibung) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            BerichtendePerson reporter = em.find(BerichtendePerson.class, berichtendePersonId);
            if (reporter == null) throw new IllegalArgumentException("Berichtende Person nicht gefunden");

            Geisternetz g = new Geisternetz();
            g.setStandort(standort);
            g.setGeschaetzeGroesse(groesse);
            g.setStatus(GeisternetzStatus.BERICHTET);
            g.setBerichtendePerson(reporter);
            g.setBeschreibung(beschreibung);
            g.setLetzteStatusVeränderung(new Date());

            em.persist(g);
            tx.commit();
            return g;
        } catch (RuntimeException ex) {
            if (tx.isActive()) tx.rollback();
            throw ex;
        }
    }

    // 2) MUST: Bergende Person trägt sich zur Bergung ein (max 1 Bergende pro Netz)
    public void eintragenZurBergung(Long geisternetzId, Long bergendePersonId) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Geisternetz g = em.find(Geisternetz.class, geisternetzId);
            BergendePerson b = em.find(BergendePerson.class, bergendePersonId);
            if (g == null || b == null) throw new IllegalArgumentException("Netz oder Person nicht gefunden");

            if (g.getBergendePerson() != null && !g.getBergendePerson().getId().equals(bergendePersonId)) {
                throw new IllegalStateException("Dieses Geisternetz ist bereits einer anderen bergenden Person zugeordnet");
            }

            g.setBergendePerson(b);
            g.setStatus(GeisternetzStatus.BERGUNG_BEVORSTEHEND);
            g.setLetzteStatusVeränderung(new Date());
            em.merge(g);
            tx.commit();
        } catch (RuntimeException ex) {
            if (tx.isActive()) tx.rollback();
            throw ex;
        }
    }

    // 3) MUST: Bergende Person sieht Netze, die noch zu bergen sind
    public List<Geisternetz> findeNochZuBergen() {
        // alle, die NICHT GEBORGEN und NICHT VERSCHOLLEN sind
        return em.createQuery(
                "select g from Geisternetz g " +
                "where g.status <> :geb and g.status <> :ver",
                Geisternetz.class)
            .setParameter("geb", GeisternetzStatus.GEBORGEN)
            .setParameter("ver", GeisternetzStatus.VERSCHOLLEN)
            .getResultList();
    }

    // 4) MUST: Bergende Person meldet als geborgen
    public void alsGeborgenMelden(Long geisternetzId, Long bergendePersonId) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Geisternetz g = em.find(Geisternetz.class, geisternetzId);
            BergendePerson b = em.find(BergendePerson.class, bergendePersonId);
            if (g == null || b == null) throw new IllegalArgumentException("Netz oder Person nicht gefunden");

            // Wenn noch keine Bergende gesetzt war, setze sie jetzt auf den Meldenden
            if (g.getBergendePerson() == null) {
                g.setBergendePerson(b);
            } else if (!g.getBergendePerson().getId().equals(b.getId())) {
                throw new IllegalStateException("Nur die zugeordnete bergende Person kann als geborgen melden");
            }

            g.setStatus(GeisternetzStatus.GEBORGEN);
            g.setLetzteStatusVeränderung(new Date());
            em.merge(g);
            tx.commit();
        } catch (RuntimeException ex) {
            if (tx.isActive()) tx.rollback();
            throw ex;
        }
    }

    // 7) COULD: Beliebige Person meldet als verschollen – NICHT anonym erlaubt
    public void alsVerschollenMelden(Long geisternetzId, Long meldendePersonId) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Geisternetz g = em.find(Geisternetz.class, geisternetzId);
            Person melder = em.find(Person.class, meldendePersonId);
            if (g == null || melder == null) throw new IllegalArgumentException("Netz oder Person nicht gefunden");

            if (melder instanceof BerichtendePerson bp && bp.isAnonym()) {
                throw new IllegalStateException("Anonyme Meldungen dürfen NICHT als verschollen markieren");
            }

            g.setStatus(GeisternetzStatus.VERSCHOLLEN);
            g.setLetzteStatusVeränderung(new Date());
            em.merge(g);
            tx.commit();
        } catch (RuntimeException ex) {
            if (tx.isActive()) tx.rollback();
            throw ex;
        }
    }

    // Weitere Helfer
    public Geisternetz findById(Long id) { return em.find(Geisternetz.class, id); }
    public List<Geisternetz> findAll() {
        return em.createQuery("select g from Geisternetz g", Geisternetz.class).getResultList();
    }
    
}
