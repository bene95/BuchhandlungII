package ViewModel;

import ViewModel.Events.*;
import com.google.common.eventbus.EventBus;
import Model.Book;
import src.Configuration;

public class ViewModel {
    private int eventCounter = 0;
    private int id;
    private EventBus eventBus;
    public ViewModel(int id, EventBus eventBus) {
    this.id = id;
    this.eventBus = eventBus;
    }



    public void addSubscriber(Subscriber subscriber) {
        eventBus.register(subscriber);
    }


    public void createNewBook(String title){
        saveState();
        Book book = new Book();
        book.setTitel(title);
        eventBus.post(new NewBookEvent(eventCounter++,book));
    }
    public void updateBook(String oldTitle,String newTitle){
        saveState();
        Book oldBook = new Book();
        oldBook.setTitel(oldTitle);
        Book newBook = new Book();
        newBook.setTitel(newTitle);
      eventBus.post(new UpdateEvent(eventCounter++,oldBook,newBook) );
    }
    public void deleteBook(String bookTitle){
        saveState();
        eventBus.post(new DeleteEvent(eventCounter++,bookTitle));
    }

    public void searchBook(String searchTitle) {
        saveState();
        Book book = new Book();
        book.setTitel(searchTitle);
        eventBus.post(new SearchEvent(eventCounter++,book));

    }

    public void sellBook(String title, String quantitiy) {
        saveState();
        Book book = new Book();
        book.setTitel(title);
        book.setQuantity(quantitiy);
        eventBus.post(new SellEvent(eventCounter++,book));
    }



    public void buyBook(String title, String quantity) {
        saveState();
        Book book = new Book();
        book.setTitel(title);
        book.setQuantity(quantity);
        eventBus.post(new BuyEvent(eventCounter++,book));
    }
    public void undo() {
        eventBus.post(new UndoEvent(eventCounter++));
    }

    public void saveState(){
        eventBus.post(new SaveEvent(eventCounter++));
    }
}
