package lib.Persistence.src;

import Model.Book;
import src.HSQLDBManager;

public class Component implements IComponent {
    public Component(){

    }
    @Override
    public void insert(Book book, HSQLDBManager hsqldbManager) {
        System.out.println("hier");
    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void select() {

    }
}
