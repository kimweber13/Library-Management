package org.example;

import java.util.Scanner;

/**
 * The visitors possible actions.
 *
 * @author Team 50
 * @version 2.0
 */

public class VisitorActions {
    private static String userId = "";

    public void performActions(Scanner scanner) {
        System.out.println("Bitte geben Sie Ihre Benutzer-ID ein:");
        System.out.print("> ");
        userId = scanner.nextLine();
        System.out.println("+--------------------------------------+");
        System.out.println("|           Besucheraktionen           |");
        System.out.println("+--------------------------------------+");
        System.out.println("Benutzer: " + userId);

        boolean proceedRequest = true;

        while (proceedRequest) {
            System.out.println("+-----------------------------------------------------+");
            System.out.println("|  1. Bücher anzeigen     |   2. Buch löschen         |");
            System.out.println("|-------------------------|---------------------------|");
            System.out.println("|  3. Buchkopien anzeigen |   4. Buchkopien löschen   |");
            System.out.println("|-------------------------|---------------------------|");
            System.out.println("|  5. Kunden anzeigen     |   6. Kunde löschen        |");
            System.out.println("|-------------------------|---------------------------|");
            System.out.println("|  7. nichts              |   8. Hauptmenü            |");
            System.out.println("+-----------------------------------------------------+");
            System.out.print("> ");

            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action) {
                case 1:
                    Book.displayBooks();
                    break;
                case 2:
                    deleteBook(scanner);
                    break;
                case 3:
                    BookCopy.displayBookCopies();
                    break;
                case 4:
                    deleteBookCopy(scanner);
                    break;
                case 5:
                    Customer.displayCustomers();
                    break;
                case 6:
                    deleteCustomer(scanner);
                    break;
                case 7:
                    System.out.println("Yet to implement.");
                    break;
                case 8:
                    System.out.println("Zurück zum Hauptmenü...");
                    LibrarySystem.main(new String[0]);
                    proceedRequest = false;
                    break;
                default:
                    System.out.println("Bitte eine valide Auswahl tätigen...");
                    break;
            }
        }
    }
    private void deleteBookCopy(Scanner scanner) {
        System.out.println("Bitte geben Sie die ID der zu löschenden Buchkopie ein:");
        System.out.print("> ");
        int copyId = scanner.nextInt();
        scanner.nextLine();
        BookCopy.deleteBookCopy(copyId);
    }

    private void deleteBook(Scanner scanner) {
        System.out.println("Bitte geben Sie die ISBN des zu löschenden Buches ein:");
        System.out.print("> ");
        String isbn = scanner.nextLine();
        Book.deleteBook(isbn);
    }

    private void deleteCustomer(Scanner scanner) {
        System.out.println("Bitte geben Sie die ID des zu löschenden Kunden ein:");
        System.out.print("> ");
        int customerId = scanner.nextInt();
        scanner.nextLine();
        Customer.deleteCustomer(customerId);
    }
}