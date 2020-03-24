package de.sebsch.flighpricegrabber.util;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Getter
@Component
@Scope
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constants {

    @Value("${apiKey}")
    private String apiKey;

    private String host = "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com";

    private String pricingBaseUrl = "https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/pricing/v1.0";

}
