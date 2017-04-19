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
    private String password = "cAvCRxkD+hLjGjr9sYvZdA==";
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

    public void insert(Book book)
    {
        HSQLDBManager.instance.startup();
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO book (title,quantity,uuid) VALUES (?,?,?)");
            stmt.setString(1,book.getTitel());
            stmt.setString(2,book.getQuantity());
            stmt.setString(3,book.getUuid());
            stmt.execute();
            HSQLDBManager.instance.shutdown();
        } catch (SQLException e) {
            e.printStackTrace();
            HSQLDBManager.instance.shutdown();
        }


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

    //Currently no use
    public void init() {
        password = AdvancedEncryptionStandard.encrypt(key,initVector,"ROOT");
        update("DROP TABLE book");
        update("CREATE TABLE book ( title varchar(255)," +
                                                "quantity varchar(255)," +
                                                "uuid varchar(255));");
    }




}