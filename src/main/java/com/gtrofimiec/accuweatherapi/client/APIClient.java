package com.gtrofimiec.accuweatherapi.client;

import com.gtrofimiec.accuweatherapi.config.APIConfig;
import com.gtrofimiec.accuweatherapi.domain.ForecastDto;
import com.gtrofimiec.accuweatherapi.domain.LocationDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

@Component
public class APIClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(APIClient.class);

    private final RestTemplate restTemplate;
    private final APIConfig apiConfig;

    public APIClient(RestTemplate restTemplate, APIConfig apiConfig) {
        this.restTemplate = restTemplate;
        this.apiConfig = apiConfig;
    }

    public LocationDto getTownLocationKeyFromPostcode(String postCode) {

        URI url = UriComponentsBuilder.fromHttpUrl(apiConfig.getLocationByPostCodeEndpoint())
                .queryParam("apikey", apiConfig.getApiKey())
                .queryParam("q", postCode)
                .build()
                .encode()
                .toUri();
        try {
            LocationDto[] foundKey = restTemplate.getForObject(url, LocationDto[].class);

            LocationDto obj = Optional.ofNullable(foundKey)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList())
                    .stream()
                    .findFirst().get();
            LOGGER.info("Location key for post code: " + postCode + " (" + obj.getName() + "): " + obj.getLocationKey());

            return new LocationDto(
                    obj.getLocationKey(),
                    obj.getName(),
                    obj.getAdministrativeAreaDto()
            );
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public LocationDto getTownLocationKeyFromName(String townName) {

        URI url = UriComponentsBuilder.fromHttpUrl(apiConfig.getLocationByNameEndpoint())
                .queryParam("apikey", apiConfig.getApiKey())
                .queryParam("q", townName)
                .queryParam("language", "pl")
                .build()
                .encode()
                .toUri();
        try {
            LocationDto[] foundKey = restTemplate.getForObject(url, LocationDto[].class);

            LocationDto obj = Optional.ofNullable(foundKey)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList())
                    .stream()
                    .findFirst().get();

            LOGGER.info("Location key for " + townName + " has been found");

            return new LocationDto(
                    obj.getLocationKey(),
                    obj.getName(),
                    obj.getAdministrativeAreaDto()
            );
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public ForecastDto get5DaysForecast(String locationKey) {

        URI url = UriComponentsBuilder.fromHttpUrl(apiConfig.getForecastLocation() + "/" + locationKey)
                .queryParam("apikey", apiConfig.getApiKey())
                .queryParam("language", "pl")
                .queryParam("details", "false")
                .queryParam("metric", "true")
                .build()
                .encode()
                .toUri();
        try {
            ForecastDto foundData = restTemplate.getForObject(url, ForecastDto.class);
            LOGGER.info("AccuWeather 5 days forecast for location key " + locationKey + " has been retrieved");
            return foundData;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }
}