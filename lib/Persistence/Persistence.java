package lib.Persistence;


import Model.Book;
import src.HSQLDBManager;

public class Persistence {

    public void insert(Book book){
        HSQLDBManager.instance.insert(book);
    }

    public void update(Book book){
        HSQLDBManager.instance.update(book);
    }

    public void delete(Book book){
        HSQLDBManager.instance.delete(book);
    }

    public Book select(String title){
        Book book = HSQLDBManager.instance.getBookFromDB(title);
        return book;
    }

}
