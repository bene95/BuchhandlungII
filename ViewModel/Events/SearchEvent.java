package ViewModel.Events;

import Model.Book;

/**
 * Created by wn00084650 on 08.04.2017.
 */
public class SearchEvent extends Event{

    private Book book;

    public SearchEvent(int id, Book book) {
        super(id);
        this.book = book;
    }
}
