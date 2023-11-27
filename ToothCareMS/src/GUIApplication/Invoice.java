package GUIApplication;

import workPkg.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Invoice extends JFrame{
    private JTextField appointmentID;
    private JButton showInvoiceButton;
    private JButton mainMenuButton;
    private JPanel InvoicePanel;

    public Invoice() {
        setContentPane(InvoicePanel);
        setTitle("Simple GUI App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setVisible(true);
        showInvoiceButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        new Invoice();
    }
}
