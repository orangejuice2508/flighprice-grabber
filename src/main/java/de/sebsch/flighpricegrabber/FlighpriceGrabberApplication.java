package de.sebsch.flighpricegrabber;

import de.sebsch.flighpricegrabber.domain.QuoteSearchParameters;
import de.sebsch.flighpricegrabber.service.QuoteService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class FlighpriceGrabberApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlighpriceGrabberApplication.class, args);
	}

	@Bean
    public CommandLineRunner run(QuoteService quoteService) {
        return args -> quoteService.createQuoteSession(new QuoteSearchParameters("US", "USD", "en-US", "SFO-sky", "LHR-sky", LocalDate.of(2020, 9, 10), LocalDate.of(2020, 9, 20), 1));
	}

}
