/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import entities.OrderLine;
import javax.persistence.CascadeType;

/**
 *
 * @author Henning
 */



@Entity
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderID")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerName", referencedColumnName = "customerName")
    private Customer customer;
    @OneToMany(mappedBy = "order", targetEntity = OrderLine.class, cascade = CascadeType.PERSIST)
    private List<OrderLine> orderLines;
    
    public Orders() {
    }

   // public Orders(Long id) {
   //     this.id = id;
   // }

    public Orders(Customer customer, List<OrderLine> orderLines) {
        this.customer = customer;
        this.orderLines = orderLines;
    }
    
    
    
    public Orders(Customer c)
    {
        this.customer = c;
    }

    public Long getOrderID() {
        return id;
    }

    public void setOrderID(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
