package ViewModel;

import Model.Book;

/**
 * Created by wn00084650 on 08.04.2017.
 */
public class UpdateEvent extends Event {
    private Book book;
    public UpdateEvent(int id,Book book) {
        super(id);
        this.book = book;

    }
}
