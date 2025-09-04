package config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@ApplicationScoped
public class JpaProducer {

    private EntityManagerFactory emf;

    @PostConstruct
    public void init() {
        try {
        	 System.out.println(">>> JpaProducer.init(): Starte EMF für PU 'GeisternetzPU'");
             emf = Persistence.createEntityManagerFactory("GeisternetzPU");
             System.out.println(">>> JpaProducer.init(): EMF erfolgreich erzeugt.");

             // Dummy-Init: sorgt dafür, dass H2 die DB-Datei sofort anlegt
             EntityManager em = emf.createEntityManager();
             try {
                 em.getTransaction().begin();
                 em.createNativeQuery("select 1").getSingleResult();
                 em.getTransaction().commit();
                 System.out.println(">>> JpaProducer.init(): H2 DB initialized, file should be created.");
             } finally {
                 em.close();
             }

         } catch (Exception e) {
             System.err.println(">>> JpaProducer.init(): FEHLER beim Erstellen der EntityManagerFactory!");
             e.printStackTrace();
             throw e;
         }
    }

    @PreDestroy
    public void destroy() {
        if (emf != null && emf.isOpen()) {
            emf.close();
            System.out.println(">>> JpaProducer.destroy(): EntityManagerFactory geschlossen.");
        }
    }

    @Produces
    @RequestScoped
    public EntityManager produceEntityManager() {
        return emf.createEntityManager();
    }

    public void close(@Disposes EntityManager em) {
        if (em != null && em.isOpen()) {
            em.close();
        }
    }
}
