package com.gtrofimiec.accuweatherapi.controller;

import com.gtrofimiec.accuweatherapi.domain.ForecastDto;
import com.gtrofimiec.accuweatherapi.domain.LocationDto;
import com.gtrofimiec.accuweatherapi.exceptions.DataNotFoundException;
import com.gtrofimiec.accuweatherapi.facade.APIFacade;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/accuWeatherApi/")
public class APIController {

    private final APIFacade apiFacade;

    @RequestMapping(method = RequestMethod.GET, value = "getLocation/{postCode}")
    public LocationDto getLocationKeyFromPostcode(@PathVariable("postCode") String postCode)
            throws DataNotFoundException {
        return apiFacade.getLocationKeyFromPostcode(postCode);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getLocation/{name}")
    public LocationDto getLocationKeyFromName(@PathVariable("name") String name)
            throws DataNotFoundException {
        return apiFacade.getLocationKeyFromName(name);
    }

    @RequestMapping(method = RequestMethod.GET, value = "get5DaysForecast/{locationKey}")
    public ForecastDto get5DaysForecast(@PathVariable("locationKey") String locationKey)
            throws DataNotFoundException {
        return apiFacade.get5DaysForecast(locationKey);
    }
}