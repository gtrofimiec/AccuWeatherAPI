package com.gtrofimiec.accuweatherapi;

import com.gtrofimiec.accuweatherapi.domain.ForecastDto;
import com.gtrofimiec.accuweatherapi.domain.LocationDto;
import com.gtrofimiec.accuweatherapi.facade.APIFacade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;

import java.util.Arrays;

@SpringBootTest
public class AccuWeatherAPITest {

    @Autowired
    private APIFacade apiFacade;

    @Test
    public void getLocationFromPostcodeTest() {

        //Given
        String postCode = "22-100";

        //When
        //Then
        try {
            LocationDto locationDto = apiFacade.getLocationKeyFromPostcode(postCode);
            System.out.println("Location key for postcode " + postCode + ": " + locationDto.getLocationKey());
        } catch(HttpStatusCodeException e) {
            System.out.println(e);
        }
    }

    @Test
    public void getLocationFromName() {

        //Given
        String name = "Chełm";

        //When
        LocationDto locationDto = apiFacade.getLocationKeyFromName(name);

        //Then
        try {
            System.out.println("Location key for " + name + ": " + locationDto.getLocationKey());
        } catch(RestClientException e) {
            System.out.println(e);
        }
    }

    @Test
    public void get5DaysForecast() {

        //Given
        String name = "Puławy";
        String locationKay = apiFacade.getLocationKeyFromName(name).getLocationKey();

        //When
        ForecastDto forecastDto = apiFacade.get5DaysForecast(locationKay);

        //Then
        try {
            System.out.println("5 days forecast for " + name + ":");
            Arrays.stream(forecastDto.getDailyForecastsList())
                    .forEach(f -> {
                        System.out.println(f.getDate());
                        System.out.println(f.getTemperature().getMinimum().getValue()
                                + f.getTemperature().getMinimum().getUnit());
                        System.out.println(f.getTemperature().getMaximum().getValue()
                                + f.getTemperature().getMinimum().getUnit());
                    });
        } catch(RestClientException e) {
            System.out.println(e);
        }
    }

    @Test
    public void get5DaysForecastForVoivod() {

        //Given
        String postCode = "22-100";
        String voivodName = apiFacade.getLocationKeyFromPostcode(postCode).getAdministrativeAreaDto().getLocalizedName();
        String locationKay = apiFacade.getLocationKeyFromName(voivodName).getLocationKey();

        //When
        ForecastDto forecastDto = apiFacade.get5DaysForecast(locationKay);

        //Then
        try {
            System.out.println("Searched postcode: " + postCode);
            System.out.println("provincial city: " + voivodName);
            System.out.println("5 days forecast for " + voivodName + ":");
            Arrays.stream(forecastDto.getDailyForecastsList())
                    .forEach(f -> {
                        System.out.println(f.getDate());
                        System.out.println(f.getTemperature().getMinimum().getValue()
                                + f.getTemperature().getMinimum().getUnit());
                        System.out.println(f.getTemperature().getMaximum().getValue()
                                + f.getTemperature().getMinimum().getUnit());
                    });
        } catch(RestClientException e) {
            System.out.println(e);
        }
    }
}
