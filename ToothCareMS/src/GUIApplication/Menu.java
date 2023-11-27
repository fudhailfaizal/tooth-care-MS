package GUIApplication;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    private JButton appointmentsButton;
    private JButton patientsButton;
    private JButton treatmentsButton;
    private JButton billingButton;
    private JPanel MenuPanel;
    private JButton reportsButton;
    private JButton exitButton;

    public Menu() {
        setContentPane(MenuPanel);
        setTitle("Simple GUI App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setVisible(true);
        appointmentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Appointments ap = new Appointments();
                ap.setVisible(true);
            }
        });
        patientsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Appointments apt = new Appointments();
                apt.setVisible(true);
            }
        });
        billingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Invoice inv = new Invoice();
                inv.setVisible(true);
            }
        });
    }
    public static void main(String[] args) {
        new Menu();
    }
}
