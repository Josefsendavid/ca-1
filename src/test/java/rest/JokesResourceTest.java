package rest;

import entities.Cars;
import entities.Joke;
import utils.EMF_Creator;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.parsing.Parser;
import java.net.URI;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
//Uncomment the line below, to temporarily disable this test
//@Disabled
public class JokesResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static Joke c1,c2,c3,c4,c5;
    
    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {

        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        
        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }
    
    @AfterAll
    public static void closeTestServer(){

         EMF_Creator.endREST_TestWithDB();
         httpServer.shutdownNow();
    }

    @BeforeEach
    public void setUp() {
     
    emf = Persistence.createEntityManagerFactory("puTest");
    EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Joke.deleteAllRows").executeUpdate();
            em.persist(new Joke("joke1", "joke reference1", "joke type1"));
            em.persist(new Joke("joke2", "joke reference2", "joke type2"));
            em.persist(new Joke("joke3", "joke reference3", "joke type3"));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    @Test
    public void testCount() throws Exception {
        given()
        .contentType("application/json")
        .get("/jokes/count").then()
        .assertThat()
        .statusCode(HttpStatus.OK_200.getStatusCode())
        .body("count", equalTo(3));   
    }
  
    @Test
    public void testGetAll() {
        given()
                .contentType("application/json")
                .get("/jokes/all")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("size()", is(3))
                .and()
                .body("reference", hasItems("joke reference1","joke reference1","joke reference2"));
                
    }
    
    
    @Test
    @Disabled
    public void testGetById(){
        given()
                .contentType("application/json")
                .get("/jokes/id/23")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("reference", equalTo("joke reference1"))
                .and()
                .body("type", equalTo("joke type1"));
                
    }
    
    @Test
    public void testServerIsUp() {
        given()
                .when()
                .get("/jokes")
                .then()
                .statusCode(200);
    }
    
    @Test
    public void testSpecificType() throws Exception {
        given()
                .contentType("application/json")
                .get("/jokes/type/type2").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("reference", hasItems("joke reference2"))
                .and()
                .body("joke", hasItems("joke2"));
    }
    
    @Test
    public void testSpecificReference() throws Exception {
        given()
                .contentType("application/json")
                .get("/jokes/reference/reference2").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("joke", hasItems("joke2"));
    }
}