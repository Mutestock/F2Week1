/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

import entities.Address;
import entities.Customer;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Henning
 */
public class tester {

    static EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("pu");
    static EntityManager em = emf.createEntityManager();
    

    public static void main(String[] args) {
        Persistence.generateSchema("pu", null);
        Stream.of(
                new Address("Nymphstreet 9", "Some forest"),
                new Customer("Jake", "Paulson"),
                new Customer("John", "Doe"),
                new Address("Drinkspiker's Avenue", "Balphegore's Mouthwash"),
                new Customer("Cakeface", "McGee")
        )
                .forEach(o -> {
                    em.getTransaction().begin();
                    em.persist(o);
                    em.getTransaction().commit();
                }
                );
    }
}
