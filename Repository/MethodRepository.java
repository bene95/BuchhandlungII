package Repository;


import Model.Book;
import src.HSQLDBManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodRepository {
    private Class cl;
    private String methodName;

    public MethodRepository(Class cl, String methodName){
        this.cl = cl;
        this.methodName = methodName;
    }
    public void execute(Book book, HSQLDBManager hsqldbManager){
        try {
            Object obj = cl.newInstance();
            Method method = cl.getDeclaredMethod(methodName, Book.class,HSQLDBManager.class);
            try {
                method.invoke(obj, book, hsqldbManager);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }


    }



}
