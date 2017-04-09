package Memento;

/**
 * Created by wn00084650 on 09.04.2017.
 */
public class DatabaseCareTaker {
    private DatabaseMemento databaseMemento;

    public DatabaseMemento getDatabaseMemento() {
        return databaseMemento;
    }

    public void setDatabaseMemento(DatabaseMemento databaseMemento) {
        this.databaseMemento = databaseMemento;
    }
}
