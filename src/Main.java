package src;

import Mediator.Mediator;
import Memento.Memento;
import View.Buchhandlung;

public class Main {

    public static void main(String[] args) {
	// write your code here
        //src.HSQLDBManager.instance.startup();
        //src.HSQLDBManager.instance.insert();
        //src.HSQLDBManager.instance.shutdown();
        Buchhandlung buchhandlung = new Buchhandlung();
        Mediator mediator = new Mediator(1);
        Memento memento = new Memento(2,"test");
        Configuration.instance.viewModel.addSubscriber(mediator);
        Configuration.instance.viewModel.addSubscriber(memento);


        System.out.println(Configuration.instance.userDirectory);
    }
}
