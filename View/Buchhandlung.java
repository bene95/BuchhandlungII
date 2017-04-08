import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Buchhandlung extends JFrame {
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
    private JPanel rootPanel;


    public Buchhandlung() {
        super  ();
        pack();
        setContentPane(rootPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(400,275));

        setVisible(true);

        NewBookSubmit.addActionListener(actionEvent -> {
            Book book = new Book(txtNewBookTitel.toString(),txtNewBookQuantity.toString(),txtNewBookUUID.toString());
            Configuration.instance.viewModel.createNewBook(book);
        });
        btnUpdateBookSubmit.addActionListener(actionEvent -> {
            Book book = new Book(txtUpdateBookTitel.toString(),txtUpdateBookQuantity.toString(),txtUpdateBookUUID.toString());
            Configuration.instance.viewModel.updateBook(book);
        });
        btnDeleteBookSubmit.addActionListener(actionEvent -> {
            Configuration.instance.viewModel.deleteBook(txtDeleteBookTitel.toString());
        });
    }
}
