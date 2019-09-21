/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author Henning
 */


@NamedQueries({
    @NamedQuery(name = "Customer.read", query = "SELECT c FROM Customer c WHERE c.id = :id"),
    @NamedQuery(name = "Customer.readAll", query = "SELECT c FROM Customer c"),
   // @NamedQuery(name = "Customer.getAllOrders", query = "SELECT o FROM Customer c JOIN c.Orders o WHERE c.customerName  = :customerName"),
    //@NamedQuery(name = "Customer.getAllOrders", query = "SELECT o FROM Orders o WHERE o.customerName =:customerName"),
})

@Entity
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CustomerID")
    private Long id;
    @Column(name="customerName")
    private String name;
    private String email;
    @OneToMany(mappedBy="customer", cascade = CascadeType.ALL)
    private List<Orders> orders;

    public Customer() {
    }

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }
    
    public List<Orders> getOrdersList()
    {
        return orders;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
