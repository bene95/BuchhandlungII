package ViewModel;

import View.Buchhandlung;
import com.book.Book;
import ViewModel.Events.*;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;


public class ViewModel extends Subscriber{
    private int eventCounter = 0;
    private int id;
    private EventBus eventBus;
    private Buchhandlung buchhandlung;
    public ViewModel(int id, EventBus eventBus, Buchhandlung buchhandlung) {
        super(id);
        this.id = id;
    this.eventBus = eventBus;
    this.buchhandlung = buchhandlung;
    }

    public void addSubscriber(Subscriber subscriber) {
        eventBus.register(subscriber);
    }


    public void createNewBook(String title){
        Book book = new Book();
        book.setTitel(title);
        eventBus.post(new NewBookEvent(eventCounter++,book));
    }
    public void updateBook(String oldTitle,String newTitle){

        Book oldBook = new Book();
        oldBook.setTitel(oldTitle);
        Book newBook = new Book();
        newBook.setTitel(newTitle);
      eventBus.post(new UpdateEvent(eventCounter++,oldBook,newBook) );
    }
    public void deleteBook(String bookTitle){

        Book book = new Book();
        book.setTitel(bookTitle);
        eventBus.post(new DeleteEvent(eventCounter++,book));
    }

    public void searchBook(String searchTitle) {

        Book book = new Book();
        book.setTitel(searchTitle);
        eventBus.post(new SearchEvent(eventCounter++,book));

    }

    public void sellBook(String title, String quantitiy) {

        Book book = new Book();
        book.setTitel(title);
        book.setQuantity(quantitiy);
        eventBus.post(new SellEvent(eventCounter++,book));
    }



    public void buyBook(String title, String quantity) {

        Book book = new Book();
        book.setTitel(title);
        book.setQuantity(quantity);
        eventBus.post(new BuyEvent(eventCounter++,book));
    }
    public void undo() {
        eventBus.post(new UndoEvent(eventCounter++));
    }


    @Subscribe
    public void receive(UpdateGUIEevent updateGUIEevent){
       if(updateGUIEevent.getForGUI().equals("Search")){
           buchhandlung.setSearchListBox(updateGUIEevent.getResultBooks());
       }
    }
//Macht der Mediator
    /*public void saveState(){
        eventBus.post(new SaveEvent(eventCounter++));
    }*/
}
