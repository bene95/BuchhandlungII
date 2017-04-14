package Mediator;

import Parser.*;
import Repository.MethodRepository;
import ViewModel.Events.*;
import ViewModel.Subscriber;
import com.google.common.eventbus.Subscribe;

import java.lang.reflect.Method;


public class Mediator extends Subscriber {
    private com.google.common.eventbus.EventBus eventBus;
    private int eventCounter = 0;
    PersistenceParser persistenceParser = new PersistenceParser();
    TransactionParser transactionParser = new TransactionParser(persistenceParser);
    SearchParser searchParser = new SearchParser(transactionParser);
    MethodRepository methodRepository;
    public Mediator(int id, com.google.common.eventbus.EventBus eventBus) {
        super(id);
        this.eventBus = eventBus;
    }
    public void addSubscriber(Subscriber subscriber) {
        eventBus.register(subscriber);
    }

    @Subscribe
    public void receive(NewBookEvent newBookEvent){
    //TODO Mediator Implementieren
        eventBus.post(new SaveEvent(eventCounter++));
        System.out.println("test");
        methodRepository =  searchParser.parse("NewBook");
        methodRepository.execute(newBookEvent.getBook());

    }
    @Subscribe
    public void receive(SearchEvent searchEvent){
        //TODO Mediator Implementieren
        eventBus.post(new SaveEvent(eventCounter++));
        searchParser.parse("SearchBook");
    }
    @Subscribe
    public void receive(UpdateEvent updateEvent){
        //TODO Mediator Implementieren
        eventBus.post(new SaveEvent(eventCounter++));
        searchParser.parse("UpdateBook");
    }
    @Subscribe
    public void receive(DeleteEvent deleteEvent){
        //TODO Mediator Implementieren
        eventBus.post(new SaveEvent(eventCounter++));
        searchParser.parse("DeleteBook");
    }
    @Subscribe
    public void receive(SellEvent sellEvent){
        //TODO Mediator Implementieren
        eventBus.post(new SaveEvent(eventCounter++));
        searchParser.parse("SellBook");
    }
    @Subscribe
    public void receive(BuyEvent buyEvent){
        //TODO Mediator Implementieren
        eventBus.post(new SaveEvent(eventCounter++));
        searchParser.parse("BuyBook");
    }
}
