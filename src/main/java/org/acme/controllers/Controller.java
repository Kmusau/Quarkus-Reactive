package org.acme.controllers;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.acme.services.CarsService;

@ApplicationScoped
@Path("/")
public class Controller {
    @Inject
    CarsService carsService;

    @GET
    @Path("cars/years")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Integer[]> fetchCarManufacturingYears() {
        return carsService.fetchCarManufacturingYears();
    }

}
