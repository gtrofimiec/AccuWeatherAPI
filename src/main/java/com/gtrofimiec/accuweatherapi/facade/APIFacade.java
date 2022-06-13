package com.gtrofimiec.accuweatherapi.facade;

import com.gtrofimiec.accuweatherapi.client.APIClient;
import com.gtrofimiec.accuweatherapi.domain.ForecastDto;
import com.gtrofimiec.accuweatherapi.domain.LocationDto;
import com.gtrofimiec.accuweatherapi.exceptions.DataNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class APIFacade {

    private final APIClient apiClient;

    public LocationDto getLocationKeyFromPostcode(String postCode) throws DataNotFoundException {
        return apiClient.getTownLocationKeyFromPostcode(postCode);
    }

    public LocationDto getLocationKeyFromName(String name) throws DataNotFoundException {
        return apiClient.getTownLocationKeyFromName(name);
    }

    public ForecastDto get5DaysForecast(String locationKey) throws DataNotFoundException {
        return apiClient.get5DaysForecast(locationKey);
    }
 }