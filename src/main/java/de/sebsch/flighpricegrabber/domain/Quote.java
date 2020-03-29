package de.sebsch.flighpricegrabber.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
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
