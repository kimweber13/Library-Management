package org.example;

import java.util.List;

/**
 * A class to specify a book.
 *
 * @author Team 50
 * @version 2.0
 */

public class Book {
    private String isbn;
    private String title;

    public Book(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public static List<Book> books = LibrarySystem.books;

    public static void displayBooks() {
        System.out.println("+--------------------------------------+");
        System.out.println("|              Bücherliste             |");
        System.out.println("+--------------------------------------+");
        for (Book book : books) {
            System.out.println(book.getTitle());
            System.out.println("ISBN: " + book.getIsbn());
        }
    }

    public static void deleteBook(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                books.remove(book);
                System.out.println("Buch gelöscht: " + book.getTitle());
                return;
            }
        }
        System.out.println("Buch mit ISBN " + isbn + " nicht gefunden.");
    }
}