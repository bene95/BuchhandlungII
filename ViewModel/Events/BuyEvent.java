package ViewModel.Events;

import Model.Book;


public class BuyEvent extends Event {
    private Book book;

    public BuyEvent(int id, Book book) {
        super(id);
        this.book = book;
    }




}
