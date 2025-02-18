package org.io_programming_.intermediateproblems.sortcsv;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Sorting {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\hp\\Desktop\\CG Training\\W5\\DAY01_CSV\\src\\main\\java\\org\\io_programming_\\intermediateproblems\\sortcsv\\employeesalary.csv";

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<String[]> records = new ArrayList<>();
            String[] nextRecord;
            boolean isHeader = true;

            // Read all records into a list
            while ((nextRecord = reader.readNext()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                records.add(nextRecord);
            }

            // Sort records by Salary (descending order)
            records.sort((record1, record2) -> {
                try {
                    double salary1 = Double.parseDouble(record1[3]);
                    double salary2 = Double.parseDouble(record2[3]);
                    return Double.compare(salary2, salary1); // descending order
                } catch (NumberFormatException e) {
                    System.err.println("Invalid salary format for " + String.join(", ", record1));
                    return 0;
                }
            });

            // Print top 5 highest-paid employees
            System.out.println("Top 5 Highest-Paid Employees:");
            System.out.println("--------------------------------");

            for (int i = 0; i < Math.min(5, records.size()); i++) {
                String[] record = records.get(i);
                // Format salary for better readability
                double salary = Double.parseDouble(record[3]);
                System.out.printf("ID: %s | Name: %s | Department: %s | Salary: %.2f%n",
                        record[0], record[1], record[2], salary);
            }

        } catch (IOException | CsvValidationException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
    }
}
