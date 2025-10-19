/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author carol
 */
public abstract class Database<T>{
    private String filename;
    private ArrayList <T> records = new ArrayList<>();
   
    public Database(String filename){
        this.filename=filename;
    }
    public void readFromFile(String filename){
        String line;
        T t;
        try(BufferedReader br=new BufferedReader(new FileReader(filename))){
            while((line=br.readLine())!=null){
                if (line.trim().isEmpty()) continue;
                t=createRecordFrom(line);
                records.add(t);
            }
            br.close();
        }catch (IOException ex) {
            System.out.println("Error reading file: " + ex.getMessage());
        }
    }
    public abstract T createRecordFrom(String line);
    public ArrayList<T> returnAllRecords(){
        return records;
    }
    public abstract String getKey(T t);
    public boolean contains(String key){
        int i;
        T line;
        for(i=0;i<records.size();i++){
           line=records.get(i);
           if(key.equals(getKey(line))){
               return true;
           }
        }
        return false;
    }
    public T getRecord(String key){
        int i;
        T line;
        for(i=0;i<records.size();i++){
           line=records.get(i);
           if(key.equals(getKey(line))){
               return line;
           }
        }
        return null;
    }
    public void insertRecord(T record){
        records.add(record);   
    }
    public void deleteRecord(String key){
        int i;
        T line;
        for(i=0;i<records.size();i++){
           line=records.get(i);
           if(key.equals(getKey(line))){
               records.remove(i);
           }
        }
    }
    public void saveToFile(){
        try{
            FileWriter w = new FileWriter(filename);
            int i;
            for(i=0;i<records.size();i++){
                w.write(records.get(i).toString());
            }
            w.close();
        }catch(IOException ex){
            System.out.println("Error writing in this file: " + ex.getMessage());
        }
        
        
    }    
}
