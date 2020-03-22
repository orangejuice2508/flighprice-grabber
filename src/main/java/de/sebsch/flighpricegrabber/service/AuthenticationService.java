package de.sebsch.flighpricegrabber.service;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import de.sebsch.flighpricegrabber.domain.Authentication;
import de.sebsch.flighpricegrabber.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class AuthenticationService {

    private XmlMapper xmlMapper;

    private Constants constants;

    @Autowired
    public AuthenticationService(Constants constants) {
        this.xmlMapper = new XmlMapper();
        this.constants = constants;
    }

    public Authentication authenticate() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_XML_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(constants.getAuthenticationBaseUrl())
                .queryParam("apiKey", constants.getApiKey());

        HttpEntity<String> response = new RestTemplate()
                .exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity<>(headers), String.class);

        Authentication authentication ;

        if(response.getBody() != null){
            String toParse = "<authentication>" + response.getBody() + "</authentication>";
            authentication = xmlMapper.readValue(toParse, Authentication.class);
        } else {
            // Replace with correct exception
            throw new Exception("Empty response body");
        }

        return authentication;
    }
}
