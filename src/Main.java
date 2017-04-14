package src;

import Mediator.Mediator;
import Memento.Memento;
import View.Buchhandlung;

public class Main {

    public static void main(String[] args) {
        Buchhandlung buchhandlung = new Buchhandlung();
        Mediator mediator = new Mediator(1, Configuration.instance.eventBus);
        Memento memento = new Memento(2);
        Configuration.instance.viewModel.addSubscriber(mediator);
        Configuration.instance.viewModel.addSubscriber(memento);
    }
}
	// write your code here
        /*
        HSQLDBManager.instance.init();
        System.out.println(Configuration.instance.userDirectory);
        Book book = new Book("Metro2033","2","1");
        Book book2 = new Book("Harry","3","2");
        Book book3 = new Book("Dune","5","3");



        Repository.HSQLDBManager.instance.insert(book);
        Repository.HSQLDBManager.instance.insert(book2);
        Repository.HSQLDBManager.instance.insert(book3);


        ArrayList<Book> save = HSQLDBManager.instance.allBookFromDB();


        //Repository.HSQLDBManager.instance.delete(book);
        //HSQLDBManager.instance.getBookFromDB("Harry");
        ArrayList<Book> books = HSQLDBManager.instance.allBookFromDB();
        System.out.println(books.get(0).getTitel());
        System.out.println(books.get(1).getTitel());
        System.out.println(books.get(2).getTitel());

        Buchhandlung buchhandlung = new Buchhandlung();
        Mediator mediator = new Mediator(1);
        Memento memento = new Memento(2,save);
        HSQLDBManager.instance.delete("1");

        Configuration.instance.viewModel.addSubscriber(mediator);
        Configuration.instance.viewModel.addSubscriber(memento);
        //Configuration.instance.viewModel.undo();

           */

        /*ArrayList<String> s1 = new ArrayList<>();
        Book book4 = new Book("DasFlaechenland","5","3");
        SoftwareRepository r1 = new SoftwareRepository(book4);
        r1.getMethod("ola");
        s1 = r1.getMethodList();
        System.out.println(s1.get(0));

        System.out.println(Configuration.instance.userDirectory);*/






