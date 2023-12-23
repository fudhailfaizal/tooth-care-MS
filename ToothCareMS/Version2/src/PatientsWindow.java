import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

public class PatientWindow extends JFrame {
    private int patientID = 0;
    private JTextField nameField;
    private JTextField addressField;
    private JTextField numberField;
    private JButton enter;
    private JPanel patientPanel;

    private LinkedList<String> patientList;

    private Appointments appointments;

    private ArrayList<PatientWindow> patientsList = new ArrayList<>();
    public Patient(Appointments appointments, LinkedList<String> patientList) {
        this.patientList = patientList;
        this.appointments = appointments;

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

                patientID++;

                // Create a string representation of patient info
                String patientInfo = String.format("PatientWindow ID: %d, Name: %s, Address: %s, Phone Number: %s",
                        patientID, name, address, phoneNumber);

                patientList.add(patientInfo); // Add patient information as a string
                appointments.updatePatientList(patientList); // Update patient list in Appointments

                JOptionPane.showMessageDialog(null, "Information Entered:\n" + patientInfo, "PatientWindow Information", JOptionPane.INFORMATION_MESSAGE);

                // Clear fields and handle further actions...
                clearFields();

                int choice = JOptionPane.showConfirmDialog(null, "Do you want to add more patients?", "Add More Patients", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    // Continue adding more patients
                } else {
                    int goToAppointments = JOptionPane.showConfirmDialog(null, "Go to Appointments?", "Navigate", JOptionPane.YES_NO_OPTION);
                    if (goToAppointments == JOptionPane.YES_OPTION) {
                        // Open Appointments window
                        LinkedList<String> patients = new LinkedList<>();
                        LinkedList<Treatment> treatments = new LinkedList<>();
                        // Assuming you've already populated patients and treatments lists

                        Appointments appointments = new Appointments(patients, treatments);
                        appointments.setVisible(true);
                        dispose(); // Close the current PatientWindow window
                    } else {
                        // Handle other actions if needed
                    }
                }
            }
        });
    }

    public Patient(int patientID, String name, String address, int phoneNumber) {
        this.patientID = patientID;
        this.nameField = new JTextField(name);
        this.addressField = new JTextField(address);
        this.numberField = new JTextField(String.valueOf(phoneNumber));
    }

    public Patient() {

    }

    @Override
    public String toString() {
        return "PatientWindow ID: " + patientID + ", Name: " + nameField.getText() +
                ", Address: " + addressField.getText() + ", Phone Number: " + numberField.getText();
    }

    private void clearFields() {
        nameField.setText("");
        addressField.setText("");
        numberField.setText("");
    }

    public static void main(String[] args) {
        LinkedList<String> patients = new LinkedList<>();
        new PatientWindow(new Appointments(patients, new LinkedList<>() ), patients);
    }
}

class Treatment {
    public int treatmentID;
    public String treatmentName;
    public double price;

    public Treatment(int treatmentID, String treatmentName, double price) {
        this.treatmentID = treatmentID;
        this.treatmentName = treatmentName;
        this.price = price;
    }
    @Override
    public String toString() {
        return treatmentName; // Display treatment name in the combo box
    }

    public static void main(String[] args) {
        LinkedList<String> patients = new LinkedList<>();
        new PatientWindow(new Appointments(patients, new LinkedList<>()), patients);
    }


}