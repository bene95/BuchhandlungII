package ViewModel.Events;
import com.book.Book;

import java.util.ArrayList;

public class UpdateGUIEevent extends  Event {
    private String forGUI;
    private ArrayList<Book> resultBooks;

    public UpdateGUIEevent(int id, String forGUI, ArrayList<Book> resultBooks) {
        super(id);
        this.forGUI = forGUI;
        this.resultBooks = resultBooks;
    }

    public String getForGUI() {
        return forGUI;
    }

    public ArrayList<Book> getResultBooks() {
        return resultBooks;
    }
}
