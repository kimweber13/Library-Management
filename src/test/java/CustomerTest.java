import org.example.Customer;
import org.example.LibrarySystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A class to test a customers features.
 *
 * @author Team 50
 * @version 2.0
 */

public class CustomerTest {

    private Customer customer;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        LibrarySystem.getCustomers().clear();
        customer = new Customer(1, "Thomas Müller");
        LibrarySystem.getCustomers().add(customer);
    }

    @Test
    public void testDisplayCustomers() {
        LibrarySystem.getCustomers().clear();
        Customer customer1 = new Customer(1, "Thomas Müller");
        Customer customer2 = new Customer(2, "Marco Reus");
        LibrarySystem.getCustomers().add(customer1);
        LibrarySystem.getCustomers().add(customer2);

        Customer.displayCustomers();

        String output = outContent.toString().trim();
        assertTrue(output.contains("Kunden:"));
        assertTrue(output.contains("ID: 1, Name: Thomas Müller"));
        assertTrue(output.contains("ID: 2, Name: Marco Reus"));
    }

    @Test
    public void testDeleteCustomer() {
        int customerId = customer.getId();
        List<Customer> customers = LibrarySystem.getCustomers();
        assertTrue(customers.contains(customer));

        Customer.deleteCustomer(customerId);

        assertFalse(customers.contains(customer));
    }

    @Test
    public void testDeleteNonExistingCustomer() {
        int nonExistingCustomerId = 1234;

        Customer.deleteCustomer(nonExistingCustomerId);

        String output = outContent.toString().trim();
        assertTrue(output.contains("Kunde mit ID " + nonExistingCustomerId + " wurde nicht gefunden."));
    }
}