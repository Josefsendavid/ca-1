package facades;

import utils.EMF_Creator;
import entities.Members;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class MemberFacadeTest {
    private static Members m1,m2,m3;
    private static EntityManagerFactory emf;
    private static MemberFacade facade;

    public MemberFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = MemberFacade.getFacadeExample(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
    
        EntityManager em = emf.createEntityManager();
        m1 = new Members("Gustav Wernegreen", "cph-gw30@cphbusiness.dk", "cph-gw30");
        m2 = new Members("Mathias Noe Clausen", "cph-mc366@cphbusiness.dk", "cph-mc366");
        m3 = new Members("David Josefsen", "cph-dj154@cphbusiness.dk", "cph-dj154");
                try {
            em.getTransaction().begin();
            em.createQuery("DELETE from Members").executeUpdate();
            em.persist(m1);
            em.persist(m2);
            em.persist(m3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    // TODO: Delete or change this method 
    @Test
    public void testMemberCount() {
        assertEquals(3, facade.getMemberCount());
    }
    
    @Test
    public void testAllMembers() {
      //  assertEquals(members, facade.getAllNames());
    }
    
    
    
    

}
