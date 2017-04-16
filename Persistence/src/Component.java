



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
        System.out.println("AUFRUF DER HSQLDB INSERT");
        ArrayList<Book> books= FormatParser.fromFormat(format);
        Book book = books.get(0);

        System.out.println(book);
        HSQLDBManager.instance.insert(books,connection);
    }

    public void update(String format, Connection connection) {
        System.out.println("AUFRUF DER HSQLDB UPDATE");
        ArrayList<Book> books = FormatParser.fromFormat(format);
        HSQLDBManager.instance.update(books,connection);
    }

    public void delete(String format, Connection connection) {
        System.out.println("AUFRUF DER HSQLDB Delete");
        ArrayList<Book> books= FormatParser.fromFormat(format);

        HSQLDBManager.instance.delete(books,connection);

    }


    public String select(String format, Connection connection) {
        System.out.println("AUFRUF DER HSQLDB SELECT");
        ArrayList<Book> books= FormatParser.fromFormat(format);
        Book book = books.get(0);
       //TODO SELECT

        return FormatParser.toFormat(books);
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

        Component.this.insert(format, connection);
        }

        @Override
        public void update(String format,Connection connection) {
            Component.this.update(format,connection);
        }

        @Override
        public void delete(String format, Connection connection) {
            Component.this.delete(format, connection);
        }

        @Override
        public String select(String format, Connection connection) {
        return  Component.this.select(format,connection);
        }
    }
}
