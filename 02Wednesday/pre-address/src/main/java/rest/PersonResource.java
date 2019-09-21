package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.PersonDTO;
import entities.Person;
import exceptions.PersonNotFoundException;
import utils.EMF_Creator;
import facades.PersonFacade;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("person")
@Produces({MediaType.APPLICATION_JSON})
public class PersonResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
            "pu",
            "jdbc:mysql://localhost:3307/startcode",
            "dev",
            "ax2",
            EMF_Creator.Strategy.CREATE);
    private static final PersonFacade FACADE = PersonFacade.getPersonFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    public String demo() {
        return "{\"msg\":\"Nothing here but us lemmings\"}";
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public String addPerson(String person) {
        Person pdto = GSON.fromJson(person, Person.class);
        Person p = FACADE.addPerson(pdto.getfName(), pdto.getlName(), pdto.getPhone());
        return GSON.toJson(new PersonDTO(p));
    }

    @GET
    @Path("/all")
    public String getAllPersonsEndpoint() {
        return "{\"all\":"
                + GSON.toJson(FACADE.getAllPersons()
                .stream()
                .map(o -> new PersonDTO(o))
                .collect(Collectors.toList()))
                + "}";
    }
    
    @GET
    @Path("retardation")
    public String divisonByZero()
    {
        return Integer.toString(0/1/0);
    }

    @GET
    @Path("/{id}")
    public String getPersonByIDEndPoint(Person entity, @PathParam("id") int id) throws PersonNotFoundException{
        if(FACADE.getPerson(id) == null)
        {
            throw new PersonNotFoundException("Could not get person with id "+id+ " - Person not found");
        }
       
        return GSON.toJson(new PersonDTO(FACADE.getPerson(id)));
    }

    @PUT
    @Path("/{id}")
    public String update(@PathParam("id") int id, String p)  throws PersonNotFoundException {
        Person ppl = GSON.fromJson(p, Person.class);
        ppl.setId(id);
        if(FACADE.getPerson(id) == null)
        {
            throw new PersonNotFoundException("Could not update person with id "+id+" - Person not found");
        }
        return GSON.toJson(new PersonDTO(FACADE.editPerson(ppl)));
    }

    @DELETE
    @Path("/{id}")
    public void deleteByID(@PathParam("id") int id)  throws PersonNotFoundException {
        
        if(FACADE.getPerson(id) == null)
        {
            throw new PersonNotFoundException("Could not delete person with id "+id+" - Person not found");
        }
        FACADE.deletePerson(id);
    }
}
