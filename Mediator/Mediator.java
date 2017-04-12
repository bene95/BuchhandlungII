package Mediator;

import ViewModel.*;
import ViewModel.Events.*;
import com.google.common.eventbus.Subscribe;


public class Mediator extends Subscriber {
    public Mediator(int id) {
        super(id);
    }

    @Subscribe
    public void receive(NewBookEvent newBookEvent){
    //TODO Mediator Implementieren
        System.out.println("test");
    }
    @Subscribe
    public void receive(SearchEvent searchEvent){
        //TODO Mediator Implementieren
    }
    @Subscribe
    public void receive(UpdateEvent updateEvent){
        //TODO Mediator Implementieren
    }
    @Subscribe
    public void receive(DeleteEvent deleteEvent){
        //TODO Mediator Implementieren
    }
    @Subscribe
    public void receive(SellEvent sellEvent){
        //TODO Mediator Implementieren
    }
    @Subscribe
    public void receive(BuyEvent buyEvent){
        //TODO Mediator Implementieren
    }
}
