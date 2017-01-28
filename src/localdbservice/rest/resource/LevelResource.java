package localdbservice.rest.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import localdbservice.rest.model.Level;

@Path("/level")
public class LevelResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
    @GET
	@Path("{levelId}")
    @Produces({MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML })
	public Level getLevel(@PathParam("levelId") int id) {
    	Level level = Level.getLevelById(id);
         return level;
	}
    
    @GET
    @Produces({MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML })
   	public List<Level> getLevelBrowser() {
		System.out.println("Getting list of lifeStyle...");
		List<Level> levels = Level.getAll();
		return levels;
   	}
}
