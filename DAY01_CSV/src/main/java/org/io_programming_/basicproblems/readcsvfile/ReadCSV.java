package org.io_programming_.basicproblems.readcsvfile;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;

public class ReadCSV {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\hp\\Desktop\\CG Training\\W5\\DAY01_CSV\\src\\main\\java\\org\\io_programming_\\basicproblems\\readcsvfile\\csvfile";

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] nextRecord;
            boolean isHeader = true;

            while ((nextRecord = reader.readNext()) != null) {
                // Skip empty lines
                if (nextRecord.length == 0 || nextRecord[0].trim().isEmpty()) {
                    continue;
                }

                if (isHeader) { // Skip the header row
                    isHeader = false;
                    continue;
                }

                // Ensure row has exactly 4 columns
                if (nextRecord.length < 4) {
                    System.err.println("Skipping malformed row: " + String.join(", ", nextRecord));
                    continue;
                }

                System.out.printf("ID: %s | Name: %s | Age: %s | Marks: %s%n",
                        nextRecord[0], nextRecord[1], nextRecord[2], nextRecord[3]);
            }
        } catch (IOException | CsvValidationException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
    }
}
