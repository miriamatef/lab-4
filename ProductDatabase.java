/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

/**
 *
 * @author Mariam Elshamy
 */
public class ProductDatabase extends Database<Product> {
    public ProductDatabase(String filename) {
        super(filename);
    }

    @Override
    public Product createRecordFrom(String line) {
        String[] data = line.split(",");
        return new Product(
            data[0].trim(),
            data[1].trim(),
            data[2].trim(),
            data[3].trim(),
            Integer.parseInt(data[4].trim()),
            Float.parseFloat(data[5].trim())
        );
    }

    @Override
    public String getKey(Product p) {
        return p.getSearchKey();
    }
}


