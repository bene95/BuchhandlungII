package src;

import Mediator.Mediator;
import Memento.Memento;
import Model.Book;
import View.Buchhandlung;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	// write your code here

        HSQLDBManager.instance.init();
        System.out.println(Configuration.instance.userDirectory);
        Book book = new Book("Metro2033","2","1");
        Book book2 = new Book("Harry","3","2");
        Book book3 = new Book("Dune","5","3");
        Book book4 = new Book("DasFlaechenland","5","3");


        src.HSQLDBManager.instance.insert(book);
        src.HSQLDBManager.instance.insert(book2);
        src.HSQLDBManager.instance.insert(book3);

        ArrayList<Book> save = HSQLDBManager.instance.allBookFromDB();

        System.out.println(save.get(0).getTitel());

        /*
        //src.HSQLDBManager.instance.delete(book);
        //HSQLDBManager.instance.getBookFromDB("Harry");
        ArrayList<Book> books = HSQLDBManager.instance.allBookFromDB();
        System.out.println(books.get(0).getTitel());
        System.out.println(books.get(1).getTitel());
        System.out.println(books.get(2).getTitel());
        */
        Buchhandlung buchhandlung = new Buchhandlung();
        Mediator mediator = new Mediator(1);
        Memento memento = new Memento(2,save);
        HSQLDBManager.instance.delete("1");

        Configuration.instance.viewModel.addSubscriber(mediator);
        Configuration.instance.viewModel.addSubscriber(memento);
        //Configuration.instance.viewModel.undo();




        System.out.println(Configuration.instance.userDirectory);
    }
}
