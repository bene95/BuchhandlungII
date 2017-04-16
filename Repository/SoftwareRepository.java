package Repository;

import com.book.Book;
import javafx.application.Application;
import src.Configuration;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Objects;

public class SoftwareRepository {


    private Book book;
    private ArrayList<String> methodList = new ArrayList<>();

    public SoftwareRepository(Book book){
        this.book = book;
    }

    public void setMethodList(ArrayList<String> methodList) {
        this.methodList = methodList;
    }
    public ArrayList<String> getMethodList() {
        return methodList;
    }

        public void getMethod(String methodName){
            int counter = 0;

            /*
            Archive archive = (Archive) Archive.intValue(counter);
            String name = "lib."+ archive +".src." + "Component";
            try {
                Class cl = Class.forName(name);
                //System.out.println("class " + name);
                //System.out.println("Its methods:");
                printMethods(cl);
                executeMethods(cl,"insert");
            } catch (ClassNotFoundException e) {
                System.out.println("Class not found.");
            }
            */
            while(null != Archive.intValue(counter))
            {
                Archive archive = (Archive) Archive.intValue(counter);
                String name = "lib."+ archive +".src." + "Component";
                try {
                    Class cl = Class.forName(name);
                    //System.out.println("class " + name);
                    //System.out.println("Its methods:");
                     printMethods(cl);
                } catch (ClassNotFoundException e) {
                    System.out.println("Class not found.");
                }
                counter ++;
            }
        }


        // Prints all Methods
        public void printMethods(Class cl) {
            Method[] methods = cl.getDeclaredMethods();

            for (int i = 0; i < methods.length; i++) {
                Method m = methods[i];
                Class retType = m.getReturnType();
                Class[] paramTypes = m.getParameterTypes();
                String name = m.getName();
                //System.out.print(Modifier.toString(m.getModifiers()));
                //System.out.print(" " + retType.getName() + " " + name + "(");
                StringBuilder b1 = new StringBuilder();
                b1.append(name + "(");
                for (int j = 0; j < paramTypes.length; j++) {
                    if (j > 0){
                        b1.append(", ");
                    }
                    b1.append(paramTypes[j].getName());
                }
                b1.append(")");
                methodList.add(b1.toString());
            }

        }


        /*public void executeMethods(Class cl, String methodName){
            try {
                Object obj = cl.newInstance();
                Method method = cl.getDeclaredMethod(methodName, Book.class);
                try {
                    method.invoke(obj, book);
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


        }*/

    public boolean getMethod(String methodeName, String className) {
       Class c1 = getClass(className);
       if(c1 == null){
           return false;
       }
       MethodRepository methodRepository = new MethodRepository(c1,methodeName);
       return methodRepository.getMethod(c1,methodeName);
    }



    public Class getClass(String archive) {
            for (int i = 0 ; i<=2 ; i++){
               // System.out.println(Archive.intValue(i));
                Archive s = (Archive) Archive.intValue(i);

                if(s.toString() ==  archive) {
                    String fileSeparator = Configuration.instance.fileSeparator;
                    String name = Configuration.instance.userDirectory + fileSeparator+ archive +fileSeparator + archive +".jar";
                        Object instance = null;
                    Object port = null;
                        try {
                           // System.out.println("pathToJar : " + name);
                            URL[] urls = {new File(name).toURI().toURL()};
                            URLClassLoader urlClassLoader = new URLClassLoader(urls,Application.class.getClassLoader());
                            Class clazz = Class.forName("Component",true,urlClassLoader);
                           // System.out.println("clazz     : " + clazz.toString());



                            return clazz;

                        } catch (Exception e) {
                            System.out.println("--- exception");
                            System.out.println(e.getMessage());
                            return null;
                        }


                }
            }
            return null;

    }
}

