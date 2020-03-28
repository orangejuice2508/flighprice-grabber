package de.sebsch.flighpricegrabber.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor
public class FlightParameters {

    private final String locale = "de";
    private final String currency = "EUR";
    private final String partnerMarket = "de";
    private final String partner = "picky";
    private String originAirportCode;
    private String destinationAirportCode;
    private LocalDate outboundDate;
    private LocalDate inboundDate;
    private FlightType flightType;
    private Integer adults;
    private String adultHoldBag;
    private Cabin cabin;

    public String toUriParameters() {
        return "fly_from" + "=" + originAirportCode + "&" +
                "fly_to" + "=" + destinationAirportCode + "&" +
                "date_from" + "=" + outboundDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "&" +
                "date_to" + "=" + outboundDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "&" +
                "return_from" + "=" + inboundDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "&" +
                "return_to" + "=" + inboundDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "&" +
                "flight_type" + "=" + flightType.toString().toLowerCase() + "&" +
                "adults" + "=" + adults.toString() + "&" +
                "selected_cabins" + "=" + cabin.toString() + "&" +
                "adult_hold_bag" + "=" + adultHoldBag + "&" +
                "locale" + "=" + locale + "&" +
                "curr" + "=" + currency + "&" +
                "partner_market" + "=" + partnerMarket + "&" +
                "partner" + "=" + partner;
    }

    public enum FlightType {
        ROUND, ONEWAY
    }

    public enum Cabin {
        // Economy
        M,
        // Premium Economy
        W,
        // Business
        C,
        // First Class
        F
    }
}
