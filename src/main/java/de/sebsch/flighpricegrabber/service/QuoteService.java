package de.sebsch.flighpricegrabber.service;

import de.sebsch.flighpricegrabber.domain.Quote;
import de.sebsch.flighpricegrabber.domain.QuoteSearchParameters;
import de.sebsch.flighpricegrabber.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class QuoteService {

    private Constants constants;

    @Autowired
    public QuoteService(Constants constants) {
        this.constants = constants;
    }

    public List<Quote> createQuoteSession(QuoteSearchParameters searchParameters) {

        HttpEntity<?> httpEntity = new HttpEntity<>(searchParameters.formatAsBody(), getHeaders(MediaType.APPLICATION_FORM_URLENCODED_VALUE));
        HttpEntity<String> entity = new RestTemplate().exchange(constants.getPricingBaseUrl(), HttpMethod.POST, httpEntity, String.class);

        return null;
    }

    private MultiValueMap<String, String> getBody() {
        return null;
    }

    private MultiValueMap<String, String> getHeaders(String contentTypeValue) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-rapidapi-host", constants.getHost());
        headers.add("x-rapidapi-key", constants.getApiKey());
        headers.add("content-type", contentTypeValue);

        return headers;
    }


}
