/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

import java.util.StringTokenizer;


/**
 *
 * @author carol
 */
public class EmployeeUserDatabase extends Database<EmployeeUser>{
    
    public EmployeeUserDatabase(String filename) {
        super(filename);
    }

    @Override
    public EmployeeUser createRecordFrom(String line){
        String[] parts = line.split(",");
        EmployeeUser emp = new EmployeeUser(parts[0].trim(),parts[1].trim(),parts[2].trim(),parts[3].trim(),parts[4].trim());
        return emp;
    }

    @Override
    public String getKey(EmployeeUser line){
        return line.getId();
    }
}
