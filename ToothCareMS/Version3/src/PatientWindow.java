import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class PatientWindow extends JFrame {
    private JTextField nameField;
    private JTextField addressField;
    private JTextField numberField;
    private JButton enter;
    private JPanel patientPanel;

    private Patient patientLogic = Patient.getInstance();
    private int patientIDCounter = 0;

    public PatientWindow() {

        setContentPane(patientPanel);
        setTitle("Simple GUI App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 300);
        setLocationRelativeTo(null);
        setVisible(true);

        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String address = addressField.getText();
                int phoneNumber = Integer.parseInt(numberField.getText());

                patientIDCounter++;
                String patientID = String.valueOf(patientIDCounter);

                patientLogic.addPatient(name, address, phoneNumber);
                JOptionPane.showMessageDialog(null, "Information Entered:\nPatient ID: " + patientID +
                                "\nName: " + name + "\nAddress: " + address + "\nPhone Number: " + phoneNumber,
                        "Patient Information", JOptionPane.INFORMATION_MESSAGE);

                // Clear fields
                clearFields();

                int addMorePatients = JOptionPane.showConfirmDialog(null, "Do you want to add more patients?", "Add More Patients", JOptionPane.YES_NO_OPTION);
                if (addMorePatients == JOptionPane.YES_OPTION) {
                    // Continue adding more patients
                } else {
                    int goToAppointments = JOptionPane.showConfirmDialog(null, "Go to Create Appointments?", "Navigate", JOptionPane.YES_NO_OPTION);
                    if (goToAppointments == JOptionPane.YES_OPTION) {
                        openAppointmentsWindow();
                    } else {
                        // Handle other actions if needed
                    }
                }
            }
        });
    }

    private void clearFields() {
        nameField.setText("");
        addressField.setText("");
        numberField.setText("");
    }

    private void openAppointmentsWindow() {

        AppointmentsWindow appointmentsWindow = new AppointmentsWindow(patientLogic.getPatientList());
        appointmentsWindow.setTitle("Appointment Management");
        appointmentsWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appointmentsWindow.setSize(400, 300);
        appointmentsWindow.setLocationRelativeTo(null);
        appointmentsWindow.setVisible(true);

        dispose(); // Close the current Patient window
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PatientWindow patientWindow = new PatientWindow();
            patientWindow.setTitle("Patient Management");
            patientWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            patientWindow.setSize(400, 300);
            patientWindow.setLocationRelativeTo(null);
            patientWindow.setVisible(true);
        });
    }
}
