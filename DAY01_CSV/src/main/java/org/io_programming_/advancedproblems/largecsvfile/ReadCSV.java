package org.io_programming_.advancedproblems.largecsvfile;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadCSV {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\hp\\Desktop\\CG Training\\W5\\DAY01_CSV\\src\\main\\java\\org\\io_programming_\\advancedproblems\\largecsvfile\\csvfiles.csv";  // Path to the large CSV file
        int batchSize = 100;  // Number of records to process at a time
        int processedRecords = 0;  // Counter for processed records

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] nextRecord;
            List<String[]> batch = new ArrayList<>(batchSize);  // List to store records in a batch

            while ((nextRecord = reader.readNext()) != null) {
                batch.add(nextRecord);  // Add the record to the batch

                // When we have collected 100 records, process the batch
                if (batch.size() == batchSize) {
                    processBatch(batch);  // Process the batch (this is where your logic goes)
                    processedRecords += batch.size();  // Increment count of processed records
                    System.out.println("Processed " + processedRecords + " records so far.");

                    // Clear the batch for the next set of records
                    batch.clear();
                }
            }

            // Process any remaining records in the batch (if they are fewer than the batch size)
            if (!batch.isEmpty()) {
                processBatch(batch);
                processedRecords += batch.size();
                System.out.println("Processed " + processedRecords + " records in total.");
            }

        } catch (IOException | CsvValidationException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
    }

    // This method simulates processing the batch. You can replace it with your own logic.
    private static void processBatch(List<String[]> batch) {
        // Example processing: just printing the records in the batch
        for (String[] record : batch) {
            System.out.println("ID: " + record[0] + ", Name: " + record[1] + ", Age: " + record[2]);
        }
    }
}
