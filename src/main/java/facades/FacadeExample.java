package facades;

import entities.Members;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class FacadeExample {

    private static FacadeExample instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private FacadeExample() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static FacadeExample getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FacadeExample();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void setUp() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Members.deleteAllRows").executeUpdate();
            em.persist(new Members("Gustav Wernegreen", "cph-gw30@cphbusiness.dk", "cph-gw30"));
            em.persist(new Members("Mathias Noe Clausen", "cph-mc366@cphbusiness.dk", "cph-mc366"));
            em.persist(new Members("David Josefsen", "cph-dj154@cphbusiness.dk", "cph-dj154"));

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    //TODO Remove/Change this before use
    public long getRenameMeCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long renameMeCount = (long) em.createQuery("SELECT COUNT(r) FROM Members r").getSingleResult();
            return renameMeCount;
        } finally {
            em.close();
        }

    }

    public static void main(String[] args) {

        setUp();
    }

}
