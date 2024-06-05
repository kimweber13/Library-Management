package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * The implementation of the library.
 * Run this class in order to start the system.
 *
 * @author Team 50
 * @version 2.0
 */

public class LibrarySystem {
    public static List<Book> books = new ArrayList<>();
    private static List<Customer> customers = new ArrayList<>();
    private static Map<Integer, BookCopy> bookCopies = new HashMap<>();

    public static List<Book> getBooks() {
        return books;
    }

    public static Map<Integer, BookCopy> getBookCopies() {
        return bookCopies;
    }

    public static List<Customer> getCustomers() {
        return customers;
    }

    public static void main(String[] args) {
        addExampleData();
        runLibrarySystem();
    }

    private static void addExampleData() {
        Book book1 = new Book("1234567890", "Odyssee, Homer");
        Book book2 = new Book("0987654321", "Der Fremde, Albert Camus");
        books.add(book1);
        books.add(book2);

        Customer customer1 = new Customer(1, "Thomas Müller");
        Customer customer2 = new Customer(2, "Marco Reus");
        customers.add(customer1);
        customers.add(customer2);

        BookCopy copy1 = new BookCopy(1, book1);
        BookCopy copy2 = new BookCopy(2, book2);
        bookCopies.put(copy1.getId(), copy1);
        bookCopies.put(copy2.getId(), copy2);
    }

    private static void runLibrarySystem() {
        Scanner scanner = new Scanner(System.in);
        displayWelcomeMessage();

        System.out.println("Möchten Sie die Bibliothek betreten? (ja/nein)");
        System.out.print("> ");
        String enterLibrary = scanner.nextLine();

        if (enterLibrary.equals("ja")) {
            VisitorActions visitorActions = new VisitorActions();
            visitorActions.performActions(scanner);
        } else {
            System.out.println("Vielen Dank und auf Wiedersehen!");
        }
        scanner.close();
    }

    private static void displayWelcomeMessage() {
        System.out.println("+--------------------------------------+");
        System.out.println("|          Willkommen zur              |");
        System.out.println("|           Bibliothek!                |");
        System.out.println("+--------------------------------------+");
    }
}