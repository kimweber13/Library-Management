package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A class for testing features of a customer.
 *
 * @author Team 50
 * @version 3.0
 */

public class CustomerTest {

    @BeforeEach
    public void setUp() {
        Customer.customers.clear();

        Customer.customers.add(new Customer(1, "Wirtz", "Flo", "Musterstr. 1", "12345", "Musterstadt", true));
        Customer.customers.add(new Customer(2, "Müller", "Thomas", "Hauptstr. 5", "54321", "Stuttgart", false));
    }

    @Test
    public void testGetters() {
        Customer customer = Customer.customers.get(0);

        assertEquals(1, customer.getId());
        assertEquals("Wirtz", customer.getName());
        assertEquals("Flo", customer.getFirstName());
        assertEquals("Musterstr. 1", customer.getAddress());
        assertEquals("12345", customer.getZipCode());
        assertEquals("Musterstadt", customer.getCity());
        assertTrue(customer.isFeesPayed());
    }

    @Test
    public void testDisplayCustomers() {
        Customer.displayCustomers();
    }

    @Test
    public void testDeleteCustomer() {
        int initialSize = Customer.customers.size();
        Customer.deleteCustomer(1);
        assertEquals(initialSize - 1, Customer.customers.size());
    }

    @Test
    public void testFindCustomerById() {
        Customer customer = Customer.findCustomerById(2);
        assertNotNull(customer);
        assertEquals("Müller", customer.getName());
    }
}