package localdbservice.rest.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import localdbservice.rest.model.Person;

@Path("/login")
public class LoginResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	

    
    @GET
    @Produces({MediaType.APPLICATION_JSON,  MediaType.APPLICATION_XML })
   	public Person getLifeStyleBrowser(@QueryParam("email") String email,
   			@QueryParam("psw") String psw) {
    	Person p=Person.getPersonLoggedIn(email, psw);
    	return p;
   	}
}
