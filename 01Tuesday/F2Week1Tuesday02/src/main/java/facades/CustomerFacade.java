package facades;

import entities.Customer;
import interfaces.ICustomerFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Henning
 */
public class CustomerFacade implements ICustomerFacade {

    private static CustomerFacade instance;
    private static EntityManagerFactory emf;

    private CustomerFacade() {
    }

    public static CustomerFacade getCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public Customer getCustomer(int id) {
        EntityManager em = getEntityManager();
        try {
            return (Customer) em.createNamedQuery("Customer.getByID")
                    .setParameter("id", id)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Customer> getCustomers() {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Customer.getAll")
                    .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Customer addCustomer(Customer cust) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cust);
            em.getTransaction().commit();
            return cust;
        } finally {
            em.close();
        }
    }

    @Override
    public Customer deleteCustomer(int id) {
        EntityManager em = getEntityManager();
        try {
            Customer c = getCustomer(id);
            em.getTransaction().begin();
            em.remove(c);
            em.getTransaction().commit();
            return c;
        } finally {
            em.close();
        }
    }

    @Override
    public Customer editCustomer(Customer cust) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(cust);
            em.getTransaction().commit();
            return cust;
        } finally {
            em.close();
        }
    }
}
