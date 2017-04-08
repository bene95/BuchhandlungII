package ViewModel;

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


    public void createNewBook(Book book){
        eventBus.post(new NewBookEvent(eventCounter++,book));
    }
    public void updateBook(Book book){
      eventBus.post(new UpdateEvent(eventCounter++, book) );
    }
    public void deleteBook(String bookTitle){
        eventBus.post(new DeleteEvent(eventCounter++,bookTitle));
    }

}
