package org.example;

import java.util.List;
import java.util.Scanner;

/**
 * A class for running the library.
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
            System.out.println("\nBibliothekssystem Menü:");
            System.out.println("1. Bücher anzeigen");
            System.out.println("2. Buch löschen");
            System.out.println("3. Buch suchen und anzeigen (nach ISBN)");
            System.out.println("4. Alle Buchkopien anzeigen");
            System.out.println("5. Buchkopie löschen");
            System.out.println("6. Buchkopie suchen und anzeigen (nach ISBN, Titel oder Autor)");
            System.out.println("7. Kunden anzeigen");
            System.out.println("8. Kunden löschen");
            System.out.println("9. Buchkopie ausleihen (nach Kunden-ID und Buchkopie-ID)");
            System.out.println("10. Buchkopie zurückgeben (nach Kunden-ID und Buchkopie-ID)");
            System.out.println("11. Alle ausgeliehenen Buchkopien anzeigen");
            System.out.println("12. Alle nicht ausgeliehenen Buchkopien anzeigen");
            System.out.println("0. Beenden");
            System.out.print("Wählen Sie eine Option: ");

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
                    Customer.displayCustomers();
                    break;
                case 8:
                    System.out.print("Geben Sie die ID des zu löschenden Kunden ein: ");
                    int customerId = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    Customer.deleteCustomer(customerId);
                    break;
                case 9:
                    System.out.print("Geben Sie die Kunden-ID ein: ");
                    int customerID = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.print("Geben Sie die Buchkopie-ID ein: ");
                    int bookCopyID = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    lendBookCopy(customerID, bookCopyID, scanner);
                    break;
                case 10:
                    System.out.print("Geben Sie die Kunden-ID ein: ");
                    customerID = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.print("Geben Sie die Buchkopie-ID ein: ");
                    bookCopyID = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    returnBookCopy(customerID, bookCopyID);
                    break;
                case 11:
                    displayBorrowedBookCopies();
                    break;
                case 12:
                    displayAvailableBookCopies();
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
        System.out.print("Geben Sie die ISBN, den Titel oder den Autor der gesuchten Buchkopie ein: ");
        String query = scanner.nextLine();
        List<BookCopy> results = BookCopy.searchBookCopy(query);
        if (!results.isEmpty()) {
            System.out.println("Gefundene Buchkopien:");
            for (BookCopy copy : results) {
                System.out.println("ID: " + copy.getId() + ", ISBN: " + copy.getIsbn() + ", Regal: " + copy.getShelfLocation() +
                        ", Hinzugefügt zur Bibliothek: " + (copy.isAddedToLibrary() ? "Ja" : "Nein") +
                        ", Ausgeliehen: " + (copy.isLent() ? "Ja" : "Nein") +
                        ", Ausleihdatum: " + copy.getLentDate() +
                        ", Titel: " + copy.getBook().getTitle() + ", Autoren: " + copy.getBook().getAuthors());
            }
        } else {
            System.out.println("Keine Buchkopien gefunden, die auf die Suchanfrage passen.");
        }
    }

    private static void lendBookCopy(int customerId, int bookCopyId, Scanner scanner) {
        Customer customer = Customer.findCustomerById(customerId);
        BookCopy bookCopy = BookCopy.findBookCopyById(bookCopyId);
        if (customer != null && bookCopy != null && !bookCopy.isLent()) {
            System.out.print("Geben Sie das Ausleihdatum ein (YYYY-MM-DD): ");
            String lentDate = scanner.nextLine();
            bookCopy.lendBookCopy(customerId, lentDate);
        } else {
            System.out.println("Kunde oder Buchkopie nicht gefunden, oder Buchkopie bereits ausgeliehen.");
        }
    }

    private static void returnBookCopy(int customerId, int bookCopyId) {
        Customer customer = Customer.findCustomerById(customerId);
        BookCopy bookCopy = BookCopy.findBookCopyById(bookCopyId);
        if (customer != null && bookCopy != null && bookCopy.isLent()) {
            bookCopy.returnBookCopy();
        } else {
            System.out.println("Kunde oder Buchkopie nicht gefunden, oder Buchkopie war nicht ausgeliehen.");
        }
    }

    private static void displayBorrowedBookCopies() {
        System.out.println("Alle ausgeliehenen Buchkopien:");
        for (BookCopy copy : BookCopy.bookCopies) {
            if (copy.isLent()) {
                System.out.println("ID: " + copy.getId() + ", ISBN: " + copy.getIsbn() + ", Regal: " + copy.getShelfLocation() +
                        ", Hinzugefügt zur Bibliothek: " + (copy.isAddedToLibrary() ? "Ja" : "Nein") +
                        ", Ausgeliehen: " + (copy.isLent() ? "Ja" : "Nein") +
                        ", Ausleihdatum: " + copy.getLentDate() +
                        ", Titel: " + copy.getBook().getTitle() + ", Autoren: " + copy.getBook().getAuthors());
            }
        }
    }

    private static void displayAvailableBookCopies() {
        System.out.println("Alle nicht ausgeliehenen Buchkopien:");
        for (BookCopy copy : BookCopy.bookCopies) {
            if (!copy.isLent()) {
                System.out.println("ID: " + copy.getId() + ", ISBN: " + copy.getIsbn() + ", Regal: " + copy.getShelfLocation() +
                        ", Hinzugefügt zur Bibliothek: " + (copy.isAddedToLibrary() ? "Ja" : "Nein") +
                        ", Ausgeliehen: " + (copy.isLent() ? "Ja" : "Nein") +
                        ", Ausleihdatum: " + copy.getLentDate() +
                        ", Titel: " + copy.getBook().getTitle() + ", Autoren: " + copy.getBook().getAuthors());
            }
        }
    }
}