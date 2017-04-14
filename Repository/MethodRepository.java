package Repository;


import com.book.Book;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class MethodRepository {
    private Class clazz;
    private String methodName;

    public MethodRepository(Class cl, String methodName) {
        this.clazz = cl;
        this.methodName = methodName;
    }

    public void execute(Book book, HSQLDBManager hsqldbManager) {
       Object instance;
       Object port;
        try {
           getMethod(clazz,"wurst");
            instance = clazz.getMethod("getInstance",new Class[0]).invoke(null,new Object[0]);
            port = clazz.getDeclaredField("port").get(instance);
            System.out.println("port      : " + port.hashCode());

            //System.out.println("version   : " + Configuration.instance.getSortTyp());
            Method getVersion = port.getClass().getMethod(methodName,ArrayList.class);
            ArrayList<Book> books = new ArrayList<Book>();
            ArrayList<HSQLDBManager> hsqldbManagers = new ArrayList<HSQLDBManager>();
            books.add( book);
            hsqldbManagers.add(HSQLDBManager.instance);
            System.out.println(hsqldbManagers.get(0).instance);
            getVersion.invoke(port, books);
            /*try {
                method.invoke(obj, book, hsqldbManager);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }*/
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }


    public boolean getMethod(Class clazz, String methodeName) {
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {

            if (method.getName() == methodeName) {
                return true;
            }
        }
        return false;
    }
}