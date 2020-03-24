package de.sebsch.flighpricegrabber.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor
public class QuoteSearchParameters {

    private String country;

    private String currency;

    private String locale;

    private String originPlace;

    private String destinationPlace;

    private LocalDate outboundDate;

    private LocalDate inboundDate;

    private Integer adults;

    public String formatAsBody() {
        return "country" + "=" + country + "&" +
                "currency" + "=" + currency + "&" +
                "locale" + "=" + locale + "&" +
                "originPlace" + "=" + originPlace + "&" +
                "destinationPlace" + "=" + destinationPlace + "&" +
                "outboundDate" + "=" + outboundDate.format(DateTimeFormatter.ISO_DATE) + "&" +
                "inboundDate" + "=" + inboundDate.format(DateTimeFormatter.ISO_DATE) + "&" +
                "adults" + "=" + adults.toString();
    }
}
