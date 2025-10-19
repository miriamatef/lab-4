/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

import java.time.LocalDate; 
//LocalDate is a class in Java used to represent a date without time (only year, month, day)
import java.time.format.DateTimeFormatter;
//DateTimeFormatter is a class that allows you to format and display a LocalDate in a specific pattern

/**
 *
 * @author DELL
 */
public class CustomerProduct {
    private String customerSSN;
    private String productID;
    private LocalDate purchaseDate;
    private boolean paid;

    public CustomerProduct(String customerSSN, String productID, LocalDate purchaseDate) {
        this.customerSSN = customerSSN;
        this.productID = productID;
        this.purchaseDate = purchaseDate;
        this.paid = false;
    }

    public String getCustomerSSN() {
        return customerSSN;
    }

    public void setCustomerSSN(String customerSSN) {
        if (customerSSN == null || !customerSSN.matches("\\d{10}")) {
        throw new IllegalArgumentException("Customer SSN must be 10 digits.");
        }
        this.customerSSN = customerSSN;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        if (productID == null || productID.isEmpty()) {
        throw new IllegalArgumentException("Product ID cannot be empty.");
        }
        this.productID = productID;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        if (purchaseDate == null || purchaseDate.isAfter(LocalDate.now())) {
        throw new IllegalArgumentException("Invalid purchase date.");
        }
        this.purchaseDate = purchaseDate;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
    
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    
    public String lineRepresentation(){
        // returns the data of the object comma separated.
        return customerSSN+","+productID+","+purchaseDate.format(DATE_FORMATTER)+","+paid;
    }
    
    public String getSearchKey() {
        return customerSSN+","+productID+","+purchaseDate.format(DATE_FORMATTER);
    }
}
