package com.dmitriy.fedo.springbootRestAPI;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CSVHelper {
    public static String TYPE = "text/csv";
    static String[] HEADERS = {"State", "Name", "Institution Type", "Phone Number", "Website"};

    public static boolean hasCSVFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    public static List<ListOfUniversities> csvToDatabase(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT)) {

            List<ListOfUniversities> universitiesList = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                ListOfUniversities university = new ListOfUniversities(
                        csvRecord.get(HEADERS[0]),
                        csvRecord.get(HEADERS[1]),
                        csvRecord.get(HEADERS[2]),
                        csvRecord.get(HEADERS[3]),
                        csvRecord.get(HEADERS[4])
                );

                universitiesList.add(university);
            }

            return universitiesList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream databaseToCSV(List<ListOfUniversities> universitiesList) {
        final CSVFormat format = CSVFormat.DEFAULT;

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format)) {
            for (ListOfUniversities university : universitiesList) {
                List<String> data = Arrays.asList(
                        String.valueOf(university.getId()),
                        university.getName(),
                        university.getPhone(),
                        university.getWebsite(),
                        university.getType()
                );

                csvPrinter.printRecord(data);
            }

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }
}
