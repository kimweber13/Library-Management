package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookCopy {
    private int id;
    private String isbn;
    private String shelfLocation;
    private boolean addedToLibrary;
    private boolean lent;
    private String lentDate;
    private Book book; // Referenz auf das Book-Objekt

    public BookCopy(int id, String isbn, String shelfLocation, boolean addedToLibrary, boolean lent, String lentDate, Book book) {
        this.id = id;
        this.isbn = isbn;
        this.shelfLocation = shelfLocation;
        this.addedToLibrary = addedToLibrary;
        this.lent = lent;
        this.lentDate = lentDate;
        this.book = book;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getShelfLocation() {
        return shelfLocation;
    }

    public void setShelfLocation(String shelfLocation) {
        this.shelfLocation = shelfLocation;
    }

    public boolean isAddedToLibrary() {
        return addedToLibrary;
    }

    public void setAddedToLibrary(boolean addedToLibrary) {
        this.addedToLibrary = addedToLibrary;
    }

    public boolean isLent() {
        return lent;
    }

    public void setLent(boolean lent) {
        this.lent = lent;
    }

    public String getLentDate() {
        return lentDate;
    }

    public void setLentDate(String lentDate) {
        this.lentDate = lentDate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
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

                    // Find the corresponding Book object
                    Book book = Book.findBookByIsbn(isbn);
                    if (book != null) {
                        BookCopy bookCopy = new BookCopy(id, isbn, shelfLocation, addedToLibrary, lent, lentDate, book);
                        bookCopies.add(bookCopy);
                    } else {
                        System.out.println("Buch mit ISBN " + isbn + " wurde nicht gefunden.");
                        // Hier können Sie optional eine Fehlerbehandlung oder Logging hinzufügen
                    }
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

    public static void deleteBookCopy(int id) {
        bookCopies.removeIf(copy -> copy.getId() == id);
        System.out.println("Buchkopie mit ID " + id + " wurde gelöscht.");
    }

    public static List<BookCopy> searchBookCopy(String query) {
        List<BookCopy> results = new ArrayList<>();

        // Suche nach ISBN
        for (BookCopy copy : bookCopies) {
            if (copy.getIsbn().equalsIgnoreCase(query)) {
                results.add(copy);
            }
        }

        // Suche nach Titel
        if (results.isEmpty()) {
            for (BookCopy copy : bookCopies) {
                if (copy.getBook().getTitle().toLowerCase().contains(query.toLowerCase())) {
                    results.add(copy);
                }
            }
        }

        // Suche nach Autoren
        if (results.isEmpty()) {
            for (BookCopy copy : bookCopies) {
                if (copy.getBook().getAuthors().toLowerCase().contains(query.toLowerCase())) {
                    results.add(copy);
                }
            }
        }

        return results;
    }
}