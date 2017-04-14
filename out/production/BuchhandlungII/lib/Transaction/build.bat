"%JAVA%\bin\javac" -Xlint:all -g:none -classpath build -d build src\IComponent.java
"%JAVA%\bin\javac" -Xlint:all -g:none -classpath build -d build src\Component.java

cd build
"%JAVA%\bin\jar" -cvf Component.jar IComponent.class
"%JAVA%\bin\jar" -uvf Component.jar Component.class

move Component.jar ..\Transaction.jar

pause