package org.io_programming_.basicproblems.writecsvfile;


import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CSVwriter {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\hp\\Desktop\\CG Training\\W5\\DAY01_CSV\\src\\main\\java\\org\\io_programming_\\basicproblems\\writecsvfile\\employees"; // CSV file to write data

        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            // Writing header
            String[] header = {"ID", "Name", "Department", "Salary"};
            writer.writeNext(header);

            // Writing employee records
            String[][] employees = {
                    {"101", "Alice Johnson", "HR", "50000"},
                    {"102", "Bob Smith", "IT", "65000"},
                    {"103", "Charlie Brown", "Finance", "70000"},
                    {"104", "David Lee", "Marketing", "55000"},
                    {"105", "Eve Clark", "Sales", "60000"}
                    };

            for (String[] employee : employees) {
                writer.writeNext(employee);
            }

            System.out.println("CSV file created successfully!");

        } catch (IOException e) {
            System.err.println("Error writing CSV file: " + e.getMessage());
        }
    }
}


