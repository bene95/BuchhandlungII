package ViewModel.Events;
import com.book.Book;


public class SellEvent extends Event {
    private Book book;

    public SellEvent(int id, Book book) {
        super(id);
      this.book = book;
    }

    public Book getBook() {
        return book;
    }
}
