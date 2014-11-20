package com.ma.lance.restful;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.xml.ws.Response;

/**
 * Created by malance on 14/11/20.
 */
@Path("/rest")
public class RestfulService {
    @Path("/test")
    @GET
    @Produces("text/plain")
    public String test(){
        return "Hello world!";
    }
}
