package PROJECT;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// Employee class to hold employee details
class Employee {
    private String name;
    private String employeeID;
    private String position;
    private String department;
    private double baseSalary;
    private double overtimepay;
    private double bonuses;
    private double deductions;
    private double taxes;

    // Constructor
    public Employee(String name, String employeeID, String position, String department, double baseSalary, double overtimepay, double bonuses, double deductions, double taxes) {
        this.name = name;
        this.employeeID = employeeID;
        this.position = position;
        this.department = department;
        this.baseSalary = baseSalary;
        this.overtimepay = overtimepay;
        this.bonuses = bonuses;
        this.deductions = deductions;
        this.taxes = taxes;
    }

    // Getter and Setter methods
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmployeeID() { return employeeID; }
    public void setEmployeeID(String employeeID) { this.employeeID = employeeID; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public double getBaseSalary() { return baseSalary; }
    public void setBaseSalary(double baseSalary) { this.baseSalary = baseSalary; }

    public double getOvertimepay() { return overtimepay; }
    public void setOvertimepay(double overtimepay) { this.overtimepay = overtimepay; }

    public double getBonuses() { return bonuses; }
    public void setBonuses(double bonuses) { this.bonuses = bonuses; }

    public double getDeductions() { return deductions; }
    public void setDeductions(double deductions) { this.deductions = deductions; }
    
    public double getTaxes() { return taxes; }
    public void setTaxes(double taxes) { this.taxes = taxes; }
    
    // Salary calculation logic
    public double calculateNetSalary() {
        double overtimePay = overtimepay * (baseSalary / 160) * 1.5; // Assuming 160 hours per month
        double grossSalary = baseSalary + overtimePay + bonuses;
        double calculatedTaxes = grossSalary * 0.15; // Assuming 15% tax
        return grossSalary - calculatedTaxes - deductions;
    }
     
    // Representation of employee details & calculated salary
    @Override
    public String toString() {
        return "Employee ID: " + employeeID + "\nName: " + name + "\nPosition: " + position + 
               "\nDepartment: " + department + "\nBase Salary: $" + baseSalary + 
               "\nOvertime Hours: " + overtimepay + "\nBonuses: $" + bonuses + 
               "\nDeductions: $" + deductions + "\nNet Salary: $" + calculateNetSalary();
    }
}

// Main Payroll System GUI class
public class PayrollSystemGUI extends JFrame {

    // Employee list
    private ArrayList<Employee> employeeList = new ArrayList<>();

    // Constructor to set up the UI
    public PayrollSystemGUI() {
        setTitle("Employee Payroll System");
        setSize(200, 50);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create buttons for UI actions
        JButton addEmployeeButton = new JButton("Add Employee");
        JButton modifyEmployeeButton = new JButton("Modify Employee");
        JButton deleteEmployeeButton = new JButton("Delete Employee");
        JButton generatePayrollButton = new JButton("Generate Payroll");

        // Create a panel to hold buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1, 10, 10));
        buttonPanel.add(addEmployeeButton);
        buttonPanel.add(modifyEmployeeButton);
        buttonPanel.add(deleteEmployeeButton);
        buttonPanel.add(generatePayrollButton);

        // Add panel to the frame
        add(buttonPanel, BorderLayout.CENTER);

        // Display the window
        setVisible(true);

        // Add action listeners to buttons
        addEmployeeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addEmployee();
            }
        });

        modifyEmployeeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modifyEmployee();
            }
        });

        deleteEmployeeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteEmployee();
            }
        });

        generatePayrollButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generatePayroll();
            }
        });
    }

    // Method to add a new employee
    private void addEmployee() {
        // Prompt the user to enter employee details
        String name = JOptionPane.showInputDialog(this, "Enter Employee Name:");
        String employeeID = JOptionPane.showInputDialog(this, "Enter Employee ID:");
        String position = JOptionPane.showInputDialog(this, "Enter Position:");
        String department = JOptionPane.showInputDialog(this, "Enter Department:");
        double baseSalary = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter Base Salary:"));
        double overtimepay = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter Overtime pay:"));
        double bonuses = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter Bonuses:"));
        double deductions = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter Deductions:"));
        double taxes = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter Taxes:"));
        
        // Create new Employee with all parameters
        Employee newEmployee = new Employee(name, employeeID, position, department, baseSalary, overtimepay, bonuses, deductions, taxes);
        
        employeeList.add(newEmployee);
        
        JOptionPane.showMessageDialog(this, "Employee added successfully.");
    }

    // Method to modify employee details
    private void modifyEmployee() {
        String employeeID = JOptionPane.showInputDialog(this, "Enter Employee ID to Modify:");
        Employee employee = findEmployeeByID(employeeID);
        
        if (employee != null) {
            String newName = JOptionPane.showInputDialog(this, "Enter New Name:", employee.getName());
            employee.setName(newName);

            String newPosition = JOptionPane.showInputDialog(this, "Enter New Position:", employee.getPosition());
            employee.setPosition(newPosition);

            String newDepartment = JOptionPane.showInputDialog(this, "Enter New Department:", employee.getDepartment());
            employee.setDepartment(newDepartment);

            double newBaseSalary = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter New Base Salary:", employee.getBaseSalary()));
            employee.setBaseSalary(newBaseSalary);
            
            double newOvertimepay = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter New Overtime pay:", employee.getOvertimepay()));
            employee.setOvertimepay(newOvertimepay);
            
            double newBonuses = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter New Bonuses:", employee.getBonuses()));
            employee.setBonuses(newBonuses);
            
            double newDeductions = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter New Deductions:", employee.getDeductions()));
            employee.setDeductions(newDeductions);
            
            double newTaxes = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter New Taxes:", employee.getTaxes()));
            employee.setTaxes(newTaxes);

            JOptionPane.showMessageDialog(this, "Employee modified successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "Employee not found.");
        }
    }

    // Method to delete an employee
    private void deleteEmployee() {
        String employeeID = JOptionPane.showInputDialog(this, "Enter Employee ID to Delete:");
        Employee employee = findEmployeeByID(employeeID);
        
        if (employee != null) {
            employeeList.remove(employee);
            JOptionPane.showMessageDialog(this, "Employee deleted successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "Employee not found.");
        }
    }

    // Method to generate payroll
    private void generatePayroll() {
        StringBuilder payroll = new StringBuilder();
        
        for (Employee employee : employeeList) {
            payroll.append(employee.toString()).append("\n\n");
        }
        
        JOptionPane.showMessageDialog(this, payroll.toString());
    }

    // Helper method to find employee by ID
    private Employee findEmployeeByID(String employeeID) {
        for (Employee emp : employeeList) {
            if (emp.getEmployeeID().equals(employeeID)) {
                return emp;
            }
        }
        return null;
    }

    // Main method to run the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PayrollSystemGUI();
            }
        });
    }
}

