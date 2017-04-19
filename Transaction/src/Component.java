



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
    public void buy(String format, Connection connection) {
        System.out.println("Componet BUY");
        ArrayList<Book> books= FormatParser.fromFormat(format);
        Book book = books.get(0);
        HSQLDBManager.instance.buy(books,connection);

    }

    public void sell(String format, Connection connection) {
        System.out.println("Componet SELL");
        ArrayList<Book> books= FormatParser.fromFormat(format);
        Book book = books.get(0);
        HSQLDBManager.instance.sell(books,connection);
    }


    public String getVersion() {
        return"Transaktion";
    }

    public class Port implements IComponent{
        private Method[] methods = getClass().getMethods();
        public Port() {
        }
        public String getVersion(){
            return Component.this.getVersion();
        }
        @Override
        public void sell(String title, Connection connection) {
             Component.this.sell(title,connection);
        }
        @Override
        public void buy(String title, Connection connection) {
             Component.this.buy(title,connection);
        }
    }
}
