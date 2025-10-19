/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;
import java.util.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author DELL
 */
public class CustomerProductDatabase extends Database<CustomerProduct>{
    
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    
    public CustomerProductDatabase(String filename){
        super(filename);
    }
    
    public void readFromFile(){
        super.readFromFile("CustomerProducts.txt");
    }
    
    @Override
    public CustomerProduct createRecordFrom(String line){
        String[] parts = line.split(",");
        if (parts.length<3){
            System.out.println("Invalid record: "+line);
            return null;
        }
        try{
            String customerSSN = parts[0].trim();
            String productID = parts[1].trim();
            LocalDate purchaseDate = LocalDate.parse(parts[2].trim(), FORMATTER);
            CustomerProduct cp = new CustomerProduct (customerSSN, productID, purchaseDate);
            if (parts.length>=4){
                String paidCondition = parts[3].trim().toLowerCase();
                if (paidCondition.equals("true"))
                    cp.setPaid(true);
                else if (paidCondition.equals("false"))
                    cp.setPaid(false);
                else{
                    System.out.println("Invalid paid value, it would be set to false automatically");
                    cp.setPaid(false);
                }
            }
            else
                cp.setPaid(false);

            return cp;
        }catch(Exception e){
            System.out.println("Error printing record: "+line);
            return null;
        }
    }
    
    public ArrayList<CustomerProduct> returnAllRecords(){
        return super.returnAllRecords();
    }
    
    public boolean contains(String key ){
        return super.contains(key);
    }
    
    public CustomerProduct getRecord(String key){
        return super.getRecord(key);
    }
    
    public void insertRecord(CustomerProduct record){
        super.insertRecord(record);
    }
    
    public void deleteRecord(String key){
        super.deleteRecord(key);
    }
    
    @Override
    public void saveToFile(){
        try(FileWriter w=new FileWriter("CustomersProducts.txt")){
            ArrayList<CustomerProduct>records = returnAllRecords();
            for (CustomerProduct cp : records) {
                w.write(cp.lineRepresentation() + "\n");
            }
        }catch (IOException ex){
            System.out.println("Error writing file: " +ex.getMessage());
        }
    }
    
    @Override
    public String getKey(CustomerProduct t){
        return t.getSearchKey();
    }
}
