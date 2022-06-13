package com.gtrofimiec.accuweatherapi.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class APIConfig {

    @Value("${api.prod.location.postcode.endpoint}")
    private String LocationByPostCodeEndpoint;
    @Value("${api.prod.location.name.endpoint}")
    private String LocationByNameEndpoint;
    @Value("${api.prod.forecast.endpoint}")
    private String ForecastLocation;
    @Value("${api.key}")
    private String apiKey;
}