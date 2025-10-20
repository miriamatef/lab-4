/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package lab4;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
*
* @author Miriam
*/
public class main {
public static void main(String[] args){

Scanner input = new Scanner(System.in);

ProductDatabase productDB = new ProductDatabase("Product.txt");
CustomerProductDatabase customerDB = new CustomerProductDatabase("CustomerProducts.txt");
EmployeeUserDatabase employeeDB = new EmployeeUserDatabase("Employee User Database.txt");

productDB.readFromFile("Product.txt");
customerDB.readFromFile();
employeeDB.readFromFile("Employee User Database.txt");

//Admin and Employee setup examples
AdminRole admin = new AdminRole("Ali", "Ali@store.com", "Alexandria", "01055556666","Employees.txt");
EmployeeRole employee = new EmployeeRole("Mazen", "mazen@store.com", "Cairo", "01077778888","Product.txt","CustomerProducts.txt");

DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

boolean continueProgram = true;
while (continueProgram) {
System.out.print("Enter your choice: ");
System.out.println("\n========== MAIN MENU ==========");
System.out.println("1. Admin Login");
System.out.println("2. Employee Login");
System.out.println("3. Manage EmployeeUser Database");
System.out.println("4. Exit");
int mainChoice = input.nextInt();
input.nextLine();

switch (mainChoice) {

// ===================== ADMIN MENU =====================
case 1:
int adminChoice;
do {
System.out.println("\n--- ADMIN MENU ---");
System.out.println("1. Add Employee (by name)");
System.out.println("2. Manage Products");
System.out.println("3. Manage Customer Records");
System.out.println("4. Save All");
System.out.println("5. Logout");
System.out.print("Enter your choice: ");
adminChoice = input.nextInt();
input.nextLine();

switch (adminChoice) {
case 1:
System.out.print("Enter employee name to add: ");
String line = input.nextLine();
   if (line == null || line.trim().isEmpty()) {
        System.out.println("Empty or null line");
    }
        String[] parts = line.split(",");
    if (parts.length < 5) {
            System.out.println("Invalid line format: " + line);
    }
admin.addEmployee(parts[0].trim(),parts[1].trim(),parts[2].trim(),parts[3].trim(),parts[4].trim());
break;

// === Product Management ===
case 2:
boolean continueProduct = true;
while (continueProduct) {
System.out.print("Enter your choice: ");
System.out.println("\n--- PRODUCT MANAGEMENT ---");
System.out.println("1. View all products");
System.out.println("2. Add new product");
System.out.println("3. Sell a product");
System.out.println("4. Return a product");
System.out.println("5. Save products");
System.out.println("6. Back");
int pChoice = input.nextInt();
input.nextLine();

switch (pChoice) {
case 1:
for (Product p : productDB.returnAllRecords()) {
System.out.println(p.lineRepresentation());
}
break;

case 3:
System.out.print("Enter Product ID (ex:P1001): ");
String id;
while (true) {
id = input.nextLine().trim();
if (!id.matches("^P\\d+$")) {
System.out.println("Invalid ID format. Must start with 'P' followed by digits.");
continue;
}
if (productDB.contains(id)) {
System.out.println("Product ID already exists. Try again.");
continue;
}
break;
}
System.out.print("Enter Product Name: ");
String name = input.nextLine();
System.out.print("Enter Manufacturer: ");
String manufacturer = input.nextLine();
System.out.print("Enter Supplier: ");
String supplier = input.nextLine();
System.out.print("Enter Quantity: ");
int qty = input.nextInt();
System.out.print("Enter Price: ");
float price = input.nextFloat();
Product newP = new Product(id, name, manufacturer, supplier, qty, price);
productDB.insertRecord(newP);
System.out.println("Product added successfully!");
break;

case 4:
System.out.print("Enter Product ID to sell: ");
String sid = input.nextLine();
Product ps = productDB.getRecord(sid);
if (ps != null) ps.decreaseQuantity();
else System.out.println("Product not found!");
break;

case 5:
System.out.print("Enter Product ID to return: ");
String rid = input.nextLine();
Product pr = productDB.getRecord(rid);
if (pr != null) pr.increaseQuantity();
else System.out.println("Product not found!");
break;

case 6:
productDB.saveToFile();
System.out.println("Products saved to Product.txt");
break;

case 7:
continueProduct = false;
break;

default:
System.out.println("Invalid choice.");
}
}
break;

// === Customer Product Management ===
case 4:
boolean continueCustomer = true;
while (continueCustomer) {
System.out.print("Enter your choice: ");
System.out.println("\n--- CUSTOMER PRODUCT DATABASE ---");
System.out.println("1. View all records");
System.out.println("2. Add new record");
System.out.println("3. Search record");
System.out.println("4. Delete record");
System.out.println("5. Save records");
System.out.println("6. Back");

int cChoice = input.nextInt();
input.nextLine();

switch (cChoice) {
case 1:
for (CustomerProduct cp : customerDB.returnAllRecords()) {
System.out.println(cp.lineRepresentation());
}
break;

case 2:
System.out.print("Enter SSN (10 digits): ");
String ssn = input.nextLine().trim();
while (!ssn.matches("\\d{10}")) {
System.out.print("Invalid SSN! Try again: ");
ssn = input.nextLine().trim();
}

System.out.print("Enter Product ID: ");
String pid = input.nextLine().trim();
while (!pid.matches("P\\d+")) {
System.out.print("Invalid Product ID! Try again: ");
pid = input.nextLine().trim();
}

System.out.print("Enter Purchase Date (dd-MM-yyyy): ");
LocalDate date = null;
while (date == null) {
String dateInput = input.nextLine().trim();
try {
date = LocalDate.parse(dateInput, FORMATTER);
} catch (DateTimeParseException e) {
System.out.print("Invalid date! Try again: ");
}
}

System.out.print("Has the customer paid? (true/false): ");
boolean paid = Boolean.parseBoolean(input.nextLine().trim());

CustomerProduct newCP = new CustomerProduct(ssn, pid, date);
newCP.setPaid(paid);
customerDB.insertRecord(newCP);
System.out.println("Record added successfully!");
break;

case 3:
System.out.print("Enter SSN: ");
String s1 = input.nextLine();
System.out.print("Enter Product ID: ");
String s2 = input.nextLine();
System.out.print("Enter Date (dd-MM-yyyy): ");
String s3 = input.nextLine();
String key = s1 + "," + s2 + "," + s3;
CustomerProduct found = customerDB.getRecord(key);
if (found != null)
System.out.println("Found: " + found.lineRepresentation());
else
System.out.println("Not found!");
break;

case 4:
System.out.print("Enter key (SSN,ProductID,Date): ");
String delKey = input.nextLine();
if (customerDB.contains(delKey)) {
customerDB.deleteRecord(delKey);
System.out.println("Deleted successfully!");
} else System.out.println("Record not found!");
break;

case 5:
customerDB.saveToFile();
System.out.println("Customer records saved to CustomerProducts.txt");
break;

case 6:
continueCustomer = false;
break;

default:
System.out.println("Invalid choice.");
}
}
break;

// === Save all data ===
case 5:
productDB.saveToFile();
customerDB.saveToFile();
employeeDB.saveToFile();
System.out.println("All data saved successfully.");
break;

case 6:
admin.logout();
break;

default:
System.out.println("Invalid choice.");
}
} while (adminChoice != 6);
break;

// ===================== EMPLOYEE MENU =====================
case 2:
int empChoice;
do {
System.out.print("Enter your choice: ");
System.out.println("\n--- EMPLOYEE MENU ---");
System.out.println("1. Get list of products");
System.out.println("2. Logout");

empChoice = input.nextInt();
input.nextLine();

switch (empChoice) {
case 1:
employee.getListOfProducts();
break;
case 2:
employee.logout();
break;
default:
System.out.println("Invalid choice.");
}
} while (empChoice != 3);
break;

// ===================== EMPLOYEE USER DATABASE =====================
case 3:
ArrayList<EmployeeUser> empRecs;
boolean continueEmpDB = true;
while (continueEmpDB) {
System.out.print("Enter choice: ");
System.out.println("\n--- EMPLOYEE USER DATABASE ---");
System.out.println("1. Contains");
System.out.println("2. Delete");
System.out.println("3. Insert");
System.out.println("4. Get Record");
System.out.println("5. Return All");
System.out.println("6. Save");
System.out.println("7. Back");

int x = input.nextInt();
input.nextLine();

switch (x) {
case 1:
System.out.print("Enter ID: ");
String n = input.nextLine();
System.out.println(employeeDB.contains(n));
break;

case 2:
System.out.print("Enter ID to delete: ");
n = input.nextLine();
employeeDB.deleteRecord(n);
break;

case 3:
System.out.println("1: Insert by line \n2: Insert manually");
int z = input.nextInt();
input.nextLine();
if (z == 1) {
System.out.print("Enter line: ");
String line = input.nextLine();
employeeDB.insertRecord(employeeDB.createRecordFrom(line));
} else {
System.out.print("Enter ID: ");
String id = input.nextLine();
System.out.print("Enter Name: ");
String name = input.nextLine();
System.out.print("Enter Email: ");
String email = input.nextLine();
System.out.print("Enter Address: ");
String address = input.nextLine();
System.out.print("Enter Number: ");
String number = input.nextLine();
EmployeeUser emp1 = new EmployeeUser(id, name, email, address, number);
employeeDB.insertRecord(emp1);
}
break;

case 4:
System.out.print("Enter ID: ");
n = input.nextLine();
EmployeeUser emp2 = employeeDB.getRecord(n);
if (emp2 != null)
System.out.println("Found: " + emp2.getId());
else
System.out.println("Record not found.");
break;

case 5:
empRecs = employeeDB.returnAllRecords();
for (int i = 0; i < empRecs.size(); i++)
System.out.println(empRecs.get(i));
break;

case 6:
employeeDB.saveToFile();
System.out.println("EmployeeUser records are saved to lab4.txt");
break;

case 7:
continueEmpDB = false;
break;

default:
System.out.println("Invalid!");
}
}
break;

// ===================== EXIT =====================
case 4:
productDB.saveToFile();
customerDB.saveToFile();
employeeDB.saveToFile();
System.out.println("\n All data are saved successfully. Exiting program.");
continueProgram = false;
break;

default:
System.out.println("Invalid choice.");
}
}
input.close();
}
}
