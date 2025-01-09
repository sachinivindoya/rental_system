import dao.CustomerDAO;
import models.Customer;

public class TestCustomerDAO {
    public static void main(String[] args) {
        CustomerDAO customerDAO = new CustomerDAO();

        // Test Add
        Customer newCustomer = new Customer("C011", "Test User", "1990-01-01", 45000, "Test Address", "Test City", "Test Province", "12345");
        if (customerDAO.add(newCustomer)) {
            System.out.println("Customer added successfully!");
        } else {
            System.out.println("Failed to add customer.");
        }

        // Test Find
        Customer customer = customerDAO.find("C011");
        if (customer != null) {
            System.out.println("Customer Found: " + customer.getName());
        } else {
            System.out.println("Customer not found.");
        }

        // Test Delete
        if (customerDAO.delete("C011")) {
            System.out.println("Customer deleted successfully!");
        } else {
            System.out.println("Failed to delete customer.");
        }
    }
}
