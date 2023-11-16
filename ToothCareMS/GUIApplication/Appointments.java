package GUIApplication;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Appointments extends JFrame {
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton mainMenuButton;
    private JButton saveDetailsButton;
    private JButton updateDetailsButton;
    private JComboBox comboBox1;
    private JPanel AppointmentsPanel;
    private JComboBox comboBox2;


    public Appointments() {
        setContentPane(AppointmentsPanel);
        setTitle("Simple GUI App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setVisible(true);
        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu menu = new Menu();
                menu.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        new Appointments();
    }
}
