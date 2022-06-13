package com.gtrofimiec.accuweatherapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyForecastsDto {

    @JsonProperty("Date")
    private String date;
    @JsonProperty("Temperature")
    private TemperatureDto temperature;
}