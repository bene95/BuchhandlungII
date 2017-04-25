package Repository;


import Parser.Soundex;
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
        getMethod(this.clazz,methodName);
    }

    public Class getClazz() {
        return clazz;
    }

    public void execute(ArrayList<Book> books, Connection connection) {
       Object instance;
       Object port;
        try {
          String format = FormatParser.toFormat(books);
            instance = clazz.getMethod("getInstance",new Class[0]).invoke(null,new Object[0]);
            port = clazz.getDeclaredField("port").get(instance);
            //System.out.println("port      : " + port.hashCode());

            Method getVersion = port.getClass().getMethod(methodName,String.class,Connection.class);
            getVersion.invoke(port, format,connection);

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

    public ArrayList<Book> execute(String search, Connection connection) {
        Object instance;
        Object port;
        try {
            instance = clazz.getMethod("getInstance",new Class[0]).invoke(null,new Object[0]);
            port = clazz.getDeclaredField("port").get(instance);
            //System.out.println("port      : " + port.hashCode());


           // Method getVersion2 = port.getClass().getMethod("getVersion");
          //  String version = (String) getVersion2.invoke(port);
           // System.out.println(version);
            getMethod(clazz,methodName);
            //System.out.println("version   : " + Configuration.instance.getSortTyp());
            Method getVersion = port.getClass().getMethod(methodName,String.class,Connection.class);
            String result = (String)getVersion.invoke(port, search,connection);
            if(!result.isEmpty()){
                ArrayList<Book> books =  FormatParser.fromFormat(result);
                return books;
            }

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;

    }

    public boolean getMethod(Class clazz, String methodeName) {
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            System.out.println(Soundex.soundex(method.getName()));
            System.out.println(Soundex.soundex(methodeName));
            System.out.println(method.getName());

            if (Soundex.soundex(method.getName()).equals(Soundex.soundex(methodeName))) {
                this.methodName = method.getName();
                return true;
            }
        }
        return false;
    }
}