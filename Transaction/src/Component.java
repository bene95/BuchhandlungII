



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
        //TODO SQL
    }
    public void sell(String format, Connection connection) {
        System.out.println("Componet SELL");
        //TODO SQL
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
        public void sell(String format, Connection connection) {
            Component.this.sell(format,connection);
        }
        @Override
        public void buy(String format, Connection connection) {
            Component.this.buy(format,connection);
        }
    }
}
