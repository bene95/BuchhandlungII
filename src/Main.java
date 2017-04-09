package src;

import Model.Book;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	// write your code here
        HSQLDBManager.instance.init();
        System.out.println(Configuration.instance.userDirectory);
        Book book = new Book("Metro2033","2","1");
        Book book2 = new Book("Harry","3","2");
        Book book3 = new Book("Dune","5","3");

        src.HSQLDBManager.instance.insert(book);
        src.HSQLDBManager.instance.insert(book2);
        src.HSQLDBManager.instance.insert(book3);
        //src.HSQLDBManager.instance.delete(book);
        //HSQLDBManager.instance.getBookFromDB("Harry");
        ArrayList<Book> books = HSQLDBManager.instance.allBookFromDB();
        System.out.println(books.get(0).getTitel());
        System.out.println(books.get(1).getTitel());
        System.out.println(books.get(2).getTitel());


        //Buchhandlung buchhandlung = new Buchhandlung();
        //Mediator mediator = new Mediator(1);
        //Memento memento = new Memento(2,"test");
        //Configuration.instance.viewModel.addSubscriber(mediator);
        //Configuration.instance.viewModel.addSubscriber(memento);


        System.out.println(Configuration.instance.userDirectory);
    }
}
