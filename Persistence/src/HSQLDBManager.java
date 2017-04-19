


import java.sql.*;
import java.util.ArrayList;

public enum HSQLDBManager {
    instance;
    Statement stmt = null;
    ResultSet result = null;


    //Executes Statement
    public synchronized void update(String sqlStatement,Connection connection) {
        try {
           // HSQLDBManager.instance.startup();
            System.out.println(connection.createStatement());
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate(sqlStatement);
            if (result == -1)
                System.out.println("error executing " + sqlStatement);
            statement.close();
            HSQLDBManager.instance.shutdown(connection);
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
    }

    public void update(ArrayList<Book> books,Connection connection) {
        Book oldBook = books.get(0);
        Book newBook = books.get(1);

        try {
            PreparedStatement stmt = connection.prepareStatement("UPDATE book SET title = ? , quantity = ? , uuid = ? WHERE title = ?");
            stmt.setString(1,newBook.getTitel());
            stmt.setString(2,newBook.getQuantity());
            stmt.setString(3,newBook.getUuid());
            stmt.setString(4,oldBook.getTitel());
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void insert(ArrayList<Book> books, Connection connection)
    {
        Book book = books.get(0);
        System.out.println("INSERT METHODE der HSQLDB");
        System.out.println(books.get(0).getTitel());
        System.out.println("VOR UPDATE AUFRUF");


        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO book (title,quantity,uuid) VALUES (?,0,0)");
            stmt.setString(1,book.getTitel());
            //stmt.setString(2,book.getQuantity());
            //stmt.setString(3,book.getUuid());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    public void delete(ArrayList<Book> books,Connection connection) {
        Book book = books.get(0);
        try {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM book WHERE title = ?");
            stmt.setString(1,book.getTitel());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Book getBookFromDB(String title,Connection connection) {
        try {
            //HSQLDBManager.instance.startup();
            stmt = connection.createStatement();
            String getBook = "SELECT title, quantity, uuid FROM book WHERE title = \'" + title +"\';";
            result = stmt.executeQuery(getBook);
            shutdown(connection);

            if(!result.next()){
                return new Book();
            }
            else
            {
                // The result "pointer" always stays behind the result, have to increment once!!
                Book b1 = new Book(result.getString("title"), result.getString("quantity"), result.getString("uuid"));
                //System.out.println(b1.getTitel());
                return b1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void shutdown(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            statement.execute("SHUTDOWN");
            connection.close();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
    }


}