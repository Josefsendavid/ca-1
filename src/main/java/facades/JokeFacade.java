/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.MembersDTO;
import entities.Joke;
import entities.Members;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Gustav
 */
public class JokeFacade {
    
    
    private static JokeFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private JokeFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static JokeFacade getFacadeJoke(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new JokeFacade();
        }
        return instance;
    }
    
    public List<Joke> getAllJokes(){
        EntityManager em = emf.createEntityManager();
         try {
              Query query = em.createNamedQuery("Joke.getAll");
              List<Joke> jokes = query.getResultList();
              return jokes;
        }         
        finally {
            em.close();
        }
    }
    
    public Joke getJokeById(long id){
        EntityManager em = emf.createEntityManager();
        try {
              Query query = em.createNamedQuery("Joke.getById");
              query.setParameter("id", id);
              Joke joke = (Joke) query.getSingleResult();
              return joke;
        }         
        finally {
            em.close();
        }
    }
    
     public long getJokeCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long jokeCount = (long) em.createQuery("SELECT COUNT(m) FROM Joke m").getSingleResult();
            return jokeCount;
        } finally {
            em.close();
        }
    }
     
      public List<Joke> getJokeByType(String type){
        EntityManager em = emf.createEntityManager();
        try {
              Query query = em.createNamedQuery("Joke.getByType");
              query.setParameter("type", type);
              List<Joke> jokeList = query.getResultList();
              return jokeList;
        }         
        finally {
            em.close();
        }  
    }
      
      public List<Joke> getJokeByReference(String reference){
        EntityManager em = emf.createEntityManager();
        try {
              Query query = em.createNamedQuery("Joke.getByReference");
              query.setParameter("reference", reference);
              List<Joke> jokeList = query.getResultList();
              return jokeList;
        }         
        finally {
            em.close();
        }  
    }
      
      public static void insertJokeData(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Joke.deleteAllRows").executeUpdate();
            em.persist(new Joke("joke1", "joke reference1", "joke type1"));
            em.persist(new Joke("joke2", "joke reference1", "joke type1"));
            em.persist(new Joke("joke3", "joke reference2", "joke type2"));
            em.persist(new Joke("joke4", "joke reference3", "joke type2"));
            em.persist(new Joke("joke5", "joke reference3", "joke type4"));
            em.persist(new Joke("joke6", "joke reference1", "joke type4"));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
//      
//      public static void main(String[] args) {
//      insertJokeData();
//    }
    
}
