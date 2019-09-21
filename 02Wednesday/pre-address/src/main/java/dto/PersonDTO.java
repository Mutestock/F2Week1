/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Person;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Henning
 */
public class PersonDTO {
   
    private int id;
    private String firstName;
    private String lastName;
    private String phone;

    public PersonDTO() {
    }
    
    public PersonDTO(Person p) {
        this.firstName = p.getfName();
        this.lastName = p.getlName();
        this.phone = p.getPhone();
        this.id = p.getId();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
