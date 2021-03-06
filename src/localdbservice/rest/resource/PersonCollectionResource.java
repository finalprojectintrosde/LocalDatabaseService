package localdbservice.rest.resource;

import localdbservice.rest.model.*;

import java.io.IOException;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;


@Path("/person")
public class PersonCollectionResource {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;


	// Return the list of people to the user in the browser
	@GET
	@Produces({ MediaType.TEXT_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Person> getPersonsBrowser() {
		System.out.println("Getting list of people...");
		List<Person> people = Person.getAll();
		return people;
	}

	// retuns the number of people
	// to get the total number of records
	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		System.out.println("Getting count...");
		List<Person> people = Person.getAll();
		int count = people.size();
		return String.valueOf(count);
	}

	@POST
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Person newPerson(Person person) throws IOException {
		System.out.println("Creating new person...");
		person.setLevel(Level.getLevelById(person.getIdLevel()));
		person.setLifeStyle(LifeStyle.getLifeStyleById(person.getIdLifeStyle()));
		return Person.savePerson(person);
	}

	@Path("{personId}")
	public PersonResource getPerson(@PathParam("personId") int id) {
		return new PersonResource(uriInfo, request, id);
	}
}