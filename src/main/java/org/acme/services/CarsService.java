package org.acme.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.unchecked.Unchecked;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.acme.configurations.Configs;
import org.acme.restclientservices.BaseService;

@Slf4j
@ApplicationScoped
public class CarsService implements BaseService {
    @Inject
    Configs configs;
    @Inject
    BaseService baseService;
    ObjectMapper objectMapper = new ObjectMapper();

    public Uni<Integer[]> fetchCarManufacturingYears() {
        return baseService.getCarManufacturingYears(configs.rapidCarApiUrl())
                .map(Unchecked.function(response -> objectMapper.readValue(response.readEntity(String.class), Integer[].class)))
                .invoke(response -> log.info("Received - {} responses from Rapid API", response.length));
    }

}
