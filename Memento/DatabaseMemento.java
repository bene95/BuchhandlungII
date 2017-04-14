package Memento;

import com.book.Book;

import java.util.ArrayList;

public class DatabaseMemento {
    private ArrayList<Book> dataBase;

    public DatabaseMemento(ArrayList<Book> dataBase) {
        this.dataBase = dataBase;
    }

    public ArrayList<Book> getHsqldbManager() {
        return dataBase;
    }

    public void setHsqldbManager(ArrayList<Book>  dataBase) {
        this.dataBase = dataBase;
    }
}
