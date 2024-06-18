package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A class for book copies.
 *
 * @author Team 50
 * @version 3.0
 */

public class BookCopy {
    private int id;
    private String isbn;
    private String shelfLocation;
    private boolean addedToLibrary;
    private boolean lent;
    private String lentDate;
    private Book book;

    public BookCopy(int id, String isbn, String shelfLocation, boolean addedToLibrary, boolean lent, String lentDate) {
        this.id = id;
        this.isbn = isbn;
        this.shelfLocation = shelfLocation;
        this.addedToLibrary = addedToLibrary;
        this.lent = lent;
        this.lentDate = lentDate;
        this.book = Book.findBookByIsbn(isbn);
    }

    public int getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getShelfLocation() {
        return shelfLocation;
    }

    public boolean isAddedToLibrary() {
        return addedToLibrary;
    }

    public boolean isLent() {
        return lent;
    }

    public String getLentDate() {
        return lentDate;
    }

    public Book getBook() {
        return book;
    }

    public static List<BookCopy> bookCopies = new ArrayList<>();

    public static void loadBookCopiesFromCSV() {
        String csvFile = "src/main/resources/buchkopien.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            boolean firstLine = true; // Skip header
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                String[] fields = line.split(",");

                if (fields.length >= 6) {
                    int id = Integer.parseInt(fields[0].trim());
                    String isbn = fields[1].trim();
                    String shelfLocation = fields[2].trim();
                    boolean addedToLibrary = Boolean.parseBoolean(fields[3].trim());
                    boolean lent = Boolean.parseBoolean(fields[4].trim());
                    String lentDate = fields[5].trim();

                    BookCopy bookCopy = new BookCopy(id, isbn, shelfLocation, addedToLibrary, lent, lentDate);
                    bookCopies.add(bookCopy);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public static void displayBookCopies() {
        System.out.println("Alle Buchkopien:");
        for (BookCopy copy : bookCopies) {
            System.out.println("ID: " + copy.getId() + ", ISBN: " + copy.getIsbn() + ", Regal: " + copy.getShelfLocation() +
                    ", Hinzugefügt zur Bibliothek: " + (copy.isAddedToLibrary() ? "Ja" : "Nein") +
                    ", Ausgeliehen: " + (copy.isLent() ? "Ja" : "Nein") +
                    ", Ausleihdatum: " + copy.getLentDate() +
                    ", Titel: " + copy.getBook().getTitle() + ", Autoren: " + copy.getBook().getAuthors());
        }
    }

    public static void displayLentBookCopies() {
        System.out.println("Alle ausgeliehenen Buchkopien:");
        for (BookCopy copy : bookCopies) {
            if (copy.isLent()) {
                System.out.println("ID: " + copy.getId() + ", ISBN: " + copy.getIsbn() + ", Regal: " + copy.getShelfLocation() +
                        ", Ausleihdatum: " + copy.getLentDate() +
                        ", Titel: " + copy.getBook().getTitle() + ", Autoren: " + copy.getBook().getAuthors());
            }
        }
    }

    public static void displayAvailableBookCopies() {
        System.out.println("Alle nicht ausgeliehenen Buchkopien:");
        for (BookCopy copy : bookCopies) {
            if (!copy.isLent()) {
                System.out.println("ID: " + copy.getId() + ", ISBN: " + copy.getIsbn() + ", Regal: " + copy.getShelfLocation() +
                        ", Titel: " + copy.getBook().getTitle() + ", Autoren: " + copy.getBook().getAuthors());
            }
        }
    }

    public static void deleteBookCopy(int id) {
        bookCopies.removeIf(copy -> copy.getId() == id);
        System.out.println("Buchkopie mit ID " + id + " wurde gelöscht.");
    }

    public static List<BookCopy> searchBookCopy(String query) {
        List<BookCopy> results = new ArrayList<>();
        for (BookCopy copy : bookCopies) {
            if (copy.getIsbn().contains(query) ||
                    copy.getBook().getTitle().contains(query) ||
                    copy.getBook().getAuthors().contains(query)) {
                results.add(copy);
            }
        }
        return results;
    }

    public static BookCopy findBookCopyById(int id) {
        for (BookCopy copy : bookCopies) {
            if (copy.getId() == id) {
                return copy;
            }
        }
        return null;
    }

    public void lendBookCopy(int customerId, String lentDate) {
        this.lent = true;
        this.lentDate = lentDate;
        System.out.println("Buchkopie mit ID " + id + " wurde an Kunden mit ID " + customerId + " ausgeliehen.");
    }

    public void returnBookCopy() {
        this.lent = false;
        this.lentDate = null;
        System.out.println("Buchkopie mit ID " + id + " wurde zurückgegeben.");
    }
}