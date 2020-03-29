package de.sebsch.flighpricegrabber.service.Excel;

import de.sebsch.flighpricegrabber.domain.Quote;
import de.sebsch.flighpricegrabber.service.QuoteService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ExcelWriterService {

    @Value("${writeLocation}")
    private String writeLocation;

    private QuoteService quoteService;

    @Autowired
    public ExcelWriterService(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    public void writeQuotesToExcelFiles(List<Quote> quotes) {
        writeIndividualQuotesToExcelFile(quotes);
        writeCondensedQuotesToExcelFile(quotes);
    }

    private void writeCondensedQuotesToExcelFile(List<Quote> quotes) {

        log.info("Start of writing condensed quotes to excel file");

        // Create file and workbook
        String name = "Condensed_Quotes";
        File file = new File(writeLocation + name + ".xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Check if file exists, if not create a whole new Excel file and sheet
        if (file.exists()) {
            try {
                // Read file and create workbook with it
                FileInputStream fis = new FileInputStream(file);
                workbook = new XSSFWorkbook(fis);

                // Get existing sheet
                Sheet sheet = workbook.getSheet(name);

                // Get basis total price from sheet
                Row firstEntryRow = sheet.getRow(1);
                int basisTotalPrice = (int) firstEntryRow.getCell(1).getNumericCellValue();

                // Create next entry row for condensed quotes
                int newTotalPrice = quoteService.calculateTotalPrice(quotes);
                Row nextEntryRow = sheet.createRow(sheet.getLastRowNum() + 1);
                nextEntryRow.createCell(0).setCellValue(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                nextEntryRow.createCell(1).setCellValue(newTotalPrice);
                nextEntryRow.createCell(2).setCellValue(quoteService.normalizeTotalPrice(basisTotalPrice, newTotalPrice));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // Create sheet
            Sheet sheet = workbook.createSheet(name);

            // Create headline row
            Map<Integer, String> headlineMap = new HashMap<>(Map.of(0, "Date", 1, "Total Price", 2, "Normalized Price"));
            Row headline = sheet.createRow(0);
            for (Integer key : headlineMap.keySet()) {
                headline.createCell(key).setCellValue(headlineMap.get(key));
            }

            // Create first entry row for condensed quotes
            int newTotalPrice = quoteService.calculateTotalPrice(quotes);
            Row firstEntryRow = sheet.createRow(1);
            firstEntryRow.createCell(0).setCellValue(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            firstEntryRow.createCell(1).setCellValue(newTotalPrice);
            firstEntryRow.createCell(2).setCellValue(quoteService.normalizeTotalPrice(newTotalPrice, newTotalPrice));
        }

        // Write workbook to existing or new file
        writeWorkbookToFile(workbook, file);

        log.info("End of writing condensed quotes to excel file");
    }

    private void writeIndividualQuotesToExcelFile(List<Quote> quotes) {

        log.info("Start of writing individual quotes to excel file");

        // Create file and workbook
        String name = "Quotes_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss"));
        File file = new File(writeLocation + name + ".xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Create sheet
        Sheet sheet = workbook.createSheet(name);

        // Create headline row
        Map<Integer, String> headlineMap = new HashMap<>(Map.of(0, "ID", 1, "From", 2, "To", 3, "Price"));
        int currentRowNumber = 0;
        Row headline = sheet.createRow(currentRowNumber++);
        for (Integer key : headlineMap.keySet()) {
            headline.createCell(key).setCellValue(headlineMap.get(key));
        }

        // Create entry rows for each quote
        for (Quote quote : quotes) {
            Row entryRow = sheet.createRow(currentRowNumber);

            // Fill cells with respective values for each quote
            entryRow.createCell(0).setCellValue(currentRowNumber++);
            entryRow.createCell(1).setCellValue(quote.getOriginAirport());
            entryRow.createCell(2).setCellValue(quote.getDestinationAirport());
            entryRow.createCell(3).setCellValue(quote.getPrice());
        }

        // Create sum row with total price of all quotes
        Row sumRow = sheet.createRow(currentRowNumber);
        sumRow.createCell(2).setCellValue("Total");
        sumRow.createCell(3).setCellValue(quoteService.calculateTotalPrice(quotes));

        // Write workbook to actual file
        writeWorkbookToFile(workbook, file);

        log.info("End of writing individual quotes to excel file");
    }

    private void writeWorkbookToFile(final Workbook workbook, final File file) {
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            workbook.write(outputStream);
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
