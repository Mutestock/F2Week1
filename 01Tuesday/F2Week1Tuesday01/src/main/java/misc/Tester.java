package misc;

import entities.Customer;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.scene.paint.Color;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Henning
 */
public class Tester {

    static EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("pu");
    static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {
        Stream.of(
                new Customer("Jake", "Paulson"),
                new Customer("John", "Doe"),
                new Customer("Cakeface", "McGee"))
                .forEach(o -> {
                    o.addHobby("CakeFetching", "derphunting");
                    o.addPhone(Integer.toString(ThreadLocalRandom.current().nextInt(10000000, 99999999)), "lul");
                    em.getTransaction().begin();
                    em.persist(o);
                    em.getTransaction().commit();
                }
                );
    }
}
