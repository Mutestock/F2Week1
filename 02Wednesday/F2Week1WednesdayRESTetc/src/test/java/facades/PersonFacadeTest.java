/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Person;
import exceptions.PersonNotFoundException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;
import utils.EMF_Creator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Settings;
import utils.EMF_Creator.DbSelector;
import org.junit.jupiter.api.Disabled;
import utils.EMF_Creator.Strategy;

/**
 *
 * @author Henning
 */
public class PersonFacadeTest {

    private static EntityManagerFactory emf;
    private static PersonFacade facade;

    public PersonFacadeTest() {
    }

    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/F2Week1DB_test",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
        facade = PersonFacade.getPersonFacade(emf);
    }

    @BeforeAll
    public static void setUpClassV2() {
        emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST, Strategy.DROP_AND_CREATE);
        facade = PersonFacade.getPersonFacade(emf);
    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Person.deleteAll").executeUpdate();
            em.getTransaction().commit();
            Stream.of(
                    new Person("Doomlord", "Bob", "36363636"),
                    new Person("Kekmaster", "John", "12312369"),
                    new Person("Branches", "the bobcat muncher", "1234")
            ).forEach(o -> {
                em.getTransaction().begin();
                em.persist(o);
                em.getTransaction().commit();
            });
        } finally {
            em.close();
        }
    }

    @Test
    public void addPersonTest()  {
        int curSize = facade.getAllPersons().size();
        facade.addPerson("John", "Doe", "36363636");
        assertEquals(facade.getAllPersons().size(), curSize + 1);
        assertEquals(true, facade.getAllPersons().stream().anyMatch(o -> o.getfName().equals("John")));
    }
    
    @Disabled
    @Test
    public void deletePersonTest() throws PersonNotFoundException{
        int curSize = facade.getAllPersons().size();
        facade.deletePerson(facade.getAllPersons().get(0).getId());
        assertEquals(curSize - 1, facade.getAllPersons().size());
        assertEquals(false, facade.getAllPersons().stream().anyMatch(o -> o.getfName().equals("Doomlord")));
    }

    @Test
    public void getPersonTest() throws PersonNotFoundException{
        assertEquals("John", facade.getPerson(facade.getAllPersons().get(1).getId())
                .getlName());

    }

    @Test
    public void getAllPersonsTest() {
        assertEquals(3, facade.getAllPersons().size());
        assertEquals("Branches", facade.getAllPersons().get(2).getfName());
    }
    
    @Disabled
    @Test
    public void editPersonTest() throws PersonNotFoundException{
        Person p = facade.getAllPersons().get(0);
        p.setlName("gurmol");
        Person k = facade.editPerson(p,p.getId());
        assertEquals("gurmol", facade.getPerson(k.getId()).getlName());
        assertEquals("gurmol", facade.getPerson(p.getId()).getlName());
    }
}
