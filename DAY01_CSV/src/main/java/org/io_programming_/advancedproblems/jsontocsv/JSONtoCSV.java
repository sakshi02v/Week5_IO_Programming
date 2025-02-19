package org.io_programming_.advancedproblems.jsontocsv;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JSONtoCSV {
    public static void main(String[] args) {
        String csvFilePath = "C:\\Users\\hp\\Desktop\\CG Training\\W5\\DAY01_CSV\\src\\main\\java\\org\\io_programming_\\advancedproblems\\jsontocsv\\students.csv";
        String jsonFilePath = "C:\\Users\\hp\\Desktop\\CG Training\\W5\\DAY01_CSV\\src\\main\\java\\org\\io_programming_\\advancedproblems\\jsontocsv\\converted.json";

        try {
            // Read the CSV file into a List of Maps
            CSVReader reader = new CSVReader(new FileReader(csvFilePath));
            List<Map<String, String>> students = new ArrayList<>();
            String[] header = reader.readNext(); // Reading the header

            String[] nextRecord;
            while ((nextRecord = reader.readNext()) != null) {
                // Create a map for each record
                Map<String, String> studentMap = new java.util.HashMap<>();
                for (int i = 0; i < header.length; i++) {
                    studentMap.put(header[i], nextRecord[i]);
                }
                students.add(studentMap);
            }

            // Convert the list to JSON and write it to a file
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(jsonFilePath), students);

            System.out.println("JSON file generated successfully!");

        } catch (IOException e) {
            System.err.println("Error processing files: " + e.getMessage());
        }
    }
}
