


import java.sql.*;
import java.util.ArrayList;

public enum HSQLDBManager {
    instance;


    //AES values
    //private String key = "Bar12345Bar12345"; // 128 bit key
    private String key = "PasswordPassword";
    private String initVector = "RandomInitVector"; // 16 bytes IV

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

        update("UPDATE book SET title = \'" + newBook.getTitel() + "\' , quantity = \'" + newBook
                .getQuantity() + "\' , uuid = \'" + newBook.getUuid() +"\' WHERE title = \'" + oldBook.getTitel() + "\';",connection );

    }
    /*
        public void startup() {
            try {
                Class.forName("org.hsqldb.jdbcDriver");
                String databaseURL = driverName + userDir + "\\database\\database";
                connection = DriverManager.getConnection(databaseURL,username,AdvancedEncryptionStandard.decrypt(key, initVector,
                        password));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }*/
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
        //update("DELETE FROM book WHERE title = \'" + book.getTitel() +"\';",connection);
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


  /*
    public void delete(String uuid) {
        update("DELETE FROM book WHERE uuid = \'" + uuid +"\';");
    }


    // One Book With Title


    // Return all books in List
    public ArrayList<Book> allBookFromDB() {
        ArrayList<Book> allB = new ArrayList<>();
        try {
            //HSQLDBManager.instance.startup();
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
*/

    public void shutdown(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            statement.execute("SHUTDOWN");
            connection.close();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
    }
/*
    public void buy(Book book){
        try {
            //HSQLDBManager.instance.startup();
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
            //HSQLDBManager.instance.startup();
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

 */


 /*
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