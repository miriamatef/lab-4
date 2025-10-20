/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

/**
 *
 * @author carol
 */
class EmployeeUser extends User{
    private String id;
    public EmployeeUser(String id,String name, String email, String address, String phoneNumber){
        this.id= id;
    }

    public String getId() {
        return id;
    }
    
}
