package misc;

import entities.Customer;
import entities.ItemType;
import entities.OrderLine;
import entities.Orders;
import facade.Facade;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class test {

    static EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("pu");
    static Facade facade = Facade.getFacade(emf);

    public static void main(String[] args) {
        Persistence.generateSchema("pu", null);

        ItemType tp = facade.createItemTypeReturn(new ItemType("asdf", "odskf", 25));
        Customer c = facade.createCustomerReturn(new Customer("John", "rockingfellow"));
        Orders o = facade.createOrderByCustReturn(c);
        facade.createOrderLine(35, o, tp);
        facade.createOrderByCust(facade.readCustomerByID(1));
        facade.createCustomer(new Customer("cas", "asdasd@asasdc.qw"));

        //####Assignments####
        
        //####Create Customer####
        facade.createCustomer(new Customer("Assignment 1 Name", "Assignment 1 Email"));
        
        ////####Read Customer####
        System.out.println(facade.readCustomerByID(1).getName());
        
        ////####Read all Customers####
        System.out.println(facade.readAllCustomers().get(0).getName());
        
        ////####Create ItemType####
        facade.createItemType(new ItemType("Assignment 4 Name", "Assignment 4 Description", 25));
        
        //####Read ItemType####
        System.out.println(facade.readItemTypeByID(1).getDescription());
        
        //####Create order and add it to a customer####
        Customer cust06 = facade.createCustomerReturn(new Customer("Assignment 6 name", "Assignment 6 Email"));
        facade.createOrderByCustReturn(cust06);

        //####Create an OrderLine for a specific ItemType and add it to an Order####
        Customer cust07 = facade.createCustomerReturn(new Customer("Assignment 7 name", "Assignment 7 Email"));
        ItemType it07 = facade.createItemTypeReturn(new ItemType("Assignment 7 Name", "Assignment 8 Description", 69));
        Orders o7 = facade.createOrderByCustReturn(cust07);
        facade.createOrderLine(25,o7,it07);
        
        ////####Find all Orders for a specific Customer####
       //Customer cust08 = facade.createCustomerReturn(new Customer("Assignment 8 name", "Assignment 8 Email"));
       //Orders o8_1 = facade.createOrderByCustReturn(cust08);
       //Orders o8_2 = facade.createOrderByCustReturn(cust08);
       //Orders o8_3 = facade.createOrderByCustReturn(cust08);
       //System.out.println(facade.getAllOrdersByCustomerName(cust08.getName()).size());

        ////####Find the total price of an order####
        //Customer cust09 = facade.createCustomerReturn(new Customer("Assignment 9 name", "Assignment 9 Email"));
        //Orders o9 = facade.createOrderByCustReturn(cust09);
        //ItemType it09 = facade.createItemTypeReturn(new ItemType("Assignment 9 Name", "Assignment 9 Description", 72));
        //OrderLine ol9 = facade.createOrderLine(123, o9, it09);
    }
}
