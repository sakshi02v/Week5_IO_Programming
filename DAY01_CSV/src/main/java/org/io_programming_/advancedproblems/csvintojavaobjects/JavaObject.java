package org.io_programming_.advancedproblems.csvintojavaobjects;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JavaObject {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\hp\\Desktop\\CG Training\\W5\\DAY01_CSV\\src\\main\\java\\org\\io_programming_\\advancedproblems\\csvintojavaobjects\\students.csv";

        List<Student> studentList = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] nextRecord;
            boolean isHeader = true;

            // Read each record and convert to Student object
            while ((nextRecord = reader.readNext()) != null) {
                if (isHeader) { // Skip header row
                    isHeader = false;
                    continue;
                }

                // Assuming the CSV file has columns: ID, Name, Age, Marks
                String id = nextRecord[0];
                String name = nextRecord[1];
                int age = Integer.parseInt(nextRecord[2]);
                int marks = Integer.parseInt(nextRecord[3]);

                // Create a Student object and add to the list
                Student student = new Student(id, name, age, marks);
                studentList.add(student);
            }

            // Print all students
            System.out.println("Student Details:");
            for (Student student : studentList) {
                System.out.println(student);
            }

        } catch (IOException | CsvValidationException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
    }
}
