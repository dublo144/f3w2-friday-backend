package rest;
/*
 * author mads
 * version 1.0
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.ChuckDTO;
import dto.DadDTO;
import dto.ResponseDTO;
import utils.HttpUtils;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;


@Path("jokes")
public class JokesResource {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    @GET
    @RolesAllowed({"user", "admin", "useradmin"})
    @Produces({MediaType.APPLICATION_JSON})
    public String getJokes() throws IOException {
        ChuckDTO chuck = gson.fromJson(HttpUtils.fetchData("https://api.chucknorris.io/jokes/random"), ChuckDTO.class);
        DadDTO dad = gson.fromJson(HttpUtils.fetchData("https://icanhazdadjoke.com"), DadDTO.class);
        return gson.toJson(new ResponseDTO(chuck, dad));
    }
}
