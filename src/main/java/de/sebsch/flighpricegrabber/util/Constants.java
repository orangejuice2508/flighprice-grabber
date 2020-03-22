package de.sebsch.flighpricegrabber.util;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

    private String authenticationBaseUrl = "https://partners.api.skyscanner.net/apiservices/token/v2/gettoken";

    private String quotesBaseUrl = "https://partners.api.skyscanner.net/apiservices/browsequotes/v1.0";

}
