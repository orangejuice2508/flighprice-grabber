package de.sebsch.flighpricegrabber;

import de.sebsch.flighpricegrabber.service.ExcelService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FlighpriceGrabberApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlighpriceGrabberApplication.class, args);
	}

	@Bean
    public CommandLineRunner run(ExcelService service) {
        return args -> service.getAirportsAndCodes();
	}

}
