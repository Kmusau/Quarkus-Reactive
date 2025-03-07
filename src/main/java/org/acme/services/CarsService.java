package org.acme.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.unchecked.Unchecked;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.acme.configurations.Configs;
import org.acme.dtos.ApiResponse;
import org.acme.restclientservices.BaseService;

import java.util.Arrays;

@Slf4j
@ApplicationScoped
public class CarsService implements BaseService {
    @Inject
    Configs configs;
    @Inject
    BaseService baseService;
    ObjectMapper objectMapper = new ObjectMapper();

    public Uni<ApiResponse> fetchCarManufacturingYears() {
        return baseService.getCarManufacturingYears(configs.rapidCarApiUrl())
                .map(Unchecked.function(response -> objectMapper.readValue(response.readEntity(String.class), Integer[].class)))
                .invoke(response -> log.info("Received - {} responses from Rapid API", response.length))
                .map(this::formulateResponse);
    }

    public ApiResponse formulateResponse(Integer[] apiResponse) {
        ApiResponse response = new ApiResponse();
        response.setMessage("Successfully fetched car manufacturing years");
        response.setData(Arrays.stream(apiResponse).sorted().toList());
        return response;
    }

}
