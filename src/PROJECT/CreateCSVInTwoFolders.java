package PROJECT;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreateCSVInTwoFolders {

    public static void main(String[] args) {
        String payrollFolderPath = "C:\\Users\\bhagw\\Documents\\payroll";  // Folder for payroll.csv
        String employeeFolderPath = "C:\\Users\\bhagw\\Documents\\Employee";  // Folder for employee.csv
        String importEmployeeFolderPath = "C:\\Users\\bhagw\\Documents\\import_data.csv";  // Folder for importing employee data

        String payrollFilePath = payrollFolderPath + "\\payroll.csv";  // Use "\\" for Windows paths
        String employeeFilePath = employeeFolderPath + "\\employee.csv";  // Use "\\" for Windows paths
        String importEmployeeFilePath = importEmployeeFolderPath + "\\importEmployee.csv"; // Use "\\" for Windows paths
        
        // Create the payroll folder if it doesn't exist
        if (createFolder(payrollFolderPath)) {
            createEmptyCSVFile(payrollFilePath, "Payroll CSV file created successfully.");
        }

        // Create the employee folder if it doesn't exist
        if (createFolder(employeeFolderPath)) {
            createEmptyCSVFile(employeeFilePath, "Employee CSV file created successfully.");
        }

        // Create the import employee folder if it doesn't exist
        if (createFolder(importEmployeeFolderPath)) {
        	 createEmptyCSVFile(importEmployeeFilePath, "importEmployee CSV file created successfully.");
        }
    }

    // Method to create a folder
    public static boolean createFolder(String folderPath) {
        File folder = new File(folderPath);
        if (!folder.exists()) {
            if (folder.mkdirs()) {
                System.out.println("Folder created successfully at: " + folderPath);
                return true;
            } else {
                System.out.println("Failed to create folder at: " + folderPath);
                return false;
            }
        } else {
            System.out.println("Folder already exists at: " + folderPath);
            return true;
        }
    }

    // Method to create an empty CSV file
    public static void createEmptyCSVFile(String filePath, String successMessage) {
        try (FileWriter writer = new FileWriter(filePath)) {
            System.out.println(successMessage);
        } catch (IOException e) {
            System.out.println("An error occurred while creating the CSV file: " + filePath);
            e.printStackTrace();
        }
    }
}
