package org.acme.restclientservices;

import io.quarkus.rest.client.reactive.QuarkusRestClientBuilder;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;


public interface BaseService {
    Logger log = LoggerFactory.getLogger(BaseService.class);

    default Uni<Response> getCarManufacturingYears(String url) {
        return QuarkusRestClientBuilder.newBuilder()
                .baseUri(URI.create(url))
                .build(MyRemoteService.class)
                .getCarManufacturingYears()
                .onItem().invoke(response -> log.info("Received response from Rapid API: {}", response.readEntity(String.class)))
                .onFailure().invoke(throwable -> log.error("Error occurred while invoking Rapid API: ", throwable));
    }
}
