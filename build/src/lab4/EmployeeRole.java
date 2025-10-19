/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

/**
 *
 * @author karen
 */
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class EmployeeRole extends User{

    private ProductDatabase productsDatabase;
    private CustomerProductDatabase customerProductDatabase;

    //constructor bya5od mn User w yzawed productFile w customerProductFile
    public EmployeeRole(String name, String email, String address, String phoneNumber,
                        String productFile, String customerProductFile) {
        super(name, email, address, phoneNumber);
        this.productsDatabase = new ProductDatabase(productFile);
        this.customerProductDatabase = new CustomerProductDatabase(customerProductFile);
        productsDatabase.readFromFile("Products.txt");
        customerProductDatabase.readFromFile("CustomerProducts.txt");
    }

    //  Add product
    public void addProduct(String productID, String productName, String manufacturerName,
                           String supplierName, int quantity, float price) {
        if (productsDatabase.contains(productID)) {
            System.out.println("Product with ID " + productID + " already exists.");
            return;
        }
        Product newProduct = new Product(productID, productName, manufacturerName, supplierName, quantity, price);
        productsDatabase.insertRecord(newProduct);
        productsDatabase.saveToFile();
        System.out.println("Product added successfully: " + productName);
    }

    //  List all products
    public Product[] getListOfProducts() {
        ArrayList<Product> products = productsDatabase.returnAllRecords();
        return products.toArray(new Product[0]);
    }

    //  List all customer operations
    public CustomerProduct[] getListOfPurchasingOperations() {
        ArrayList<CustomerProduct> operations = customerProductDatabase.returnAllRecords();
        return operations.toArray(new CustomerProduct[0]);
    }

    //  Purchase a product
    public boolean purchaseProduct(String customerSSN, String productID, LocalDate purchaseDate) {
        if (!productsDatabase.contains(productID)) {
            System.out.println("Product not found!");
            return false;
        }

        Product product = productsDatabase.getRecord(productID);
        if (product.getQuantity() <= 0) {
            System.out.println("Product out of stock!");
            return false;
        }

        // Update product quantity
        product.setQuantity(product.getQuantity() - 1);
        productsDatabase.saveToFile();

        // Record purchase
        CustomerProduct purchase = new CustomerProduct(customerSSN, productID, purchaseDate);
        customerProductDatabase.insertRecord(purchase);
        customerProductDatabase.saveToFile();

        System.out.println("Purchase completed successfully.");
        return true;
    }

    //  Handle product return and refund logic
    public double returnProduct(String customerSSN, String productID,
                                LocalDate purchaseDate, LocalDate returnDate) {
        String key = customerSSN + "," + productID + "," + purchaseDate.toString();

        if (!customerProductDatabase.contains(key)) {
            System.out.println("Purchase not found!");
            return 0.0;
        }

        CustomerProduct record = customerProductDatabase.getRecord(key);
        Product product = productsDatabase.getRecord(productID);

        long daysBetween = ChronoUnit.DAYS.between(purchaseDate, returnDate);
        double refundAmount = product.getPrice();

        if (daysBetween > 15) {
            refundAmount *= 0.5; // 50% refund after 15 days
        }

        // byraga3 el product tani
        product.setQuantity(product.getQuantity() + 1);
        productsDatabase.saveToFile();

        // byshelha mn el sold
        customerProductDatabase.deleteRecord(key);
        customerProductDatabase.saveToFile();

        System.out.println("Return processed. Refund amount: " + refundAmount);
        return refundAmount;
    }

    //  Apply payment
    public boolean applyPayment(String customerSSN, LocalDate purchaseDate) {
        ArrayList<CustomerProduct> purchases = customerProductDatabase.returnAllRecords();
        boolean paid = false;

        for (CustomerProduct p : purchases) {
            if (p.getCustomerSSN().equals(customerSSN) && p.getPurchaseDate().equals(purchaseDate)) {
                p.setPaid(true);
                paid = true;
                System.out.println("Payment applied for: " + p.getProductID());
            }
        }

        if (paid) {
            customerProductDatabase.saveToFile();
        } else {
            System.out.println("No matching unpaid purchase found.");
        }

        return paid;
    }
    EmployeeUser emp;
    @Override
    public void logout() {
        System.out.println("Employee " + emp.getName() + " logged out.");
    }
}
