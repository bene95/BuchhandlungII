package Repository;


import com.book.Book;
import src.AdvancedEncryptionStandard;
import src.Configuration;

import java.sql.*;
import java.util.ArrayList;

public enum HSQLDBManager {
    instance;

    private Connection connection = null;
    private String driverName = "jdbc:hsqldb:file:";
    private String username = "ROOT";
    private String password = "NnaBm1EKxVRVPGM6AAnBLQ==";
    private String userDir = Configuration.instance.userDirectory;

    //AES values
    //private String key = "Bar12345Bar12345"; // 128 bit key
    private String key = "PasswordPassword";
    private String initVector = "RandomInitVector"; // 16 bytes IV

    Statement stmt = null;
    ResultSet result = null;

    //Connect to Database which is located Project File
    public void startup() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            String databaseURL = driverName + userDir + "\\database\\database";
            connection = DriverManager.getConnection(databaseURL,username, AdvancedEncryptionStandard.decrypt(key, initVector,
                    password));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    //Executes Statement
    public synchronized void update(String sqlStatement) {
        try {
            HSQLDBManager.instance.startup();
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate(sqlStatement);
            if (result == -1)
                System.out.println("error executing " + sqlStatement);
            statement.close();
            HSQLDBManager.instance.shutdown();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
    }

    public void insert(com.book.Book book) {
        System.out.println("Dort");
        update("INSERT INTO book (title,quantity,uuid) " +
                "VALUES (\'" + book.getTitel() + "\',\'" + book.getQuantity() + "\',\'" + book.getUuid() + "\');");

    }


    public void delete(Book book) {
        update("DELETE FROM book WHERE uuid = \'" + book.getUuid() +"\';");
    }

    public void delete(String uuid) {
        update("DELETE FROM book WHERE uuid = \'" + uuid +"\';");
    }

    public void update(Book book)
    {
       update("UPDATE book SET title = \'" + book.getTitel() + "\' , quantity = \'" + book.getQuantity() + "\' , uuid = \'" + book.getUuid() +"\' WHERE title = \'" + book.getTitel() + "\';" );
    }

    // One Book With Title
    public Book getBookFromDB(String title) {
        try {
            HSQLDBManager.instance.startup();
            stmt = connection.createStatement();
            String getBook = "SELECT title, quantity, uuid FROM book WHERE title = \'" + title +"\';";
            result = stmt.executeQuery(getBook);
            HSQLDBManager.instance.shutdown();
            // The result "pointer" always stays behind the result, have to increment once!!
            result.next();
            Book b1 = new Book(result.getString("title"), result.getString("quantity"), result.getString("uuid"));
            //System.out.println(b1.getTitel());
            return b1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Return all books in List
    public ArrayList<Book> allBookFromDB() {
        ArrayList<Book> allB = new ArrayList<>();
        try {
            HSQLDBManager.instance.startup();
            stmt = connection.createStatement();
            String getBook = "SELECT title, quantity, uuid FROM book;";
            result = stmt.executeQuery(getBook);
            HSQLDBManager.instance.shutdown();
            // The result "pointer" always stays behind the result, have to increment once!!
            while(result.next()) {
                Book book = new Book(result.getString(1), result.getString(2), result.getString(3));
                allB.add(book);
            }
            return allB;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void shutdown() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("SHUTDOWN");
            connection.close();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
    }

    public void buy(Book book){
        try {
            HSQLDBManager.instance.startup();
            stmt = connection.createStatement();
            String getBook = "SELECT title, quantity, uuid FROM book WHERE title = \'" + book.getTitel() +"\';";
            result = stmt.executeQuery(getBook);
            HSQLDBManager.instance.shutdown();
            result.next();
            String quantity =  result.getString("quantity");
            int quant = Integer.parseInt(quantity);
            quant++;
            quantity = String.valueOf(quant);
            if(result != null)
            {
                update("UPDATE book SET  quantity = \'" +quantity+ "\' WHERE title = \'" + book.getTitel() + "\';" );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void sell(Book book){
        try {
            HSQLDBManager.instance.startup();
            stmt = connection.createStatement();
            String getBook = "SELECT title, quantity, uuid FROM book WHERE title = \'" + book.getTitel() +"\';";
            result = stmt.executeQuery(getBook);
            HSQLDBManager.instance.shutdown();
            result.next();
            String quantity =  result.getString("quantity");
            int quant = Integer.parseInt(quantity);
            quant--;
            quantity = String.valueOf(quant);
            if(quant > 0)
            {

                update("UPDATE book SET  quantity = \'" + quantity + "\' WHERE title = \'" + book.getTitel() + "\';" );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    //Currently no use
    public void init() {
        password = AdvancedEncryptionStandard.encrypt(key,initVector,"ROOT");
        update("DROP TABLE book");
        update("CREATE TABLE book ( title varchar(255)," +
                                                "quantity varchar(255)," +
                                                "uuid varchar(255));");
    }



    /*
    Currently no Use, maybe when first starting
    public void dropTable() {
        System.out.println("--- dropTable");

        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append("DROP TABLE data");
        System.out.println("sqlStringBuilder : " + sqlStringBuilder.toString());

        update(sqlStringBuilder.toString());
    }

    public void createTable() {
        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append("CREATE TABLE buch ").append(" ( ");
        sqlStringBuilder.append(" )");
        update(sqlStringBuilder.toString());
    }


      AES
        String key = "Bar12345Bar12345"; // 128 bit key
        String initVector = "RandomInitVector"; // 16 bytes IV
        System.out.println(AdvancedEncryptionStandard.encrypt(key,initVector,"Hello World"));

        System.out.println(AdvancedEncryptionStandard.decrypt(key, initVector,
                "9MU7vSBqfzPnj7iWvvfsEw"));


    */

}