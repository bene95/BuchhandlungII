package ViewModel.Events;

import com.book.Book;

/**
 * Created by wn00084650 on 08.04.2017.
 */
public class DeleteEvent extends Event {
    public Book getBook() {
        return book;
    }

    private Book book;

    public DeleteEvent(int id, Book book) {
        super(id);
        this.book = book;
    }
}
