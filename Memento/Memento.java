package Memento;

import ViewModel.Events.NewBookEvent;
import ViewModel.Events.SaveEvent;
import ViewModel.Events.UndoEvent;
import ViewModel.Subscriber;
import com.google.common.eventbus.Subscribe;
import src.HSQLDBManager;

import java.util.HashMap;

public class Memento extends Subscriber {
    String dataBase;
    HSQLDBManager hsqldbManager;
    private DatabaseCareTaker databaseCareTaker = new DatabaseCareTaker();
    public Memento(int id,String dataBase) {
        super(id);
        this.dataBase = dataBase;

        databaseCareTaker.setDatabaseMemento(new DatabaseMemento(this.dataBase));
    }
    @Subscribe
    public void receive(UndoEvent undoEvent){
       dataBase = databaseCareTaker.getDatabaseMemento().getHsqldbManager();
        System.out.println("Memento");
    }
    @Subscribe
    public void receive(SaveEvent saveEvent){
        System.out.println("Save");
        databaseCareTaker.setDatabaseMemento(new DatabaseMemento(dataBase));
    }
}
