package View;

import com.book.Book;
import src.Configuration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Buchhandlung extends JFrame {
    private JTabbedPane SearchBook;
    private JTextField txtNewBookTitle;
    private JButton NewBookSubmit;
    private JButton btnUpdateBookSubmit;
    private JTextField txtUpdateBookOldTitle;

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
    private JButton btnBuyBookSubmit;
    private JLabel lblUpdateBookNewTitle;
    private JLabel lblUpdteBookOldTitle;
    private JTextField txtBuyBookTitle;
    private JTextField txtBuyBookQuantity;


    public Buchhandlung() {
        super();
        pack();
        setContentPane(rootPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(600, 300));

        setVisible(true);

        NewBookSubmit.addActionListener(actionEvent -> {

            Configuration.instance.viewModel.createNewBook(txtNewBookTitle.getText());
        });
        btnUpdateBookSubmit.addActionListener(actionEvent -> {

            Configuration.instance.viewModel.updateBook(txtUpdateBookOldTitle.getText(), txtUpdateBookNewTitle.getText());
        });
        btnDeleteBookSubmit.addActionListener(actionEvent -> {
            Configuration.instance.viewModel.deleteBook(txtDeleteBookTitle.getText());
        });
        btnSearchBookSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Configuration.instance.viewModel.searchBook(txtSearchBook.getText());
            }
        });
        btnSellBookSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Configuration.instance.viewModel.sellBook(txtSellBookTitle.getText(), txtSellBookQuantity.getText());
            }
        });
        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Configuration.instance.viewModel.undo();
            }
        });
        btnBuyBookSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Configuration.instance.viewModel.buyBook(txtBuyBookTitle.getText(), txtBuyBookQuantity.getText());
            }
        });
    }

    public void setSearchListBox(ArrayList<Book> result) {
        lstSearchBook.removeAll();
        DefaultListModel listModel = new DefaultListModel();
        String s = "Nicht vorhanden";
        if(result != null){
            for (Book book : result) {
                if(book.getTitel() != null) {
                    String toAdd = "Titel: " + book.getTitel() + " Quantity:" + book.getQuantity() + " UUID:" + book.getUuid();
                    listModel.addElement(toAdd);
                }
                else {

                    listModel.addElement(s);
                }
            }

        }
        else listModel.addElement(s);

        lstSearchBook.setModel(listModel);
    }

}
