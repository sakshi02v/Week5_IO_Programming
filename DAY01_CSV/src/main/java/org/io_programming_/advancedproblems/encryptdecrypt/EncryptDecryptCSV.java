package org.io_programming_.advancedproblems.encryptdecrypt;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class EncryptDecryptCSV {
    public static void main(String[] args) {
        String inputFilePath = "C:\\Users\\hp\\Desktop\\CG Training\\W5\\DAY01_CSV\\src\\main\\java\\org\\io_programming_\\advancedproblems\\encryptdecrypt\\employees.csv"; // Path to input CSV
        String outputFilePath = "C:\\Users\\hp\\Desktop\\CG Training\\W5\\DAY01_CSV\\src\\main\\java\\org\\io_programming_\\advancedproblems\\encryptdecrypt\\employeesencrypt.csv"; // Path to output encrypted CSV
        String decryptedFilePath = "C:\\Users\\hp\\Desktop\\CG Training\\W5\\DAY01_CSV\\src\\main\\java\\org\\io_programming_\\advancedproblems\\encryptdecrypt\\employeesdecrypt.csv"; // Decrypted CSV output

        try {
            // Encrypt data and write to CSV
            encryptCSVData(inputFilePath, outputFilePath);

            // Decrypt data and write to CSV
            decryptCSVData(outputFilePath, decryptedFilePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Encrypt sensitive fields and write to a new CSV
    public static void encryptCSVData(String inputFilePath, String outputFilePath) throws Exception {
        try (CSVReader reader = new CSVReader(new FileReader(inputFilePath));
             CSVWriter writer = new CSVWriter(new FileWriter(outputFilePath))) {

            List<String[]> records = new ArrayList<>();
            String[] nextRecord;

            while ((nextRecord = reader.readNext()) != null) {
                // Encrypt the Salary and Email fields (assuming they are in column 3 and 4)
                nextRecord[3] = EncryptionUtil.encrypt(nextRecord[3]);  // Salary
                nextRecord[4] = EncryptionUtil.encrypt(nextRecord[4]);  // Email

                records.add(nextRecord);
            }

            // Write encrypted data to new CSV file
            writer.writeAll(records);
            System.out.println("Encrypted CSV file created: " + outputFilePath);
        }
    }

    // Decrypt sensitive fields and write to a new CSV
    public static void decryptCSVData(String inputFilePath, String outputFilePath) throws Exception {
        try (CSVReader reader = new CSVReader(new FileReader(inputFilePath));
             CSVWriter writer = new CSVWriter(new FileWriter(outputFilePath))) {

            List<String[]> records = new ArrayList<>();
            String[] nextRecord;

            while ((nextRecord = reader.readNext()) != null) {
                // Decrypt the Salary and Email fields (assuming they are in column 3 and 4)
                nextRecord[3] = EncryptionUtil.decrypt(nextRecord[3]);  // Salary
                nextRecord[4] = EncryptionUtil.decrypt(nextRecord[4]);  // Email

                records.add(nextRecord);
            }

            // Write decrypted data to a new CSV file
            writer.writeAll(records);
            System.out.println("Decrypted CSV file created: " + outputFilePath);
        }
    }
}
