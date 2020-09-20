package facades;

import entities.Joke;
import utils.EMF_Creator;
import entities.Members;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


public class JokeFacadeTest {

    private static Joke j1, j2, j3;
    private static EntityManagerFactory emf;
    private static JokeFacade facade;

    public JokeFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = JokeFacade.getFacadeJoke(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {

        emf = Persistence.createEntityManagerFactory("puTest");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Joke.deleteAllRows").executeUpdate();
            em.persist(new Joke("joke1", "joke reference1", "joke type1"));
            em.persist(new Joke("joke2", "joke reference1", "joke type1"));
            em.persist(new Joke("joke3", "joke reference2", "joke type2"));
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
        assertEquals(3, facade.getJokeCount());
    }
    
    @Disabled
    @Test
    public void testAMembers() {
        List<Joke> list = facade.getAllJokes();
        Joke testData = new Joke("joke1", "joke reference1", "joke type1");
        testData.setId(16L);
        assertEquals(testData, list.get(0));
    }

}
