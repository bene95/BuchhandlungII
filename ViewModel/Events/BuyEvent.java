package ViewModel.Events;

public class BuyEvent extends Event {
    private com.book.Book book;

    public BuyEvent(int id, com.book.Book book) {
        super(id);
        this.book = book;
    }




}
