package org.io_programming_.advancedproblems.validatecsv;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateCSV {

    // Regex for email validation
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    // Regex for phone number validation (10 digits)
    private static final String PHONE_REGEX = "^\\d{10}$";
    private static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);

    public static void main(String[] args) {
        String filePath = "C:\\Users\\hp\\Desktop\\CG Training\\W5\\DAY01_CSV\\src\\main\\java\\org\\io_programming_\\advancedproblems\\validatecsv\\data.csv";
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] nextRecord;
            boolean isHeader = true;

            while ((nextRecord = reader.readNext()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                // Ensure the record has at least 5 columns: ID, Name, Email, Phone Number, Salary
                if (nextRecord.length < 5) {
                    System.err.println("Skipping malformed row (too few columns): " + String.join(", ", nextRecord));
                    continue;
                }

                // Validate Email
                String email = nextRecord[2]; // Assuming email is in the 3rd column
                if (!isValidEmail(email)) {
                    System.err.println("Invalid email format: " + email + " in row: " + String.join(", ", nextRecord));
                    continue;
                }

                // Validate Phone Number
                String phoneNumber = nextRecord[3]; // Assuming phone number is in the 4th column
                if (!isValidPhoneNumber(phoneNumber)) {
                    System.err.println("Invalid phone number format: " + phoneNumber + " in row: " + String.join(", ", nextRecord));
                    continue;
                }

                // If the row is valid, process further (e.g., print the row or save to another file)
                System.out.printf("Valid Row: %s%n", String.join(", ", nextRecord));
            }

        } catch (IOException | CsvValidationException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
    }

    // Validate email using regex
    private static boolean isValidEmail(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    // Validate phone number using regex (should have exactly 10 digits)
    private static boolean isValidPhoneNumber(String phoneNumber) {
        Matcher matcher = PHONE_PATTERN.matcher(phoneNumber);
        return matcher.matches();
    }
}
