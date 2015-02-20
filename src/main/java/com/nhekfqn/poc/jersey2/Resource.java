package com.nhekfqn.poc.jersey2;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/blah")
public class Resource {

    private final Service service;

    @Inject
    public Resource(Service service) {
        this.service = service;
    }

    @GET
    public String get() { // todo: json
        return service.getMessage();
    }

}
