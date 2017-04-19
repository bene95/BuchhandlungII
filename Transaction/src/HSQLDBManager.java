


import java.sql.*;
import java.util.ArrayList;

public enum HSQLDBManager {
    instance;

    Statement stmt = null;
    ResultSet result = null;

    public void buy(ArrayList<Book> books, Connection connection) {
        Book book = books.get(0);
        System.out.println("Booksname-"+book.getTitel() + "    QuantNew" + book.getQuantity());

        Book book2 = getBookFromDB(book.getTitel(),connection);
        System.out.println("QuantOld-" + book2.getQuantity());
        int quantOld = Integer.parseInt(book2.getQuantity());
        int quantNew = Integer.parseInt(book.getQuantity()) + quantOld;
        book.setQuantity(String.valueOf(quantNew));
        System.out.println(book.getQuantity());

        try {
            PreparedStatement stmt = connection.prepareStatement("UPDATE book SET  quantity = ? WHERE title = ?");
            stmt.setString(1,book.getQuantity());
            stmt.setString(2,book.getTitel());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void sell(ArrayList<Book> books, Connection connection) {
        Book book = books.get(0);
        System.out.println("Booksname-"+book.getTitel() + "    QuantNew" + book.getQuantity());

        Book book2 = getBookFromDB(book.getTitel(),connection);

        int quantOld = Integer.parseInt(book2.getQuantity());
        int quantNew = quantOld - Integer.parseInt(book.getQuantity())  ;
        System.out.println("QuantOld-" + quantNew);
        if (quantNew < 0)
        {

        }
        else
        {
            book.setQuantity(String.valueOf(quantNew));
            System.out.println(book.getQuantity());

            try {
                PreparedStatement stmt = connection.prepareStatement("UPDATE book SET  quantity = ? WHERE title = ?");
                stmt.setString(1,book.getQuantity());
                stmt.setString(2,book.getTitel());
                stmt.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    public Book getBookFromDB(String title,Connection connection) {
        try {
            //HSQLDBManager.instance.startup();
            stmt = connection.createStatement();
            String getBook = "SELECT title, quantity, uuid FROM book WHERE title = \'" + title +"\';";
            result = stmt.executeQuery(getBook);

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