import java.util.HashMap;

public class CustomerHandler {
    private HashMap<String, Customer> customers;

    public CustomerHandler() {
        this.customers = new HashMap<String, Customer>();
    }

    public Customer getCustomer(String email) {
        if (customers.containsKey(email)) {
            return customers.get(email);
        } else {
            return null;
        }
    }

    public boolean addCustomer(Customer customer) {
        if (customers.containsKey(customer.getEmail())) {
            return false;
        } else {
            customers.put(customer.getEmail(), customer);
            return true;
        }
    }
}