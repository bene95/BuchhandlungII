


import java.sql.*;
import java.util.ArrayList;

public enum HSQLDBManager {
    instance;

    Statement stmt = null;
    ResultSet result = null;


    public ArrayList<Book> searchRegBook(String title, Connection connection) {
        ArrayList<Book> regSearchB = new ArrayList<>();
        try {
            System.out.println(".............................");
            //HSQLDBManager.instance.startup();
            stmt = connection.createStatement();
            String getBook = "SELECT title, quantity, uuid FROM book WHERE REGEXP_MATCHES(title,?)";
            PreparedStatement stmt = connection.prepareStatement(getBook);
            stmt.setString(1,title);
            result = stmt.executeQuery();
            HSQLDBManager.instance.shutdown(connection);
            // The result "pointer" always stays behind the result, have to increment once!!
            while(result.next()) {
                Book book = new Book(result.getString(1), result.getString(2), result.getString(3));
                regSearchB.add(book);
            }
            return regSearchB;

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