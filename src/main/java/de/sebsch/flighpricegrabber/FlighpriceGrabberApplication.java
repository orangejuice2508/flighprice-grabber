package de.sebsch.flighpricegrabber;

import de.sebsch.flighpricegrabber.service.AuthenticationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FlighpriceGrabberApplication {

	//private static final Logger log = LoggerFactory.getLogger(FlighpriceGrabberApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FlighpriceGrabberApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(AuthenticationService authenticationService) throws Exception {
		return args -> {
			authenticationService.authenticate();
		};
	}

}
