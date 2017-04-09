package ViewModel.Events;

import Model.Book;

/**
 * Created by wn00084650 on 09.04.2017.
 */
public class SellEvent extends Event {
    private Book book;

    public SellEvent(int id, Book book) {
        super(id);
      this.book = book;
    }
}
