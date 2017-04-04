package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public enum HSQLDBManager {
    instance;

    private Connection connection = null;
    private String driverName = "jdbc:hsqldb:file:";
    private String username = "SA";
    private String password = "";

    //Connect to Database which is located in the lib package (can be changed)
    public void startup() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            String databaseURL = driverName + "C:\\Users\\hro\\Desktop\\Buchhandlung\\BuchhandlungII\\database\\database";
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

    //TODO the Database calls
    public void insert() {
        update("DROP TABLE book");
        update("CREATE TABLE book (BOOKNAME varchar(20))");
        update("INSERT INTO book (BOOKNAME) VALUES ('The Witcher')");
        update("INSERT INTO book (BOOKNAME) VALUES ('Metro2033')");
    }

    public void delete() {
        update(null);
    }

    public void update() {
        update(null);
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


    /*
    //Currently no use
    public void init() {
        dropTable();
        createTable();
    }


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