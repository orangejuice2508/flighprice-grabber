package de.sebsch.flighpricegrabber.service.Excel;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class ExcelReaderService {

    public Map<String, String> readAirportsAndCodes() {

        log.info("Start of reading airports and codes");

        Map<String, String> airportCodesMap = new HashMap<>();

        try {
            File airportCodesExcelFile = ResourceUtils.getFile("classpath:static/Airports_&_IATA_Codes.xlsx");
            FileInputStream fis = new FileInputStream(airportCodesExcelFile);

            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);

            int lastRowNumber = sheet.getLastRowNum();

            for (int i = 1; i <= lastRowNumber; i++) {
                Row row = sheet.getRow(i);
                String aiport = row.getCell(0).toString();
                String iataCode = row.getCell(1).toString();

                airportCodesMap.put(aiport, iataCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("End of reading airports and codes");

        return airportCodesMap;
    }
}
