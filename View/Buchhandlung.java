package View;

import src.Configuration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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


    public Buchhandlung() {
        super  ();
        pack();
        setContentPane(rootPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(600,300));

        setVisible(true);

        NewBookSubmit.addActionListener(actionEvent -> {

            Configuration.instance.viewModel.createNewBook(txtNewBookTitle.getText());
        });
        btnUpdateBookSubmit.addActionListener(actionEvent -> {

            Configuration.instance.viewModel.updateBook(txtUpdateBookOldTitle.toString(), txtUpdateBookNewTitle.toString());
        });
        btnDeleteBookSubmit.addActionListener(actionEvent -> {
            Configuration.instance.viewModel.deleteBook(txtDeleteBookTitle.getText());
        });
        btnSearchBookSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Configuration.instance.viewModel.searchBook(txtSearchBook.toString());
            }
        });
        btnSellBookSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            Configuration.instance.viewModel.sellBook(txtSellBookTitle.toString(),txtSellBookQuantity.toString());
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
                Configuration.instance.viewModel.buyBook(txtSellBookTitle.toString(),txtSellBookQuantity.toString());
            }
        });
    }
}
