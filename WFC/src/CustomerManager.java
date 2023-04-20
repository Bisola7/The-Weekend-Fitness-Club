import java.util.HashMap;

public class CustomerManager {
    private HashMap<Integer, Customer> customers;

    public CustomerManager() {
        this.customers = new HashMap<Integer, Customer>();
    }

    public Customer getCustomer(Integer phoneNumber) {
        if (customers.containsKey(phoneNumber)) {
            return customers.get(phoneNumber);
        } else {
            System.out.println("Customer with phone number " + phoneNumber + " does not exist");
            return null;
        }
    }

    public void addCustomer(Customer customer) {
        if (customers.containsKey(customer.getPhoneNumber())) {
            System.out.println("Customer with phone number " + customer.getPhoneNumber() + " already exists");
        } else {
            customers.put(customer.getPhoneNumber(), customer);
            System.out.println("Customer with phone number " + customer.getPhoneNumber() + " has been added");
        }
    }
}