package View;

import javax.swing.*;
import java.awt.*;
import Model.Book;
import src.*;


public class Buchhandlung extends JFrame {
    private JTabbedPane NewBook;
    private JTextField txtNewBookTitle;
    private JTextField txtNewBookQuantity;
    private JTextField txtNewBookUUID;
    private JButton NewBookSubmit;
    private JButton btnUpdateBookSubmit;
    private JTextField txtUpdateBookTitle;
    private JLabel lblUpdateBookQuantity;
    private JLabel lblUpdateBookUUID;
    private JLabel lblUpdteBookTitle;
    private JTextField txtUpdateBookQuantity;
    private JTextField txtUpdateBookUUID;
    private JLabel lblNewBookTitle;
    private JLabel lblNewBookUUID;
    private JLabel lblNewBookQuantity;
    private JPanel tabNewBook;
    private JPanel tabUpdateBook;
    private JTextField txtDeleteBookTitle;
    private JPanel tabDeleteBook;
    private JButton btnDeleteBookSubmit;
    private JLabel lblDeleteBookTitle;
    private JPanel rootPanel;


    public Buchhandlung() {
        super  ();
        pack();
        setContentPane(rootPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(400,275));

        setVisible(true);

        NewBookSubmit.addActionListener(actionEvent -> {
            Book book = new Book(txtNewBookTitle.toString(),txtNewBookQuantity.toString(),txtNewBookUUID.toString());
            Configuration.instance.viewModel.createNewBook(book);
        });
        btnUpdateBookSubmit.addActionListener(actionEvent -> {
            Book book = new Book(txtUpdateBookTitle.toString(),txtUpdateBookQuantity.toString(),txtUpdateBookUUID.toString());
            Configuration.instance.viewModel.updateBook(book);
        });
        btnDeleteBookSubmit.addActionListener(actionEvent -> {
            Configuration.instance.viewModel.deleteBook(txtDeleteBookTitle.toString());
        });
    }
}
