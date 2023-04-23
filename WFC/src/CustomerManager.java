import java.util.HashMap;

public class CustomerManager {
    private HashMap<String, Customer> customers;

    public CustomerManager() {
        this.customers = new HashMap<String, Customer>();
    }

    public Customer getCustomer(String phoneNumber) {
        if (customers.containsKey(phoneNumber)) {
            return customers.get(phoneNumber);
        } else {
            System.out.println("Customer with phone number " + phoneNumber + " does not exist");
            return null;
        }
    }

    public boolean addCustomer(Customer customer) {
        if (customers.containsKey(customer.getPhoneNumber())) {
            return false;
        } else {
            customers.put(customer.getPhoneNumber(), customer);
            return true;
        }
    }
}