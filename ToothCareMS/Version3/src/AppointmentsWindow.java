import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AppointmentsWindow extends JFrame {
    // GUI components
    private JComboBox<String> treatmentPicker;
    private JButton addPatientButton;
    private JComboBox datePicker;
    private JComboBox timePicker;
    private JTextField appointmentID;
    private JButton searchButton;
    private JButton exitButton;
    private JButton updateButton;
    private JButton saveButton;
    private JPanel appointmentsPanel;
    private JTextField patientIDSearch;

    // Logic components
    private Appointment appointmentLogic = new Appointment();
    private Patient patient = Patient.getInstance();
    private ArrayList<String> patientList = new ArrayList<>();

    // Constructor
    public AppointmentsWindow(ArrayList<String> patientList) {
        initializeUI();
        setupActionListeners();
        this.patientList.addAll(patientList);
    }

    // Sets up action listeners for buttons
    private void setupActionListeners() {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve user input
                String patientID = patientIDSearch.getText();
                String selectedTreatmentID = (String) treatmentPicker.getSelectedItem();
                String selectedDate = (String) datePicker.getSelectedItem();
                String selectedTime = (String) timePicker.getSelectedItem();

                // Search for patient details using patientID
                String patientDetails = appointmentLogic.findPatientDetails(patientID, appointmentLogic.getPatientListFromPatientClass());

                if (patientDetails != null) {
                    // Patient found, proceed to schedule appointment
                    appointmentLogic.scheduleAppointment(patientID, selectedTreatmentID, selectedDate, selectedTime);
                    // Display success message
                    JOptionPane.showMessageDialog(null, "Appointment Scheduled:\nPatient ID: " + patientID +
                                    "\nTreatment ID: " + selectedTreatmentID + "\nDate: " + selectedDate + "\nTime: " + selectedTime,
                            "Appointment Information", JOptionPane.INFORMATION_MESSAGE);
                    // Clear fields
                    clearFields();
                } else {
                    // Display error message if patient not found
                    JOptionPane.showMessageDialog(null, "Patient not found!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // ActionListener for adding a new patient
        addPatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openPatientWindow(); // Open the PatientWindow
            }
        });

        // ActionListener for exiting the application
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Close the application
            }
        });
    }

    // Initialize UI and set window properties
    private void initializeUI() {
        setContentPane(appointmentsPanel);
        setTitle("Appointment Management");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Open the PatientWindow
    private void openPatientWindow() {
        PatientWindow patientWindow = new PatientWindow();
        patientWindow.setTitle("Patient Management");
        patientWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        patientWindow.setSize(700, 300);
        patientWindow.setLocationRelativeTo(null);
        patientWindow.setVisible(true);
        dispose(); // Close the current AppointmentsWindow
    }

    // Clear fields after an action
    private void clearFields() {
        appointmentID.setText("");
        // Clear combo boxes
        patientIDSearch.setText("");
        treatmentPicker.setSelectedIndex(0);
        datePicker.setSelectedIndex(0);
        timePicker.setSelectedIndex(0);
    }

    // Entry point for the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Appointment appointmentLogic = new Appointment();
            ArrayList<String> patientsList = appointmentLogic.getPatientListFromPatientClass();

            // Initialize and display the AppointmentsWindow
            AppointmentsWindow appointmentsWindow = new AppointmentsWindow(patientsList);
            appointmentsWindow.setTitle("Appointment Management");
            appointmentsWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            appointmentsWindow.setSize(700, 300);
            appointmentsWindow.setLocationRelativeTo(null);
            appointmentsWindow.setVisible(true);
        });
    }
}
