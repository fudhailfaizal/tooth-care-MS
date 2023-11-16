package GUIApplication;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class Login extends JFrame {
    private JTextField username;
    private JButton Login;
    private JPanel LoginPanel;
    private JTextField confirmpassword;
    private JButton Exit;
    private JTextField password;

    public Login() {
        setContentPane(LoginPanel);
        setTitle("Simple GUI App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setVisible(true);
        Login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = username.getText();
                String passWord = password.getText();
                String confirmPassword = confirmpassword.getText();

                switch (userName.trim()) {
                    case "Admin":
                        passwordVerifAdmin(passWord, confirmPassword);
                        JOptionPane.showMessageDialog(Login.this, "Welcome Admin");
                        //Surgeon.main();
                        break;

                    case "Operator":
                        passwordVerifOperator(passWord, confirmPassword);
                        JOptionPane.showMessageDialog(Login.this, "Welcome Operator");
                        Menu men = new Menu();
                        men.setVisible(true);
                        break;

                    default:
                        JOptionPane.showMessageDialog(Login.this, "This username doesnt exist :/");
                        System.exit(0);
                }
            }

            void passwordVerifAdmin(String passWord, String confirmPassword) {
                // Password Verification Admin
                if (passWord.equals(confirmPassword)) {
                    switch (passWord.trim()) {
                        case "admin123":
                            JOptionPane.showMessageDialog(Login.this, "Password Verified");
                            break;
                        default:
                            JOptionPane.showMessageDialog(Login.this, "Invalid Password");
                            System.exit(0);
                    }
                } else {
                    JOptionPane.showMessageDialog(Login.this, "Passwords do not match :/");
                    System.exit(0);
                }
            }
            void passwordVerifOperator(String passWord, String conformPassword) {
                // Password Verification Appointments
                if (passWord.equals(conformPassword)) {
                    switch (passWord.trim()) {
                        case "op123":
                            JOptionPane.showMessageDialog(Login.this, "Password Verified");
                            break;
                        default:
                            JOptionPane.showMessageDialog(Login.this, "Invalid Password");
                            System.exit(0);
                    }
                } else {
                    JOptionPane.showMessageDialog(Login.this, "Passwords do not match :/");
                    System.exit(0);
                }
            }
        });


        Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    };
    public void close() {
        WindowEvent closeWindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
    }


    public static void main(String[] args) {
        new Login();
    }
}
