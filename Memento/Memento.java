package Memento;

import Model.Book;
import ViewModel.Events.SaveEvent;
import ViewModel.Events.UndoEvent;
import ViewModel.Subscriber;
import com.google.common.eventbus.Subscribe;
import src.HSQLDBManager;

import java.util.ArrayList;

public class Memento extends Subscriber {
    ArrayList<Book> dataBase;
    HSQLDBManager hsqldbManager;
    private DatabaseCareTaker databaseCareTaker = new DatabaseCareTaker();
    
    public Memento(int id,ArrayList<Book> dataBase) {
        super(id);
        this.dataBase = dataBase;
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
