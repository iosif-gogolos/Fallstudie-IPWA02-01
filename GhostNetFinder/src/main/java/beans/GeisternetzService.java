package beans;

import entities.Geisternetz;
import entities.GeisternetzStatus;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

@Named
@RequestScoped
public class GeisternetzService {

    @PersistenceContext(unitName = "GeisternetzPU")
    private EntityManager em;

    @Transactional
    public void createGhostNet(Geisternetz netz) {
        em.persist(netz);
    }

    public List<Geisternetz> findeAlleGeisternetze() {
        return em.createQuery("SELECT g FROM Geisternetz g", Geisternetz.class).getResultList();
    }

    public List<Geisternetz> findeAlleGeisternetzNachStatus(GeisternetzStatus status) {
        return em.createQuery("SELECT g FROM Geisternetz g WHERE g.status = :status", Geisternetz.class)
                 .setParameter("status", status)
                 .getResultList();
    }

    @Transactional
    public void updateGeisternetz(Geisternetz netz) {
        em.merge(netz);
    }
}
