package Memento;

import src.HSQLDBManager;

/**
 * Created by wn00084650 on 09.04.2017.
 */
public class DatabaseMemento {
    private String dataBase;

    public DatabaseMemento(String dataBase) {
        this.dataBase = dataBase;
    }

    public String getHsqldbManager() {
        return dataBase;
    }

    public void setHsqldbManager(String  dataBase) {
        this.dataBase = dataBase;
    }
}
