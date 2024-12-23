package PROJECT;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileManager {

    private EmployeeDAO employeeDAO;

    // Constructor accepting EmployeeDAO
    public FileManager(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    // Method to import employees from a CSV file
    public void importEmployeesFromCSV(File file) {
        String line;
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        	br.readLine();
            while ((line = br.readLine()) != null) {
                // Split line by comma to extract employee data
                String[] employeeData = line.split(csvSplitBy);

                // Ensure the data array has the correct number of elements before parsing
                if (employeeData.length == 9) {
                    // Create a new Employee object from the CSV data
                    Employee employee = new Employee(
                        employeeData[0], // employeeID
                        employeeData[1], // name
                        employeeData[2], // position
                        employeeData[3], // department
                        Double.parseDouble(employeeData[4]), // baseSalary
                        Double.parseDouble(employeeData[5]), // overtimepay
                        Double.parseDouble(employeeData[6]), // bonuses
                        Double.parseDouble(employeeData[7]), // deductions
                        Double.parseDouble(employeeData[8])  // taxes
                    );

                    // Add the employee to the database using EmployeeDAO
                    employeeDAO.addEmployee(employee);
                } else {
                    System.out.println("Invalid data format in CSV line: " + line);
                }
            }
            System.out.println("Employees imported successfully from CSV.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
    
    // Method to export employees to a CSV file
    public void exportEmployeesToCSV(String filePath) {
        List<Employee> employees = employeeDAO.getAllEmployees(); // Use EmployeeDAO to get all employees
        File file = new File(filePath);
        
        //check if file can be written to
        if(!file.exists() || !file.canWrite()) {
        	System.out.println("Error: Unable to write to the specified file path.");
        	return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            // Write CSV header
            writer.write("EmployeeID,Name,Position,Department,BaseSalary,OvertimePay,Bonuses,Deductions,Taxes");
            writer.newLine();

            // Write employee data
            for (Employee employee : employees) {
                writer.write(employee.getEmployeeID() + "," + employee.getName() + "," + employee.getPosition() + "," +
                             employee.getDepartment() + "," + employee.getBaseSalary() + "," +
                             employee.getOvertimepay() + "," + employee.getBonuses() + "," +
                             employee.getDeductions() + "," + employee.getTaxes());
                writer.newLine();
            }
            System.out.println("Employees exported successfully to CSV.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to export payroll (including calculated net salary) to a CSV file
    public void exportPayrollToCSV(String filePath) {
        List<Employee> employees = employeeDAO.getAllEmployees(); // Use EmployeeDAO to get all employees

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Write CSV header
            writer.write("EmployeeID,Name,BaseSalary,OvertimePay,Bonuses,Deductions,Taxes,NetSalary");
            writer.newLine();

            // Write employee payroll data
            for (Employee employee : employees) {
                // Calculate gross and net salary
                double grossSalary = employee.getBaseSalary() + employee.getOvertimepay() + employee.getBonuses();
                double netSalary = grossSalary - (employee.getDeductions() + employee.getTaxes());

                writer.write(employee.getEmployeeID() + "," + employee.getName() + "," + employee.getBaseSalary() + "," +
                             employee.getOvertimepay() + "," + employee.getBonuses() + "," +
                             employee.getDeductions() + "," + employee.getTaxes() + "," + netSalary);
                writer.newLine();
            }
            System.out.println("Payroll exported successfully to CSV.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
