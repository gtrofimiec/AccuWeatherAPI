package com.gtrofimiec.accuweatherapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ForecastDto {

    @JsonProperty("DailyForecasts")
    private DailyForecastsDto[] dailyForecastsList;
}
