package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.Book;
import src.*;


public class Buchhandlung extends JFrame {
    private JTabbedPane SearchBook;
    private JTextField txtNewBookTitle;
    private JTextField txtNewBookQuantity;
    private JTextField txtNewBookUUID;
    private JButton NewBookSubmit;
    private JButton btnUpdateBookSubmit;
    private JTextField txtUpdateBookOldTitle;
    private JLabel lblUpdateBookNewTitle;
    private JLabel lblUpdteBookTitle;
    private JTextField txtUpdateBookNewTitle;
    private JTextField txtUpdateBookUUID;
    private JLabel lblNewBookTitle;
    private JPanel tabNewBook;
    private JPanel tabUpdateBook;
    private JTextField txtDeleteBookTitle;
    private JPanel tabDeleteBook;
    private JButton btnDeleteBookSubmit;
    private JLabel lblDeleteBookTitle;
    private JPanel rootPanel;
    private JList lstSearchBook;
    private JLabel lblSearchBookTitle;
    private JTextField txtSearchBook;
    private JButton btnSearchBookSubmit;
    private JLabel lblSellBookTitle;
    private JTextField txtSellBookTitle;
    private JLabel lblSellTitleQuantity;
    private JTextField txtSellBookQuantity;
    private JButton btnSellBookSubmit;
    private JButton undoButton;


    public Buchhandlung() {
        super  ();
        pack();
        setContentPane(rootPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(600,300));

        setVisible(true);

        NewBookSubmit.addActionListener(actionEvent -> {
            Book book = new Book(txtNewBookTitle.toString(),txtNewBookQuantity.toString(),txtNewBookUUID.toString());
            Configuration.instance.viewModel.createNewBook(book);
        });
        btnUpdateBookSubmit.addActionListener(actionEvent -> {
            Book book = new Book(txtUpdateBookOldTitle.toString(), txtUpdateBookNewTitle.toString(),txtUpdateBookUUID.toString());
            Configuration.instance.viewModel.updateBook(book);
        });
        btnDeleteBookSubmit.addActionListener(actionEvent -> {
            Configuration.instance.viewModel.deleteBook(txtDeleteBookTitle.toString());
        });
        btnSearchBookSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        btnSellBookSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                
            }
        });
    }
}
