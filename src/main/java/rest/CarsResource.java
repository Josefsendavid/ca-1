
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.CarsDTO;
import dtos.MembersDTO;
import facades.CarFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

@Path("cars")
public class CarsResource {
    
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    
    private static final CarFacade FACADE =  CarFacade.getCarFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    public CarsResource() {
    }
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRenameMeCount() {
        long count = FACADE.getCarCount();
        return "{\"count\":"+count+"}";
    }

    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAll(){
            List<CarsDTO> allCars = FACADE.getAllCars();
        return GSON.toJson(allCars);
    }

}
