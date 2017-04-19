package ViewModel.Events;

import com.book.Book;

public class BuyEvent extends Event {
    private com.book.Book book;

    public Book getBook() {
        return book;
    }

    public BuyEvent(int id, com.book.Book book) {
        super(id);
        this.book = book;

    }


}
