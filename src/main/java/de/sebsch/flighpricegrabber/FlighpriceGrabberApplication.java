package de.sebsch.flighpricegrabber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FlighpriceGrabberApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlighpriceGrabberApplication.class, args);
	}

/*	@Bean
    public CommandLineRunner run(ScheduledTasks service) {
        return args -> service.getAndSaveQuotes();
	}*/

}
