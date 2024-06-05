import org.example.Book;
import org.example.LibrarySystem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * A class to test a books features.
 *
 * @author Team 50
 * @version 2.0
 */

public class BookTest {

    private Book book;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        book = new Book("1234567890", "Odyssee, Homer");
        LibrarySystem.getBooks().add(book);
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        LibrarySystem.getBooks().clear();
    }

    @Test
    public void testDisplayBooks() {
        List<Book> newBooks = new ArrayList<>();
        newBooks.add(new Book("1234567890", "Odyssee, Homer"));
        newBooks.add(new Book("0987654321", "Der Fremde, Albert Camus"));
        LibrarySystem.getBooks().clear();
        LibrarySystem.getBooks().addAll(newBooks);

        Book.displayBooks();

        String output = outContent.toString();
        assertTrue(output.contains("Bücherliste"));
        for (Book book : newBooks) {
            assertTrue(output.contains(book.getTitle()));
            assertTrue(output.contains(book.getIsbn()));
        }
    }

    @Test
    public void testDeleteBook() {
        String isbn = book.getIsbn();
        List<Book> booksBeforeDelete = new ArrayList<>(LibrarySystem.getBooks());
        assertTrue(booksBeforeDelete.contains(book));

        Book.deleteBook(isbn);

        assertFalse(LibrarySystem.getBooks().contains(book));
    }

    @Test
    public void testDeleteBookNotFound() {
        String isbn = "0000000000"; // invented isbn, not available

        Book.deleteBook(isbn);

        String output = outContent.toString();
        assertTrue(output.contains("Buch mit ISBN " + isbn + " nicht gefunden."));
    }
}