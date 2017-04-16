



import java.lang.reflect.Method;
import java.sql.Connection;
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

    public String search(String title,Connection connection){
      // Book book =  HSQLDBManager.instance.getBookFromDB(title);
        System.out.println("Componet SEARCH");
        ArrayList<Book> books = new ArrayList<Book>();
        Book bsp = new Book("Test","Test","Test0");
        books.add(bsp);
        return FormatParser.toFormat(books);
    }


    public String getVersion() {
        return"Search";
    }

    public class Port implements IComponent{

        private Method[] methods = getClass().getMethods();

        public Port() {


        }
        public String getVersion(){
            return Component.this.getVersion();
        }


        @Override
        public String search(String title, Connection connection) {
            String result = Component.this.search(title,connection);
            return result;
        }
    }
}
