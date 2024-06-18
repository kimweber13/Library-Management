package org.example;

import java.util.List;
import java.util.Scanner;

/**
 * A class to run the library system.
 *
 * @author Team 50
 * @version 3.0
 */

public class LibrarySystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        loadExampleData();

        while (!exit) {
            System.out.println("+-----------------------------------------------------+");
            System.out.println("|  1. Bücher anzeigen     |   2. Buch löschen         |");
            System.out.println("|-------------------------|---------------------------|");
            System.out.println("|  3. Buch suchen (ISBN)  |   4. Buchkopien anzeigen  |");
            System.out.println("|-------------------------|---------------------------|");
            System.out.println("|  5. Buchkopien löschen  |   6. Buchkopien suchen    |");
            System.out.println("|                         |      (ISBN, Autor, Titel) |");
            System.out.println("|-------------------------|---------------------------|");
            System.out.println("|  7. Kunden löschen      |   8. Kunden anzeigen      |");
            System.out.println("|-------------------------|---------------------------|");
            System.out.println("|  0. Exit                |");
            System.out.println("+-------------------------+");

            System.out.println("Bitte eine Eingabe treffen: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    Book.displayBooks();
                    break;
                case 2:
                    System.out.print("Geben Sie die ISBN des zu löschenden Buches ein: ");
                    String isbnToDelete = scanner.nextLine();
                    Book.deleteBook(isbnToDelete);
                    break;
                case 3:
                    System.out.print("Geben Sie die ISBN des gesuchten Buches ein: ");
                    String isbnToSearch = scanner.nextLine();
                    Book foundBook = Book.findBookByIsbn(isbnToSearch);
                    if (foundBook != null) {
                        displayBookDetails(foundBook);
                    } else {
                        System.out.println("Buch mit ISBN " + isbnToSearch + " wurde nicht gefunden.");
                    }
                    break;
                case 4:
                    BookCopy.displayBookCopies();
                    break;
                case 5:
                    System.out.print("Geben Sie die ID der zu löschenden Buchkopie ein: ");
                    int copyId = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    BookCopy.deleteBookCopy(copyId);
                    break;
                case 6:
                    searchBookCopyOption(scanner);
                    break;
                case 7:
                    System.out.print("Geben Sie die ID des zu löschenden Kunden ein: ");
                    int customerId = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    Customer.deleteCustomer(customerId);
                    break;
                case 8:
                    Customer.displayCustomers();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Ungültige Option. Bitte wählen Sie erneut.");
                    break;
            }
        }
        scanner.close();
    }

    public static void loadExampleData() {
        Book.loadBooksFromCSV();
        BookCopy.loadBookCopiesFromCSV();
        Customer.loadCustomersFromCSV();
    }

    private static void displayBookDetails(Book book) {
        System.out.println("Buchdetails:");
        System.out.println("ISBN: " + book.getIsbn());
        System.out.println("Titel: " + book.getTitle());
        System.out.println("Autoren: " + book.getAuthors());
        System.out.println("Jahr: " + book.getYear());
        System.out.println("Stadt: " + book.getCity());
        System.out.println("Verlag: " + book.getPublisher());
        System.out.println("Auflage: " + book.getEdition());
    }

    private static void searchBookCopyOption(Scanner scanner) {
        System.out.println("Suchen nach Buchkopie (nach ISBN, Titel oder Autor)");
        System.out.print("Geben Sie einen Suchbegriff ein: ");
        String query = scanner.nextLine();

        List<BookCopy> results = BookCopy.searchBookCopy(query);

        if (results.isEmpty()) {
            System.out.println("Keine Ergebnisse gefunden für: " + query);
        } else {
            System.out.println("Suchergebnisse:");
            for (BookCopy copy : results) {
                System.out.println(copy.getBook().getTitle() + "," +
                        copy.getBook().getAuthors() + "," +
                        copy.getBook().getIsbn() + "," +
                        copy.getId() + "," +
                        copy.getShelfLocation() + "," +
                        (copy.isLent() ? "Ausgeliehen" : "Verfügbar") + "," +
                        copy.getLentDate());
            }
        }
    }
}