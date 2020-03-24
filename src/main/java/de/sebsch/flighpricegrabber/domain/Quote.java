package de.sebsch.flighpricegrabber.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {

    @JsonProperty("QuoteId")
    private Long id;

    @JsonProperty("MinPrice")
    private Long price;

    @JsonProperty("QuoteDateTime")
    private LocalDateTime dateTime;

}
