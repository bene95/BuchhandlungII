package Mediator;

import Parser.PersistenceParser;
import Parser.SearchParser;
import Parser.TransactionParser;
import Repository.MethodRepository;
import ViewModel.Events.*;
import ViewModel.Subscriber;
import com.google.common.eventbus.Subscribe;
import src.AdvancedEncryptionStandard;
import src.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;


public class Mediator extends Subscriber {
    private com.google.common.eventbus.EventBus eventBus;
    private int eventCounter = 0;
    PersistenceParser persistenceParser = new PersistenceParser();
    TransactionParser transactionParser = new TransactionParser(persistenceParser);
    SearchParser searchParser = new SearchParser(transactionParser);
    MethodRepository methodRepository;


    //Instanz Database
    private Connection connection = null;
    private String driverName = "jdbc:hsqldb:file:";
    private String username = "ROOT";
    private String password = "cAvCRxkD+hLjGjr9sYvZdA==";
    private String userDir = Configuration.instance.userDirectory;

    //AES values
    //private String key = "Bar12345Bar12345"; // 128 bit key
    private String key = "PasswordPassword";
    private String initVector = "RandomInitVector"; // 16 bytes IV

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
        System.out.println("newBookEvent");
        Connection connection = startup();
        methodRepository =  searchParser.parse("insert");
        methodRepository.execute(newBookEvent.getBook(), connection);
        eventBus.post(new SaveEvent(eventCounter++));
        searchParser.parse("NewBook");
    }
    @Subscribe
    public void receive(SearchEvent searchBook){
        //TODO Mediator Implementieren
        eventBus.post(new SaveEvent(eventCounter++));
        methodRepository =  searchParser.parse("insert");
        searchParser.parse("SearchBook");
    }
    @Subscribe
    public void receive(UpdateEvent updateEvent){
        //TODO Mediator Implementieren
        eventBus.post(new SaveEvent(eventCounter++));
        System.out.println("Update");
        methodRepository =  searchParser.parse("update");
        searchParser.parse("UpdateBook");
    }
    @Subscribe
    public void receive(DeleteEvent deleteEvent){
        //TODO Mediator Implementieren
        eventBus.post(new SaveEvent(eventCounter++));
        System.out.println("Delete");
        Connection connection = startup();
        methodRepository =  searchParser.parse("delete");
        methodRepository.execute(deleteEvent.getBook(), connection);
        eventBus.post(new SaveEvent(eventCounter++));
        searchParser.parse("DeleteBook");
    }
    @Subscribe
    public void receive(SellEvent sellEvent){
        //TODO Mediator Implementieren
        eventBus.post(new SaveEvent(eventCounter++));
        System.out.println("Update");
        searchParser.parse("SellBook");
    }
    @Subscribe
    public void receive(BuyEvent buyEvent){
        //TODO Mediator Implementieren
        eventBus.post(new SaveEvent(eventCounter++));
        System.out.println("Update");
        searchParser.parse("BuyBook");
    }


    public Connection startup() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            String databaseURL = driverName + Configuration.instance.userDirectory + "\\database\\database";
            System.out.println(AdvancedEncryptionStandard.encrypt(key,initVector,"ROOT"));
            connection = DriverManager.getConnection(databaseURL,username,AdvancedEncryptionStandard.decrypt(key,initVector,password));
            return connection;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
