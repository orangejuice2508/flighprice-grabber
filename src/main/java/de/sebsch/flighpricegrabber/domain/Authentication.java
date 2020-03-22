package de.sebsch.flighpricegrabber.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@JacksonXmlRootElement(localName = "authentication")
public class Authentication {
    @JacksonXmlProperty(localName = "string")
    private String token;
}
