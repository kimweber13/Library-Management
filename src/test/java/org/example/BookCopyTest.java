package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A class for testing features of book copies.
 *
 * @author Team 50
 * @version 3.0
 */

public class BookCopyTest {

    @BeforeEach
    public void setUp() {
        BookCopy.bookCopies.clear();
        Book.books.clear();

        Book.books.add(new Book("1234567890", "Testbuch 1", "Autor 1", 2024, "Stadt 1", "Verlag 1", "1. Auflage"));
        Book.books.add(new Book("9876543210", "Testbuch 2", "Autor 2", 2024, "Stadt 2", "Verlag 2", "2. Auflage"));

        BookCopy.bookCopies.add(new BookCopy(1, "1234567890", "Regal A", true, false, null));
        BookCopy.bookCopies.add(new BookCopy(2, "9876543210", "Regal B", false, true, "2024-06-18"));
    }

    @AfterEach
    public void tearDown() {
        BookCopy.bookCopies.clear();
        Book.books.clear();
    }

    @Test
    public void testGetters() {
        BookCopy copy = BookCopy.bookCopies.get(0);

        assertEquals(1, copy.getId());
        assertEquals("1234567890", copy.getIsbn());
        assertEquals("Regal A", copy.getShelfLocation());
        assertTrue(copy.isAddedToLibrary());
        assertFalse(copy.isLent());
        assertNull(copy.getLentDate());
        assertEquals("Testbuch 1", copy.getBook().getTitle());
        assertEquals("Autor 1", copy.getBook().getAuthors());
    }

    @Test
    public void testDeleteBookCopy() {
        int initialSize = BookCopy.bookCopies.size();
        BookCopy.deleteBookCopy(1);
        assertEquals(initialSize - 1, BookCopy.bookCopies.size());
        assertNull(BookCopy.findBookCopyById(1));
    }

    @Test
    public void testSearchBookCopy() {
        List<BookCopy> results = BookCopy.searchBookCopy("Testbuch");
        assertEquals(2, results.size());
        assertTrue(results.stream().anyMatch(copy -> copy.getIsbn().equals("1234567890")));
    }

    @Test
    public void testFindBookCopyById() {
        BookCopy copy = BookCopy.findBookCopyById(2);
        assertNotNull(copy);
        assertEquals("9876543210", copy.getIsbn());
    }

    @Test
    public void testLendBookCopy() {
        BookCopy copy = BookCopy.bookCopies.get(0);
        assertFalse(copy.isLent());
        copy.lendBookCopy(1234, "2024-06-18");
        assertTrue(copy.isLent());
        assertEquals("2024-06-18", copy.getLentDate());
    }

    @Test
    public void testReturnBookCopy() {
        BookCopy copy = BookCopy.bookCopies.get(1);
        assertTrue(copy.isLent());
        copy.returnBookCopy();
        assertFalse(copy.isLent());
        assertNull(copy.getLentDate());
    }

    @Test
    public void testDisplayBookCopies() {
        BookCopy.displayBookCopies();
    }

    @Test
    public void testDisplayAvailableBookCopies() {
        BookCopy.displayAvailableBookCopies();
    }

    @Test
    public void testDisplayLentBookCopies() {
        BookCopy.displayLentBookCopies();
    }
}