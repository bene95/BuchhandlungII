import com.book.Book;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class Component  {
    private static Component instance = new Component();
    public Port port;

    private Component() {
        port = new Port();
    }
    public static Component getInstance() {

        return instance;

    }

    public void insert(ArrayList<Book >books){
        System.out.println("ODERDOCH!!!!");
      HSQLDBManager.instance.insert(books);
    }

    public void update() {

    }


    public void delete() {

    }


    public void select() {

    }
    public String getVersion() {
        return"Persistence";
    }

    public class Port implements IComponent{

        private Method[] methods = getClass().getMethods();

        public Port() {


        }
        public String getVersion(){
            return Component.this.getVersion();
        }

        @Override
        public void insert(ArrayList<Book >books) {
            System.out.println("bla");
        Component.this.insert(books);
        }

        @Override
        public void update() {

        }

        @Override
        public void delete() {

        }

        @Override
        public void select() {

        }
    }
}
