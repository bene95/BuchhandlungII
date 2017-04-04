import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.Book;

/**
 * Created by wn00084650 on 04.04.2017.
 */
public class Buchhandlung {
    private JTabbedPane NewBook;
    private JTextField txtNewBookTitel;
    private JTextField txtNewBookQuantity;
    private JTextField txtNewBookUUID;
    private JButton NewBookSubmit;
    private JButton btnUpdateBookSubmit;
    private JTextField txtUpdateBookTitel;
    private JLabel lblUpdateBookQuantity;
    private JLabel lblUpdateBookUUID;
    private JLabel lblUpdteBookTitel;
    private JTextField txtUpdateBookQuantity;
    private JTextField txtUpdateBookUUID;
    private JLabel lblNewBookTitel;
    private JLabel lblNewBookUUID;
    private JLabel lblNewBookQuantity;
    private JPanel tabNewBook;
    private JPanel tabUpdateBook;
    private JTextField txtDeleteBookTitel;
    private JPanel tabDeleteBook;
    private JButton btnDeleteBookSubmit;
    private JLabel lblDeleteBookTitel;


    public Buchhandlung() {
        NewBookSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Book book = new Book(lblNewBookTitel.toString(),lblNewBookQuantity.toString(),lblNewBookUUID.toString());

            }
        });
    }
}
