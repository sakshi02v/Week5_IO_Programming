package org.io_programming_.advancedproblems.csvfromduplicate;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class GenerateCSV{
    public static void main(String[] args) {
        // Database connection details
        String jdbcUrl = "";
        String username = "your_username";
        String password = "your_password";
        // SQL query to fetch employee records
        String query = "SELECT employee_id, name, department, salary FROM employees"; // Adjust the table name and column names

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
             CSVWriter csvWriter = new CSVWriter(new FileWriter("employees_report.csv"))) {

            // Writing headers to the CSV file
            String[] headers = {"Employee ID", "Name", "Department", "Salary"};
            csvWriter.writeNext(headers);

            // Fetching and writing each record to CSV
            while (resultSet.next()) {
                String employeeId = resultSet.getString("employee_id");
                String name = resultSet.getString("name");
                String department = resultSet.getString("department");
                String salary = resultSet.getString("salary");

                // Writing employee data to CSV
                String[] employeeData = {employeeId, name, department, salary};
                csvWriter.writeNext(employeeData);
            }

            System.out.println("CSV report generated successfully!");

        } catch (SQLException | IOException e) {
            System.err.println("Error occurred: " + e.getMessage());
        }
    }
}
