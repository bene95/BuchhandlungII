package ViewModel;

/**
 * Created by wn00084650 on 08.04.2017.
 */
public class DeleteEvent extends Event {
    private String bookTitle;

    public DeleteEvent(int id, String bookTitle) {
        super(id);
        this.bookTitle = bookTitle;
    }
}
