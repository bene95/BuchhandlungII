package Memento;

import com.book.Book;
import ViewModel.Events.SaveEvent;
import ViewModel.Events.UndoEvent;
import ViewModel.Subscriber;
import com.google.common.eventbus.Subscribe;
import Repository.HSQLDBManager;

import java.util.ArrayList;

public class Memento extends Subscriber {
    private ArrayList<Book> dataBase;
    private DatabaseCareTaker databaseCareTaker = new DatabaseCareTaker();
    
    public Memento(int id) {
        super(id);
        this.dataBase = HSQLDBManager.instance.allBookFromDB();
        databaseCareTaker.setDatabaseMemento(new DatabaseMemento(this.dataBase));
    }
    @Subscribe
    public void receive(UndoEvent undoEvent){
        dataBase = databaseCareTaker.getDatabaseMemento().getHsqldbManager();
        undoLast(dataBase);
        System.out.println("Memento");
    }
    @Subscribe
    public void receive(SaveEvent saveEvent){
        System.out.println("Save");
        databaseCareTaker.setDatabaseMemento(new DatabaseMemento(dataBase));
    }
    
    public void undoLast(ArrayList<Book> books)
    {
        HSQLDBManager.instance.init();
        for (Book b1: books) {
            HSQLDBManager.instance.insert(b1);
        }
    }
}
