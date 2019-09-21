package facades;

import dto.PersonDTO;
import entities.Person;
import exceptions.PersonNotFoundException;
import interfaces.IPersonFacade;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

public class PersonFacade implements IPersonFacade {

    private static PersonFacade instance;
    private static EntityManagerFactory emf;

    private PersonFacade() {
    }

    public static PersonFacade getPersonFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public Person addPerson(String fName, String lName, String phone) {
        Person p = new Person(fName, lName, phone);
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
        return p;
    }

    @Override
    public Person deletePerson(int id) throws PersonNotFoundException {
        EntityManager em = getEntityManager();
        try {
            Person p = em.find(Person.class, id);
            em.getTransaction().begin();
            em.remove(p);
            em.getTransaction().commit();
            return p;
        } finally {
            getEntityManager().close();
        }
    }

    @Override
    public Person getPerson(int id) throws PersonNotFoundException {
        try {
            return getEntityManager().find(Person.class, id);
        } finally {
            getEntityManager().close();
        }
    }

    @Override
    public List<Person> getAllPersons() {
        try {
            return (getEntityManager()
                    .createNamedQuery("Person.findAll")
                    .getResultList());
        } finally {
            getEntityManager().close();
        }
    }

    @Override
    public Person editPerson(Person p) throws PersonNotFoundException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
            return p;
        } finally {
            em.close();
        }

    }
}
