package ViewModel.Events;
import com.book.Book;
/**
 * Crated by wn00084650 on 08.04.2017.
 */
public class UpdateEvent extends Event {
   private Book oldTitle,newTitle;
    public UpdateEvent(int id,Book oldTitle,Book newTitle) {
        super(id);
        this.oldTitle = oldTitle;
        this.newTitle = newTitle;

    }
}
