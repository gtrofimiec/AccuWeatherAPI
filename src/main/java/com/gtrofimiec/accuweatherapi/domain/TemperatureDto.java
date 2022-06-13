package com.gtrofimiec.accuweatherapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TemperatureDto {

    @JsonProperty("Minimum")
    private MinimumDto minimum;
    @JsonProperty("Maximum")
    private MaximumDto maximum;
}