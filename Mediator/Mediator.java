package Mediator;

import ViewModel.NewBookEvent;
import ViewModel.Subscriber;
import com.google.common.eventbus.Subscribe;

/**
 * Created by wn00084650 on 08.04.2017.
 */
public class Mediator extends Subscriber {
    public Mediator(int id) {
        super(id);
    }

    @Subscribe
    public void receive(NewBookEvent newBookEvent){
    //TODO Mediator Implementieren
        System.out.println("test");
}
}
