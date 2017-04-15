package ViewModel.Events;
import com.book.Book;

public class UpdateEvent extends Event {
   private Book oldTitle,newTitle;

    public Book getOldTitle() {
        return oldTitle;
    }

    public Book getNewTitle() {
        return newTitle;
    }

    public UpdateEvent(int id, Book oldTitle, Book newTitle) {
        super(id);
        this.oldTitle = oldTitle;
        this.newTitle = newTitle;

    }
}
