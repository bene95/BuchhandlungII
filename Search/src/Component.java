



import java.lang.reflect.Method;

public class Component  {
    private static Component instance = new Component();
    public Port port;

    private Component() {
        port = new Port();
    }
    public static Component getInstance() {
        return instance;

    }

    public void search(String title){
        HSQLDBManager.instance.getBookFromDB(title);

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
        public void search(String title) {
            search(title);
        }
    }
}
