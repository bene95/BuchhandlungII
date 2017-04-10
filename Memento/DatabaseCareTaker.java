package Memento;


public class DatabaseCareTaker {
    private DatabaseMemento databaseMemento;

    public DatabaseMemento getDatabaseMemento() {
        return databaseMemento;
    }

    public void setDatabaseMemento(DatabaseMemento databaseMemento) {
        this.databaseMemento = databaseMemento;
    }
}
