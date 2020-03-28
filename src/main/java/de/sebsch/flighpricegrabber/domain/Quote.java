package de.sebsch.flighpricegrabber.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {

    @JsonProperty("id")
    private String id;

    @JsonProperty("flyFrom")
    private String originAirport;

    @JsonProperty("flyTo")
    private String destinationAirport;

    @JsonProperty("price")
    private Integer price;

}
