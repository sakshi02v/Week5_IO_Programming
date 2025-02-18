package org.io_programming_.basicproblems.countrowscsvfile;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;

public class CountRow {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\hp\\Desktop\\CG Training\\W5\\DAY01_CSV\\src\\main\\java\\org\\io_programming_\\basicproblems\\countrowscsvfile\\employees"; // Path to your CSV file

        int recordCount = 0;

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] nextRecord;
            boolean isHeader = true;

            while ((nextRecord = reader.readNext()) != null) {
                if (isHeader) { // Skip the header row
                    isHeader = false;
                    continue;
                }
                recordCount++; // Count only data rows
            }

            System.out.println("Total records (excluding header): " + recordCount);

        } catch (IOException | CsvValidationException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
    }
}
