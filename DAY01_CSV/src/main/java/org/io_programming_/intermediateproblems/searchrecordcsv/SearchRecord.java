package org.io_programming_.intermediateproblems.searchrecordcsv;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class SearchRecord {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\hp\\Desktop\\CG Training\\W5\\DAY01_CSV\\src\\main\\java\\org\\io_programming_\\basicproblems\\countrowscsvfile\\employees";

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter employee name to search: ");
        String searchName = scanner.nextLine().trim();

        boolean found = false;

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] nextRecord;
            boolean isHeader = true;

            while ((nextRecord = reader.readNext()) != null) {
                if (isHeader) { // Skip the header row
                    isHeader = false;
                    continue;
                }

                // Ensure valid row format
                if (nextRecord.length < 4) {
                    System.err.println("Skipping malformed row: " + String.join(", ", nextRecord));
                    continue;
                }

                String name = nextRecord[1].trim();

                // Case-insensitive comparison
                if (name.equalsIgnoreCase(searchName)) {
                    System.out.printf("Employee Found: %s | Department: %s | Salary: %s%n",
                            name, nextRecord[2], nextRecord[3]);
                    found = true;
                    break; // Stop searching after finding the first match
                }
            }

            if (!found) {
                System.out.println("Employee not found!");
            }

        } catch (IOException | CsvValidationException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
    }
}

