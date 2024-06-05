import org.example.Book;
import org.example.BookCopy;
import org.example.LibrarySystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * A class to test the book copies.
 *
 * @author Team 50
 * @version 2.0
 */

public class BookCopyTest {

    private Book book;
    private BookCopy bookCopy1;
    private BookCopy bookCopy2;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        book = new Book("1234567890", "Odyssee, Homer");
        bookCopy1 = new BookCopy(1, book);
        bookCopy2 = new BookCopy(2, book);
        LibrarySystem.getBookCopies().put(bookCopy1.getId(), bookCopy1);
        LibrarySystem.getBookCopies().put(bookCopy2.getId(), bookCopy2);
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testDeleteBookCopy() {
        int copyIdToDelete = bookCopy1.getId();
        Map<Integer, BookCopy> bookCopies = LibrarySystem.getBookCopies();
        assertTrue(bookCopies.containsKey(copyIdToDelete));

        BookCopy.deleteBookCopy(copyIdToDelete);
        BookCopy.displayBookCopies();

        assertFalse(bookCopies.containsKey(copyIdToDelete));
        String actualOutput = outContent.toString().trim().replaceAll("\\r?\\n", " ");
        String expectedOutput = "Buchkopien: ID: 2, Buch: Odyssee, Homer";
        assertTrue(actualOutput.contains(expectedOutput));
    }

    @Test
    public void testDeleteBookCopyNotAvailable() {
        int copyIdToDelete = bookCopy2.getId() + 1;
        Map<Integer, BookCopy> bookCopies = LibrarySystem.getBookCopies();
        assertFalse(bookCopies.containsKey(copyIdToDelete));

        BookCopy.deleteBookCopy(copyIdToDelete);
        BookCopy.displayBookCopies();

        assertFalse(bookCopies.containsKey(copyIdToDelete));
        String actualOutput = outContent.toString().trim().replaceAll("\\r?\\n", " ");
        String expectedOutput = "Buchkopie mit ID " + copyIdToDelete + " wurde nicht gefunden.";
        assertTrue(actualOutput.contains(expectedOutput));
    }
}