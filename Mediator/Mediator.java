package Mediator;

import Parser.PersistenceParser;
import Parser.SearchParser;
import Parser.TransactionParser;
import Repository.MethodRepository;
import ViewModel.Events.*;
import ViewModel.Subscriber;
import com.book.Book;
import com.google.common.eventbus.Subscribe;
import src.AdvancedEncryptionStandard;
import src.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;


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
        eventBus.post(new SaveEvent(eventCounter++));
        Connection connection = startup();
        methodRepository =  searchParser.parse("insert");
        ArrayList<Book> books = new ArrayList<Book>();
        books.add(newBookEvent.getBook());
        methodRepository.execute(books, connection);
        //eventBus.post(new SaveEvent(eventCounter++));
       //searchParser.parse("NewBook");
    }
    @Subscribe
    public void receive(SearchEvent searchBook){

        eventBus.post(new SaveEvent(eventCounter++));
        Connection connection = startup();
        methodRepository =  searchParser.parse("search");
       ArrayList<Book> books =  methodRepository.execute(searchBook.getBook().getTitel(),connection);//hier pasaier nichts !?!?!?!?!
      //Test
    //   ArrayList<Book> books = new ArrayList<Book>();
    //  books.add(new Book("Test","10","1325586699"));
      //Ende Test
        eventBus.post(new UpdateGUIEevent(eventCounter++,"Search", books));
        //searchParser.parse("select");
    }
    @Subscribe
    public void receive(UpdateEvent updateEvent){
        eventBus.post(new SaveEvent(eventCounter++));
        Connection connection = startup();
        methodRepository =  searchParser.parse("update");
        ArrayList<Book> books = new ArrayList<Book>();
        books.add(updateEvent.getOldTitle());
        books.add(updateEvent.getNewTitle());
       methodRepository.execute(books,connection);
    }
    @Subscribe
    public void receive(DeleteEvent deleteEvent){
        eventBus.post(new SaveEvent(eventCounter++));
        Connection connection = startup();
        methodRepository =  searchParser.parse("delete");
        ArrayList<Book> books = new ArrayList<Book>();
        books.add(deleteEvent.getBook());
        methodRepository.execute(books, connection);

    }
    @Subscribe
    public void receive(SellEvent sellEvent){
        eventBus.post(new SaveEvent(eventCounter++));
        Connection connection = startup();
        methodRepository = searchParser.parse("sell");
        ArrayList<Book> books = new ArrayList<Book>();
        books.add(sellEvent.getBook());
        methodRepository.execute(books, connection);
    }
    @Subscribe
    public void receive(BuyEvent buyEvent){
        eventBus.post(new SaveEvent(eventCounter++));
        Connection connection = startup();
        methodRepository = searchParser.parse("buy");
        ArrayList<Book> books = new ArrayList<Book>();
        books.add(buyEvent.getBook());
        methodRepository.execute(books, connection);
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
