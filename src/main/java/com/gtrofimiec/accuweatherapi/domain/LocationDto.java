package com.gtrofimiec.accuweatherapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationDto {

    @JsonProperty("Key")
    private String locationKey;
    @JsonProperty("LocalizedName")
    private String name;
    @JsonProperty("AdministrativeArea")
    private AdministrativeAreaDto administrativeAreaDto;
}