/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4_final;

/**
 *
 * @author Mariam Elshamy
 */
public class Product {
      private String productID;
    private String productName;
    private String manufacturerName;
    private String supplierName;
    private int quantity;
    private float price;
    public Product(String productID, String productName, String manufacturerName, String
    supplierName, int quantity, float price){
        this.productID = productID;
        this.productName = productName;
        this.manufacturerName = manufacturerName;
        this.supplierName = supplierName;
        this.quantity = quantity;
        this.price = price;
    }
    public int getQuantity(){
         return quantity;
     }
   public void setQuantity(int quantity) {
        if (quantity < 0) {
            System.out.println(" Quantity cannot be negative");
        } else {
            this.quantity = quantity;
        }
    }
   public void increaseQuantity() {
        quantity++;
        System.out.println("One unit returned. Stock now:" + quantity);
    }
   public boolean decreaseQuantity() {
        if (quantity > 0) {
            quantity--;
            System.out.println("One unit sold. Remaining stock: " + quantity);
            if (quantity == 0) {
                System.out.println("️Product " + productID + " is now out of stock!");
            }
            return true;
        } else {
            System.out.println("Cannot sell product " + productID + ": out of stock.");
            return false;
        }
    }
    public String getSearchKey(){
        return productID;
    }
    public String lineRepresentation(){ //returns the data ready to be saved in the file
          return productID + "," + productName + "," + manufacturerName + "," +  
               supplierName + "," + quantity + "," + price;
    }
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    public void setproductname(String productName){
    this.productName = productName;
}
     public void setmanufname(String manName){
    this.manufacturerName = manName;
}
     public String getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }
    
    public String getManufacturerName() {
        return manufacturerName;
    }

    public String getSupplierName() {
        return supplierName;
    }
    @Override
    public String toString() {
    return lineRepresentation() + System.lineSeparator();
}
}
