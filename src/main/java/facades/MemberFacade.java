package facades;

import dtos.MembersDTO;
import entities.Members;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class MemberFacade {

    private static MemberFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private MemberFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MemberFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MemberFacade();
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
    public long getMemberCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long memberCount = (long) em.createQuery("SELECT COUNT(m) FROM Members m").getSingleResult();
            return memberCount;
        } finally {
            em.close();
        }

    }
    
    public List<MembersDTO> getAllNames(){
        EntityManager em = emf.createEntityManager();
         try {
              Query query = em.createNamedQuery("Members.getAll");
              List<MembersDTO> members = query.getResultList();
              return members;
        }         
        finally {
            em.close();
        }
    }

//    public static void main(String[] args) {
//        setUp();
//    }

}
