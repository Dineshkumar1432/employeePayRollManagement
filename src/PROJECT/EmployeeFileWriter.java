package PROJECT;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class EmployeeFileWriter {

    public void writeEmployeeData(List<Employee> employeeList, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            // Write CSV header
            writer.append("Name,EmpID,Position,Department,BaseSalary,OvertimeHours,Bonuses,Deductions,Taxes\n");

            // Write each employee's data
            for (Employee emp : employeeList) {
                writer.append(emp.getName()).append(",");
                writer.append(emp.getEmployeeID()).append(",");
                writer.append(emp.getPosition()).append(",");
                writer.append(emp.getDepartment()).append(",");
                writer.append(String.valueOf(emp.getBaseSalary())).append(",");
                writer.append(String.valueOf(emp.getOvertimepay())).append(",");
                writer.append(String.valueOf(emp.getBonuses())).append(",");
                writer.append(String.valueOf(emp.getDeductions())).append(",");
                writer.append(String.valueOf(emp.getTaxes())).append("\n");
            }

            writer.flush();
            System.out.println("Employee data has been written to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Create some example employee data
        Employee emp1 = new Employee("John Doe", "E001", "Tester", "IT", 50000, 5, 1000, 500, 200);
        Employee emp2 = new Employee("Jane Smith", "E002", "Analyat", "IT", 60000, 8, 1500, 700, 200);

        List<Employee> employeeList = List.of(emp1, emp2);

        // Write to file
        EmployeeFileWriter writer = new EmployeeFileWriter();
        writer.writeEmployeeData(employeeList, "output_employees.csv");
    }
}
