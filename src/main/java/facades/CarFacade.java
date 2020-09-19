package facades;

import dtos.CarsDTO;
import entities.Cars;
import entities.Members;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author David
 */
public class CarFacade {

    private static CarFacade instance;
    private static EntityManagerFactory emf;

    private CarFacade() {

    }

    public static CarFacade getCarFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CarFacade();
        }
        return instance;
    }
//        public static void main(String[] args) {
//        insertData();
//    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void insertData() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();

        Date date = new Date();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Cars.deleteAllRows").executeUpdate();
            em.persist(new Cars(1997, "Ford", "E350", 3000, "test", date));
            em.persist(new Cars(1999, "Chevy", "Venture", 4900, "test1", date));
            em.persist(new Cars(2000, "Chevy", "Venture", 5000, "test3", date));
            em.persist(new Cars(1996, "Jeep", "Grand Cherokee", 4799, "test4", date));
            em.persist(new Cars(2005, "Volvo", "v70", 44799, "test5", date));

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
        public long getCarCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long carCount = (long) em.createQuery("SELECT COUNT(c) FROM Cars c").getSingleResult();
            return carCount;
        } finally {
            em.close();
        }

    }

    public static List<CarsDTO> getAllCars() {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createNamedQuery("Cars.getAll");
            List<CarsDTO> cars = query.getResultList();
            return cars;
        } finally {
            em.close();
        }
    }


}