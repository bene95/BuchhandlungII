package Test;

import com.book.Book;
import org.junit.Assert;
import src.FormatParser;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FormatParserTest {
    @org.junit.jupiter.api.Test
    void toFormat() {
        ArrayList<Book> books = new ArrayList<Book>();
        books.add(new Book("Test","10","12345678900"));
        books.add(new Book("Blub","30","98765432100"));
        FormatParser.toFormat(books);
        String must = "Test#10#12345678900$Blub#30#98765432100$";
        Assert.assertEquals(must,FormatParser.toFormat(books));
    }

    @org.junit.jupiter.api.Test
    void fromFormat() {
        String must = "Test#10#12345678900$Blub#30#98765432100$";

        ArrayList<Book> books = new ArrayList<Book>();
        books.add(new Book("Test","10","12345678900"));
        books.add(new Book("Blub","30","98765432100"));
        FormatParser.toFormat(books);
        Assert.assertEquals(must,FormatParser.toFormat(books));
    }

}