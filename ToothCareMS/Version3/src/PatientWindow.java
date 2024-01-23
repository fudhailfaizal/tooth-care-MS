import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PatientWindow extends JFrame {
    // GUI components
    private JTextField nameField;
    private JTextField addressField;
    private JTextField numberField;
    private JButton enter;
    private JPanel patientPanel;
    private JButton toAppointments;
    private JButton exitButton;

    // Logic components
    private Patient patientLogic = Patient.getInstance();
    private int patientIDCounter = 0;

    // Constructor
    public PatientWindow() {
        initializeUI();
        setupActionListeners();
    }

    // Setup action listeners for buttons
    private void setupActionListeners() {
        // Listener for the 'Enter' button
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get input data
                String name = nameField.getText();
                String address = addressField.getText();
                int phoneNumber = 0;

                try {
                    // Extract and validate phone number
                    phoneNumber = Integer.parseInt(numberField.getText());
                    validatePhoneNumber(numberField.getText());

                    // Generate patient ID and add patient to the system
                    patientIDCounter++;
                    String patientID = String.valueOf(patientIDCounter);
                    patientLogic.addPatient(name, address, phoneNumber);

                    // Display patient information and clear fields
                    JOptionPane.showMessageDialog(null, "Information Entered:\nPatient ID: " + patientID +
                                    "\nName: " + name + "\nAddress: " + address + "\nPhone Number: " + phoneNumber,
                            "Patient Information", JOptionPane.INFORMATION_MESSAGE);
                    clearFields();

                    // Ask user if they want to add more patients or navigate to appointments
                    handleUserChoice();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }


            }
        });

        // Listener for the 'Exit' button
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Listener for the 'To Appointments' button
        toAppointments.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAppointmentsWindow();
            }
        });
    }

    // Initialize UI
    private void initializeUI() {
        setContentPane(patientPanel);
        setTitle("Patient Management");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Validate phone number format
    private void validatePhoneNumber(String phoneNumberStr) throws NumberFormatException {
        if (phoneNumberStr.length() != 10 || !phoneNumberStr.matches("\\d+")) {
            throw new NumberFormatException("Invalid phone number. Please enter a 10-digit numeric value.");
        }
    }

    // Clear input fields
    private void clearFields() {
        nameField.setText("");
        addressField.setText("");
        numberField.setText("");
    }

    // Handle user choice after adding a patient
    private void handleUserChoice() {
        int addMorePatients = JOptionPane.showConfirmDialog(null, "Do you want to add more patients?", "Add More Patients", JOptionPane.YES_NO_OPTION);
        if (addMorePatients == JOptionPane.YES_OPTION) {
            // Continue adding more patients
        } else {
            int goToAppointments = JOptionPane.showConfirmDialog(null, "Go to Create Appointments?", "Navigate", JOptionPane.YES_NO_OPTION);
            if (goToAppointments == JOptionPane.YES_OPTION) {
                openAppointmentsWindow();
            } else {
                // Handle other cases
            }
        }
    }

    // Open the AppointmentsWindow
    private void openAppointmentsWindow() {
        AppointmentsWindow appointmentsWindow = new AppointmentsWindow(patientLogic.getPatientList());
        appointmentsWindow.setTitle("Appointment Management");
        appointmentsWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appointmentsWindow.setSize(700, 300);
        appointmentsWindow.setLocationRelativeTo(null);
        appointmentsWindow.setVisible(true);

        dispose(); // Close the current Patient window
    }

    // Main method to launch the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PatientWindow patientWindow = new PatientWindow();
            patientWindow.setTitle("Patient Management");
            patientWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            patientWindow.setSize(700, 300);
            patientWindow.setLocationRelativeTo(null);
            patientWindow.setVisible(true);
        });
    }
}
