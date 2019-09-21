package facade;

import entities.Customer;
import entities.ItemType;
import entities.Orders;
import entities.OrderLine;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class Facade {

    private static Facade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private Facade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static Facade getFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new Facade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void createCustomer(Customer c) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
    }

    public Customer createCustomerReturn(Customer c) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
        return c;
    }

    public Customer readCustomerByID(long id) {
        return getEntityManager().find(Customer.class, id);
    }

    public List<Customer> readAllCustomers() {
        try {
            return getEntityManager()
                    .createNamedQuery("Customer.readAll").getResultList();
        } finally {
            getEntityManager().close();
        }
    }

    public void createItemType(ItemType tp) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(tp);
        em.getTransaction().commit();
    }

    public ItemType createItemTypeReturn(ItemType tp) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(tp);
        em.getTransaction().commit();
        return tp;
    }

    public void createOrder(Orders o) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(o);
        em.getTransaction().commit();
    }

    public void createOrderByCust(Customer c) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(new Orders(c));
        em.getTransaction().commit();
    }

    public Orders createOrderByCustReturn(Customer c) {
        Orders o = new Orders(c);
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(o);
        em.getTransaction().commit();
        return o;
    }

    public void createOrderLineBlank(OrderLine o) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(o);
        em.getTransaction().commit();
    }

    public OrderLine createOrderLine(int quantity, Orders o, ItemType it) {
        OrderLine ol = new OrderLine(quantity, o, it);
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(ol);
        em.getTransaction().commit();
        return ol;
    }

    public List<OrderLine> readOrderLines(Customer c) {
        try {
            return getEntityManager()
                    .createNamedQuery("OrderLine.readAllByCustomer")
                    .setParameter("CustomerID", c.getId())
                    .getResultList();
        } finally {
            getEntityManager().close();
        }
    }

    public ItemType readItemTypeByID(long id) {
        return getEntityManager().find(ItemType.class, id);
    }

    public List<Orders> getAllOrdersByCustomerName(String name) {
        try {
            return ((List<Orders>)getEntityManager()
                    .createNamedQuery("Customer.getAllOrders")
                    .getResultList())
                    .stream()
                    .collect(Collectors.toList());
        } finally {
            getEntityManager().close();
        }
    }
}
