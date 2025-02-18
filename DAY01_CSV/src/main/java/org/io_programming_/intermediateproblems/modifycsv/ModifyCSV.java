package org.io_programming_.intermediateproblems.modifycsv;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ModifyCSV {
    public static void main(String[] args) {
        String inputFilePath = "C:\\Users\\hp\\Desktop\\CG Training\\W5\\DAY01_CSV\\src\\main\\java\\org\\io_programming_\\intermediateproblems\\modifycsv\\employees.csv";
        String outputFilePath = "C:\\Users\\hp\\Desktop\\CG Training\\W5\\DAY01_CSV\\src\\main\\java\\org\\io_programming_\\intermediateproblems\\modifycsv\\employees_updated.csv";

        try (CSVReader reader = new CSVReader(new FileReader(inputFilePath));
             CSVWriter writer = new CSVWriter(new FileWriter(outputFilePath))) {

            String[] nextRecord;
            boolean isHeader = true;

            while ((nextRecord = reader.readNext()) != null) {
                // Writing header without any modification
                if (isHeader) {
                    writer.writeNext(nextRecord);
                    isHeader = false;
                    continue;
                }

                // Check if the department is "IT"
                if (nextRecord.length >= 4 && nextRecord[2].equalsIgnoreCase("IT")) {
                    try {
                        // Increase the salary by 10%
                        double salary = Double.parseDouble(nextRecord[3]);
                        salary += salary * 0.10; // Increase salary by 10%
                        nextRecord[3] = String.valueOf(salary);
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid salary format for " + nextRecord[1]);
                    }
                }

                // Write the updated record to the new CSV file
                writer.writeNext(nextRecord);
            }

            System.out.println("Salary update complete. Updated records saved to: " + outputFilePath);

        } catch (IOException | CsvValidationException e) {
            System.err.println("Error processing the CSV file: " + e.getMessage());
        }
    }
}
