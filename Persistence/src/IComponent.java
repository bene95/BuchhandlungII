import com.book.Book;

import java.util.ArrayList;
public interface IComponent {
    void insert(ArrayList<Book >books);
    void update();
    void delete();
    void select();
}
