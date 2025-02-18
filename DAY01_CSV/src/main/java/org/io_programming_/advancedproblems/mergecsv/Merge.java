package org.io_programming_.advancedproblems.mergecsv;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Merge {
    public static void main(String[] args) {
        String file1 = "C:\\Users\\hp\\Desktop\\CG Training\\W5\\DAY01_CSV\\src\\main\\java\\org\\io_programming_\\advancedproblems\\mergecsv\\file1.csv";  // CSV file containing ID, Name, Age
        String file2 = "C:\\Users\\hp\\Desktop\\CG Training\\W5\\DAY01_CSV\\src\\main\\java\\org\\io_programming_\\advancedproblems\\mergecsv\\file2.csv";  // CSV file containing ID, Marks, Grade
        String outputFile = "merged_students.csv";  // Output file for merged data

        Map<String, String[]> studentsMap = new HashMap<>();

        // Step 1: Read the first CSV file (students1.csv) and store it in a map
        try (CSVReader reader1 = new CSVReader(new FileReader(file1))) {
            String[] nextRecord;
            boolean isHeader1 = true;

            while ((nextRecord = reader1.readNext()) != null) {
                if (isHeader1) {
                    isHeader1 = false; // Skip the header row
                    continue;
                }

                // Store student data from students1.csv in the map
                String id = nextRecord[0];
                studentsMap.put(id, nextRecord);
            }
        } catch (IOException | CsvValidationException e) {
            System.err.println("Error reading first CSV file: " + e.getMessage());
        }

        // Step 2: Read the second CSV file (students2.csv) and merge data
        try (CSVReader reader2 = new CSVReader(new FileReader(file2))) {
            String[] nextRecord;
            boolean isHeader2 = true;

            while ((nextRecord = reader2.readNext()) != null) {
                if (isHeader2) {
                    isHeader2 = false; // Skip the header row
                    continue;
                }

                String id = nextRecord[0];

                // If student exists in the map, merge the data from students2.csv
                if (studentsMap.containsKey(id)) {
                    String[] student1 = studentsMap.get(id);
                    String[] mergedRecord = new String[5];
                    System.arraycopy(student1, 0, mergedRecord, 0, 3); // Copy ID, Name, Age
                    mergedRecord[3] = nextRecord[1]; // Marks
                    mergedRecord[4] = nextRecord[2]; // Grade
                    studentsMap.put(id, mergedRecord); // Update the map with merged data
                }
            }
        } catch (IOException | CsvValidationException e) {
            System.err.println("Error reading second CSV file: " + e.getMessage());
        }

        // Step 3: Write merged data to a new CSV file (merged_students.csv)
        try (CSVWriter writer = new CSVWriter(new FileWriter(outputFile))) {
            // Write header row
            String[] header = {"ID", "Name", "Age", "Marks", "Grade"};
            writer.writeNext(header);

            // Write merged records
            for (String[] record : studentsMap.values()) {
                writer.writeNext(record);
            }

            System.out.println("CSV files merged successfully!");

        } catch (IOException e) {
            System.err.println("Error writing merged CSV file: " + e.getMessage());
        }
    }
}
