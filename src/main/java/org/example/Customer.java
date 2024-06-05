package org.example;

/**
 * A class to specify a customer.
 *
 * @author Team 50
 * @version 2.0
 */

public class Customer {
    private int id;
    private String name;

    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static void displayCustomers() {
        System.out.println("Kunden:");
        for (Customer customer : LibrarySystem.getCustomers()) {
            System.out.println("ID: " + customer.getId() + ", Name: " + customer.getName());
        }
    }

    public static void deleteCustomer(int customerId) {
        boolean found = false;
        for (Customer customer : LibrarySystem.getCustomers()) {
            if (customer.getId() == customerId) {
                LibrarySystem.getCustomers().remove(customer);
                System.out.println("Kunde gelöscht: ID " + customer.getId() + ", Name: " + customer.getName());
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Kunde mit ID " + customerId + " wurde nicht gefunden.");
        }
    }
}