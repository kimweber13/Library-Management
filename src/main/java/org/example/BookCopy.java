package org.example;

/**
 * A class to specify a book copy.
 *
 * @author Team 50
 * @version 3.0
 */

public class BookCopy {
    private int id;
    private Book book;

    public BookCopy(int id, Book book) {
        this.id = id;
        this.book = book;
    }

    public int getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public static void displayBookCopies() {
        System.out.println("Buchkopien:");
        for (BookCopy copy : LibrarySystem.getBookCopies().values()) {
            System.out.println("ID: " + copy.getId() + ", Buch: " + copy.getBook().getTitle());
        }
    }

    public static void deleteBookCopy(int copyId) {
        if (LibrarySystem.getBookCopies().containsKey(copyId)) {
            BookCopy deletedCopy = LibrarySystem.getBookCopies().remove(copyId);
            System.out.println("Buchkopie gelöscht: ID " + deletedCopy.getId() + ", Buch: " + deletedCopy.getBook().getTitle());
        } else {
            System.out.println("Buchkopie mit ID " + copyId + " wurde nicht gefunden.");
        }
    }
}