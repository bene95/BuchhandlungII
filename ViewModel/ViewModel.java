package ViewModel;

import ViewModel.Events.*;
import com.google.common.eventbus.EventBus;
import Model.Book;

public class ViewModel {
    private int eventCounter = 0;
    private int id;
    private EventBus eventBus;
    public ViewModel(int id, Subscriber subscriber) {
    this.id = id;
    eventBus = new EventBus("ECB-"+id);
    addSubscriber(subscriber);
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
        eventBus.post(new DeleteEvent(eventCounter++,bookTitle));
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

    public void undo() {
        eventBus.post(new UndoEvent(eventCounter++));
    }

    public void buyBook(String title, String quantity) {
        Book book = new Book();
        book.setTitel(title);
        book.setQuantity(quantity);
        eventBus.post(new BuyEvent(eventCounter,book));
    }
}
