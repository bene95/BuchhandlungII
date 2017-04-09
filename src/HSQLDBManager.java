package src;

import Model.Book;

import java.sql.*;
import java.util.ArrayList;

public enum HSQLDBManager {
    instance;

    private Connection connection = null;
    private String driverName = "jdbc:hsqldb:file:";
    private String username = "SA";
    private String password = "";
    private String userDir = Configuration.instance.userDirectory;

    Statement stmt = null;
    ResultSet result = null;

    //Connect to Database which is located Project File
    public void startup() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            String databaseURL = driverName + userDir + "\\database\\database";
            connection = DriverManager.getConnection(databaseURL,username,password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    //Executes Statement
    public synchronized void update(String sqlStatement) {
        try {
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate(sqlStatement);
            if (result == -1)
                System.out.println("error executing " + sqlStatement);
            statement.close();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
    }

    public void insert(Model.Book book) {
        src.HSQLDBManager.instance.startup();
        update("INSERT INTO book (title,quantity,uuid) " +
                "VALUES (\'" + book.getTitel() + "\',\'" + book.getQuantity() + "\',\'" + book.getUuid() + "\');");
        src.HSQLDBManager.instance.shutdown();
    }


    public void delete(Model.Book book) {
        src.HSQLDBManager.instance.startup();
        update("DELETE FROM book WHERE uuid = \'" + book.getUuid() +"\';");
        src.HSQLDBManager.instance.shutdown();
    }

    public void delete(String uuid) {
        src.HSQLDBManager.instance.startup();
        update("DELETE FROM book WHERE uuid = \'" + uuid +"\';");
        src.HSQLDBManager.instance.shutdown();
    }

    public void update(Model.Book book) {
        src.HSQLDBManager.instance.startup();
        delete(book);
        insert(book);
        src.HSQLDBManager.instance.shutdown();
    }

    // One Book With Title
    public Book getBookFromDB(String title) {
        try {
            src.HSQLDBManager.instance.startup();
            stmt = connection.createStatement();
            String getBook = "SELECT title, quantity, uuid FROM book WHERE title = \'" + title +"\';";
            result = stmt.executeQuery(getBook);
            src.HSQLDBManager.instance.shutdown();
            // The result "pointer" always stays behind the result, have to increment once!!
            result.next();
            Book b1 = new Book(result.getString("title"), result.getString("quantity"), result.getString("uuid"));
            System.out.println(b1.getTitel());
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
            src.HSQLDBManager.instance.startup();
            stmt = connection.createStatement();
            String getBook = "SELECT title, quantity, uuid FROM book;";
            result = stmt.executeQuery(getBook);
            src.HSQLDBManager.instance.shutdown();
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
        src.HSQLDBManager.instance.startup();
        update("DROP TABLE book");
        update("CREATE TABLE book ( title varchar(255)," +
                                                "quantity varchar(255)," +
                                                "uuid varchar(255));");
        src.HSQLDBManager.instance.shutdown();
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
    */

}