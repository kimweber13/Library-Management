package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A class for customers.
 *
 * @author Team 50
 * @version 3.0
 */

public class Customer {
    private int id;
    private String name;
    private String firstName;
    private String address;
    private String zipCode;
    private String city;
    private boolean feesPayed;

    public Customer(int id, String name, String firstName, String address, String zipCode, String city, boolean feesPayed) {
        this.id = id;
        this.name = name;
        this.firstName = firstName;
        this.address = address;
        this.zipCode = zipCode;
        this.city = city;
        this.feesPayed = feesPayed;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getAddress() {
        return address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public boolean hasFeesPayed() {
        return feesPayed;
    }

    public static List<Customer> customers = new ArrayList<>();

    public static void loadCustomersFromCSV() {
        String csvFile = "src/main/resources/benutzer.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            boolean firstLine = true; // Skip header
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                String[] fields = line.split(",");

                if (fields.length >= 7) {
                    int id = Integer.parseInt(fields[0].trim());
                    String name = fields[1].trim();
                    String firstName = fields[2].trim();
                    String address = fields[3].trim();
                    String zipCode = fields[4].trim();
                    String city = fields[5].trim();
                    boolean feesPayed = fields[6].trim().equalsIgnoreCase("ja");

                    Customer customer = new Customer(id, name, firstName, address, zipCode, city, feesPayed);
                    customers.add(customer);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public static void displayCustomers() {
        System.out.println("");
        System.out.println("Alle Kunden:");
        for (Customer customer : customers) {
            System.out.println(customer.getId() + " - " + customer.getName() + " " + customer.getFirstName() +
                    ", " + customer.getAddress() + ", " + customer.getZipCode() + " " + customer.getCity() +
                    ", Gebühren bezahlt: " + (customer.hasFeesPayed() ? "Ja" : "Nein"));
        }
    }

    public static void deleteCustomer(int id) {
        customers.removeIf(customer -> customer.getId() == id);
        System.out.println("Kunde mit ID " + id + " wurde gelöscht.");
    }
}