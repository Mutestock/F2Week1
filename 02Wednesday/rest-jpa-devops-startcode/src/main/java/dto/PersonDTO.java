/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Address;
import entities.Person;
import java.util.Date;
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
    private Date lastEdited;
    private Date created;
    private Address address;
    private String city;
    private String street;
    private int zip;

    public PersonDTO() {
    }

    public PersonDTO(Person p) {
        this.firstName = p.getfName();
        this.lastName = p.getlName();
        this.phone = p.getPhone();
        this.lastEdited = p.getLastEdited();
        this.created = p.getCreated();
        this.id = p.getId();
        this.zip = p.getAddress().getZip();
        this.city = p.getAddress().getCity();
        this.street = p.getAddress().getStreet();

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String City) {
        this.city = City;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String Street) {
        this.street = Street;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public Date getLastEdited() {
        return lastEdited;
    }

    public void setLastEdited(Date lastEdited) {
        this.lastEdited = lastEdited;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

}
