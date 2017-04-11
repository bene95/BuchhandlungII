package Repository;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class SoftwareRepository {



    private ArrayList<String> methodList = new ArrayList<>();

    public void setMethodList(ArrayList<String> methodList) {
        this.methodList = methodList;
    }
    public ArrayList<String> getMethodList() {
        return methodList;
    }

        public void getMethod(String methodName){
            int counter = 0;
            while(null != Archive.intValue(counter))
            {
                Archive archive = (Archive) Archive.intValue(counter);
                String name = "lib."+ archive +".src." + "Component";
                try {
                    Class cl = Class.forName(name);
                    //System.out.println("class " + name);
                    //System.out.println("Its methods:");
                     printMethods(cl);
                    //executeMethods(cl,methodName);
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

        public void executeMethods(Class cl, String methodName){
            try {
                Object obj = cl.newInstance();
                Method method = cl.getDeclaredMethod(methodName);
                try {
                    method.invoke(obj,null);
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

