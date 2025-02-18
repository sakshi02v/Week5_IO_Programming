package org.io_programming_.intermediateproblems.filterrecordscsv;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;

public class FilterRecord {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\hp\\Desktop\\CG Training\\W5\\DAY01_CSV\\src\\main\\java\\org\\io_programming_\\intermediateproblems\\filterrecordscsv\\students.csv";

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] nextRecord;
            boolean isHeader = true;

            System.out.println("Students with marks > 80:");
            System.out.println("--------------------------------------");

            while ((nextRecord = reader.readNext()) != null) {
                if (isHeader) { // Skip the header row
                    isHeader = false;
                    continue;
                }

                // Ensure the row has at least 4 columns (ID, Name, Age, Marks)
                if (nextRecord.length < 4) {
                    System.err.println("Skipping malformed row: " + String.join(", ", nextRecord));
                    continue;
                }

                try {
                    int marks = Integer.parseInt(nextRecord[3].trim()); // Convert Marks column to integer

                    if (marks > 80) {
                        System.out.printf("ID: %s | Name: %s | Marks: %d%n", nextRecord[0], nextRecord[1], marks);
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Skipping row with invalid marks: " + String.join(", ", nextRecord));
                }
            }

        } catch (IOException | CsvValidationException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
    }
}
