/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4_final;

/**
 *
 * @author karen
 */
import java.util.ArrayList;

public class AdminRole extends User {

    private EmployeeUserDatabase employeeDatabase;

    // constructor haynafz el User w y3ml employeeFile
    public AdminRole(String name, String email, String address, String phoneNumber, String employeeFile) {
        super(name, email, address, phoneNumber);
        this.employeeDatabase = new EmployeeUserDatabase(employeeFile);
        employeeDatabase.readFromFile(); // Load existing data
    }

    //  Add new employee
    public void addEmployee(String employeeId, String name, String email, String address, String phoneNumber) {
        if (employeeDatabase.contains(employeeId)) {
            System.out.println("Employee with ID " + employeeId + " already exists.");
            return;
        }

        EmployeeUser newEmployee = new EmployeeUser(employeeId, name, email, address, phoneNumber);
        employeeDatabase.insertRecord(newEmployee);
        employeeDatabase.saveToFile();
        System.out.println("Employee added successfully: " + name);
    }

    //  Get all employees
    public EmployeeUser[] getListOfEmployees() {
        ArrayList<EmployeeUser> employees = employeeDatabase.returnAllRecords();
        return employees.toArray(new EmployeeUser[0]);
    }

    //  Remove employee
    public void removeEmployee(String employeeId) {
        if (employeeDatabase.contains(employeeId)) {
            employeeDatabase.deleteRecord(employeeId);
            employeeDatabase.saveToFile();
            System.out.println("Employee with ID " + employeeId + " removed successfully.");
        } else {
            System.out.println("Employee with ID " + employeeId + " not found.");
        }
    }

    @Override
    public void logout() {
        System.out.println("Admin " + name + " logged out.");
    }
}
