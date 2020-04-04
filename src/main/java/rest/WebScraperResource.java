package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import webscraper.TagCounter;
import webscraper.TagDTO;
import webscraper.Tester;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;

/**
 * REST Web Service
 *
 * @author lam
 */
@Path("scrape")
public class WebScraperResource {

    @Context
    private UriInfo context;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getTags() throws InterruptedException {
        return makeResponse();
    }

    private String makeResponse() throws InterruptedException {
        List<TagCounter> dataFetched = new Tester().runParallel();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<TagDTO> results = new ArrayList();
        for(TagCounter tc: dataFetched){
            results.add(new TagDTO(tc));
        }
        return gson.toJson(results);
    }

}
