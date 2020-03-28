package de.sebsch.flighpricegrabber.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.sebsch.flighpricegrabber.domain.FilterParameters;
import de.sebsch.flighpricegrabber.domain.FlightParameters;
import de.sebsch.flighpricegrabber.domain.Quote;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class QuoteService {

    private static final String quoteBaseUrl = "https://api.skypicker.com/flights";

    public List<Quote> getQuotes(FlightParameters searchParameters, FilterParameters filterParameters) throws JsonProcessingException {

        String uri = quoteBaseUrl + "?" +
                searchParameters.toUriParameters() + "&" +
                filterParameters.toUriParameters();

        ResponseEntity<String> responseEntity = new RestTemplate().getForEntity(uri, String.class);

        return deseralizeResponse(responseEntity);
    }

    private List<Quote> deseralizeResponse(ResponseEntity<String> responseEntity) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode dataNode = objectMapper.readTree(Objects.requireNonNull(responseEntity.getBody())).get("data");

        return objectMapper.readValue(dataNode.toString(), new TypeReference<>() {
        });
    }


}
