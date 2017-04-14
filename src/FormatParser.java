package src;

import com.book.Book;

import java.util.ArrayList;

public class FormatParser {
   private static char bookSeparator = '$';
    private static char  propertiySeparator = '#';
   public static String toFormat(ArrayList<Book> books){
      String format = "";
       for (Book book:books) {
           format += book.getTitel()+propertiySeparator;
           format += book.getQuantity()+propertiySeparator;
           format += book.getUuid();

           format+=bookSeparator;
       }
       return format;
    }
    public static ArrayList<Book> fromFormat(String format){
       ArrayList<Book> books = new ArrayList<Book>();
       String[] splittedBooks = format.split(String.valueOf(bookSeparator));
        for (String stringBook:splittedBooks) {
            Book book = new Book();
            stringBook = stringBook.substring(0,stringBook.length()-1);
            String[] splittedPropertiy = stringBook.split(String.valueOf(propertiySeparator));


            if(splittedPropertiy[0].equals("null")){
                book.setTitel(null);
            }
            else {
                book.setTitel(splittedPropertiy[0]);
            }

                if(splittedPropertiy[1].equals("null"))
                {
                    book.setQuantity(null);
                }
                else
                {
                    book.setQuantity(splittedPropertiy[1]);
                }
               if(splittedPropertiy[2].equals("null")){
                    book.setUuid(null);
               }
               else
               {
                   book.setUuid(splittedBooks[2]);
               }

           books.add(book);
        }
        return books;
    }

}
