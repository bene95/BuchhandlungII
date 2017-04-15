



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

    public void insert(String format, Connection connection){
        System.out.println("AUFRUF DER HSQLDB");
        ArrayList<Book> books= FormatParser.fromFormat(format);
        Book book = books.get(0);

        System.out.println(book);
         HSQLDBManager.instance.insert(books,connection);
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
        public void insert(String format, Connection connection) {
            System.out.println("IM PORT");
        Component.this.insert(format, connection);
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