package interfaces;

import entities.Customer;
import java.util.List;

/**
 *
 * @author Henning
 */
public interface ICustomerFacade {

    public Customer getCustomer(int id);

    public List<Customer> getCustomers();

    public Customer addCustomer(Customer cust);

    public Customer deleteCustomer(int id);

    public Customer editCustomer(Customer cust);

}
