package de.sebsch.flighpricegrabber;

import de.sebsch.flighpricegrabber.domain.FilterParameters;
import de.sebsch.flighpricegrabber.domain.FlightParameters;
import de.sebsch.flighpricegrabber.domain.Quote;
import de.sebsch.flighpricegrabber.service.Excel.ExcelReaderService;
import de.sebsch.flighpricegrabber.service.Excel.ExcelWriterService;
import de.sebsch.flighpricegrabber.service.QuoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class ScheduledTasks {

    private ExcelReaderService excelReaderService;

    private QuoteService quoteService;

    private ExcelWriterService excelWriterService;

    @Autowired
    public ScheduledTasks(ExcelReaderService excelReaderService, QuoteService quoteService, ExcelWriterService excelWriterService) {
        this.excelReaderService = excelReaderService;
        this.quoteService = quoteService;
        this.excelWriterService = excelWriterService;
    }

    @Scheduled(cron = "0 0 6 * * *")
    public void getAndSaveQuotes() {
        Map<String, String> airportsAndCode = excelReaderService.readAirportsAndCodes();
        FlightParameters flightParameters = new FlightParameters(LocalDate.of(2020, 4, 25),
                LocalDate.of(2020, 4, 30), FlightParameters.FlightType.ROUND,
                1, "1", FlightParameters.Cabin.M);
        FilterParameters filterParameters = new FilterParameters(1, FilterParameters.SortType.PRICE, true);

        List<Quote> quotes = new ArrayList<>();

        log.info("Start of getting quotes");
        for (String originAirport : airportsAndCode.keySet()) {
            for (String destinationAirport : airportsAndCode.keySet()) {
                if (!originAirport.equals(destinationAirport)) {
                    flightParameters.setOriginAirportCode(airportsAndCode.get(originAirport));
                    flightParameters.setDestinationAirportCode(airportsAndCode.get(destinationAirport));
                    quotes.addAll(quoteService.getQuotes(flightParameters, filterParameters));
                }
            }
        }
        log.info("End of getting quotes");

        excelWriterService.writeQuotesToExcelFiles(quotes);
    }
}
