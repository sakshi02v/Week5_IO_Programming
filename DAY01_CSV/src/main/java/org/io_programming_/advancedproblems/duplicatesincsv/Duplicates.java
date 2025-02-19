package org.io_programming_.advancedproblems.duplicatesincsv;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Duplicates {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\hp\\Desktop\\CG Training\\W5\\DAY01_CSV\\src\\main\\java\\org\\io_programming_\\advancedproblems\\duplicatesincsv\\students.csv";

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] nextRecord;
            boolean isHeader = true;
            Set<String> seenIDs = new HashSet<>(); // To track unique IDs
            Set<String[]> duplicateRecords = new HashSet<>(); // To store duplicate records

            System.out.println("Duplicate Records based on ID:");
            System.out.println("-------------------------------");

            while ((nextRecord = reader.readNext()) != null) {
                if (isHeader) {
                    isHeader = false; // Skip the header row
                    continue;
                }

                // Ensure the row has at least 1 column (ID)
                if (nextRecord.length < 1) {
                    System.err.println("Skipping malformed row: " + String.join(", ", nextRecord));
                    continue;
                }

                String id = nextRecord[0]; // Assume ID is in the first column

                // Check if the ID has already been seen
                if (seenIDs.contains(id)) {
                    duplicateRecords.add(nextRecord); // Add to duplicates set if ID is already encountered
                } else {
                    seenIDs.add(id); // Add ID to the seen set
                }
            }

            // Print duplicate records
            if (duplicateRecords.isEmpty()) {
                System.out.println("No duplicate records found.");
            } else {
                for (String[] record : duplicateRecords) {
                    System.out.printf("ID: %s | Name: %s | Age: %s | Marks: %s%n",
                            record[0], record[1], record[2], record[3]);
                }
            }

        } catch (IOException | CsvValidationException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
    }
}
