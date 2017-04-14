package lib.Persistence.src;


import Model.Book;
import src.HSQLDBManager;

public interface IComponent {
    void insert(Book book, HSQLDBManager hsqldbManager);
    void update();
    void delete();
    void select();
}
