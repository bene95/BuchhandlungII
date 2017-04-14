"%JAVA%\bin\javac" -Xlint:all -g:none -classpath build -d build src\IComponent.java
"%JAVA%\bin\javac" -Xlint:all -g:none -classpath build -d build src\Component.java
"%JAVA%\bin\javac" -Xlint:all -g:none -classpath build -d build src\com.book.Book.java
"%JAVA%\bin\javac" -Xlint:all -g:none -classpath build -d build src\AdvancedEncryptionStandard.java
"%JAVA%\bin\javac" -Xlint:all -g:none -classpath build -d build src\HSQLDBManager.java
"%JAVA%\bin\javac" -Xlint:all -g:none -classpath build -d build src\Configuration.java

cd build
"%JAVA%\bin\jar" -cvf Component.jar IComponent.class
"%JAVA%\bin\jar" -uvf Component.jar Component.class
"%JAVA%\bin\jar" -uvf Component.jar com.book.Book.class
"%JAVA%\bin\jar" -uvf Component.jar AdvancedEncryptionStandard.class
"%JAVA%\bin\jar" -uvf Component.jar HSQLDBManager.class
"%JAVA%\bin\jar" -uvf Component.jar Configuration.class

move Component.jar ..\Persistence.jar

pause