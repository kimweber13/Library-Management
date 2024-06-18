package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A class for books.
 *
 * @author Team 50
 * @version 3.0
 */

public class Book {
    private String isbn;
    private String title;
    private String authors;
    private int year;
    private String city;
    private String publisher;
    private String edition;

    public Book(String isbn, String title, String authors, int year, String city, String publisher, String edition) {
        this.isbn = isbn;
        this.title = title;
        this.authors = authors;
        this.year = year;
        this.city = city;
        this.publisher = publisher;
        this.edition = edition;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthors() {
        return authors;
    }

    public int getYear() {
        return year;
    }

    public String getCity() {
        return city;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getEdition() {
        return edition;
    }

    public static List<Book> books = new ArrayList<>();

    public static void loadBooksFromCSV() {
        String csvFile = "src/main/resources/buecher.csv";

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
                    String isbn = fields[0].trim();
                    String title = fields[1].trim();
                    String authors = fields[2].trim();
                    int year = Integer.parseInt(fields[3].trim());
                    String city = fields[4].trim();
                    String publisher = fields[5].trim();
                    String edition = fields[6].trim();

                    Book book = new Book(isbn, title, authors, year, city, publisher, edition);
                    books.add(book);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public static void displayBooks() {
        System.out.println("");
        System.out.println("Alle Bücher:");
        for (Book book : books) {
            System.out.println(book.getIsbn() + " - " + book.getTitle() + " von " + book.getAuthors() + ", " + book.getYear() + ", " + book.getCity() + ", " + book.getPublisher() + ", " + book.getEdition());
        }
    }

    public static void deleteBook(String isbn) {
        books.removeIf(book -> book.getIsbn().equals(isbn));
        System.out.println("Buch mit ISBN " + isbn + " wurde gelöscht.");
    }

    public static Book findBookByIsbn(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }
}