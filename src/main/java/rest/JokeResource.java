/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.JokeDTO;
import dtos.MembersDTO;
import entities.Joke;
import facades.JokeFacade;
import facades.MemberFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

/**
 * REST Web Service
 *
 * @author Gustav
 */
@Path("jokes")
public class JokeResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    private static final JokeFacade FACADE = JokeFacade.getFacadeJoke(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"jokes lol\"}";
    }

    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getJokeCount() {
        long count = FACADE.getJokeCount();
        return "{\"count\":" + count + "}";
    }

    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAll() {
        List<Joke> allJokes = FACADE.getAllJokes();
        return GSON.toJson(allJokes);
    }

    @Path("allDTO")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllDTO() {
        List<Joke> a = FACADE.getAllJokes();
        List<JokeDTO> dto = new ArrayList<JokeDTO>();
        for (Joke i : a) {
            JokeDTO add = new JokeDTO(i);
            dto.add(add);
        }

        return GSON.toJson(dto);
    }

    @GET
    @Path("/id/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getJokeById(@PathParam("id") int id) {
        Joke joke = FACADE.getJokeById(id);
        return GSON.toJson(joke);
    }

    @GET
    @Path("/reference/{reference}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getJokeByReference(@PathParam("reference") String reference) {
        List<Joke> jokeList = FACADE.getJokeByReference(reference);
        return GSON.toJson(jokeList);
    }
    
    @GET
    @Path("/referenceDTO/{reference}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getJokeByReferenceDTO(@PathParam("reference") String reference) {
        List<Joke> a = FACADE.getJokeByReference(reference);
        List<JokeDTO> dto = new ArrayList<JokeDTO>();
        for (Joke i : a) {
            JokeDTO add = new JokeDTO(i);
            dto.add(add);
        }
        return GSON.toJson(dto);
    }

    @GET
    @Path("/type/{type}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getJokeByType(@PathParam("type") String type) {
        List<Joke> jokeList = FACADE.getJokeByType(type);
        return GSON.toJson(jokeList);
    }
    
    @GET
    @Path("/typeDTO/{type}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getJokeByTypeDTO(@PathParam("type") String type) {
        List<Joke> a = FACADE.getJokeByType(type);
        List<JokeDTO> dto = new ArrayList<JokeDTO>();
        for (Joke i : a) {
            JokeDTO add = new JokeDTO(i);
            dto.add(add);
        }
        return GSON.toJson(dto);
    }
}
