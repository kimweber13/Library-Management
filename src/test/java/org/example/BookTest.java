package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A class for testing features of books.
 *
 * @author Team 50
 * @version 3.0
 */

public class BookTest {

    @BeforeEach
    public void setUp() {
        Book.books.clear();

        Book.books.add(new Book("1234567890", "Testbuch 1", "Autor 1", 2024, "Stadt 1", "Verlag 1", "1. Auflage"));
        Book.books.add(new Book("9876543210", "Testbuch 2", "Autor 2", 2024, "Stadt 2", "Verlag 2", "2. Auflage"));
    }

    @Test
    public void testLoadBooksFromCSV() {
    }

    @Test
    public void testDisplayBooks() {
        assertDoesNotThrow(() -> Book.displayBooks());
    }

    @Test
    public void testDeleteBook() {
        String isbnToDelete = "1234567890";
        Book.deleteBook(isbnToDelete);
        Book deletedBook = Book.findBookByIsbn(isbnToDelete);
        assertNull(deletedBook, "Das Buch mit der ISBN " + isbnToDelete + " sollte gelöscht worden sein.");
    }

    @Test
    public void testFindBookByIsbn() {
        String isbnToFind = "9876543210";
        Book foundBook = Book.findBookByIsbn(isbnToFind);
        assertNotNull(foundBook, "Das Buch mit der ISBN " + isbnToFind + " sollte gefunden werden.");
        assertEquals(isbnToFind, foundBook.getIsbn(), "Die ISBN des gefundenen Buches sollte " + isbnToFind + " sein.");
    }

    @Test
    public void testAddBook() {
        Book newBook = new Book("123456789", "Neues Buch", "Autor 3", 2024, "Stadt 3", "Verlag 3", "1. Auflage");
        Book.books.add(newBook);

        assertTrue(Book.books.contains(newBook), "Das Buch sollte zur Liste hinzugefügt worden sein.");
    }

    @Test
    public void testDeleteNonExistingBook() {
        String nonExistingIsbn = "9999999999";
        int initialSize = Book.books.size();
        Book.deleteBook(nonExistingIsbn);
        assertEquals(initialSize, Book.books.size(), "Die Größe der Bücherliste sollte unverändert bleiben, wenn ein nicht vorhandenes Buch gelöscht wird.");
    }

    @Test
    public void testFindNonExistingBookByIsbn() {
        String nonExistingIsbn = "9999999999";
        Book foundBook = Book.findBookByIsbn(nonExistingIsbn);
        assertNull(foundBook, "Ein nicht vorhandenes Buch sollte nicht gefunden werden.");
    }
}