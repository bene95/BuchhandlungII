package Repository;


import com.book.Book;

import src.FormatParser;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.ArrayList;

public class MethodRepository {
    private Class clazz;
    private String methodName;

    public MethodRepository(Class cl, String methodName) {
        this.clazz = cl;
        this.methodName = methodName;
    }

    public void execute(Book book, Connection connection) {
       Object instance;
       Object port;
        try {
            ArrayList<Book> books = new ArrayList<Book>();
            books.add(book);
          String format = FormatParser.toFormat(books);

            instance = clazz.getMethod("getInstance",new Class[0]).invoke(null,new Object[0]);
            port = clazz.getDeclaredField("port").get(instance);
            System.out.println("port      : " + port.hashCode());

            //System.out.println("version   : " + Configuration.instance.getSortTyp());
            Method getVersion = port.getClass().getMethod(methodName,String.class,Connection.class);


            getVersion.invoke(port, format,connection);
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