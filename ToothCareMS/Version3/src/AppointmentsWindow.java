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
    private JButton viewButton;
    private JTextArea resultTextArea;
    private boolean saveButtonClicked = false;

    // Logic components
    private Appointment appointmentLogic = Appointment.getInstance();
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

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the TableViewWindow
                TableViewWindow tableViewWindow = new TableViewWindow();
                tableViewWindow.setTitle("Appointment Table View");
                tableViewWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                tableViewWindow.setSize(700, 300);
                tableViewWindow.setLocationRelativeTo(null);
                tableViewWindow.setVisible(true);
                // Dispose the current window if needed
                dispose();
            }
        });
        // Save button
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Set the flag to indicate that saveButton was clicked
                saveButtonClicked = true;

                // Call the saveAppointment method
                saveAppointment();

                // Reset the flag after processing
                saveButtonClicked = false;
            }
        });

        // Update button
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the updateAppointment method
                updateAppointment();
            }
        });

        // Search functionality
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String appointmentIDInput = appointmentID.getText();

                // Get appointment details based on the appointment ID
                String appointmentDetails = appointmentLogic.getAppointmentDetails(appointmentIDInput);

                if (!appointmentDetails.equals("Appointment ID not found")) {
                    JOptionPane.showMessageDialog(null, appointmentDetails, "Appointment Details", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Appointment not found!", "Error", JOptionPane.ERROR_MESSAGE);
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
            Appointment appointmentLogic = Appointment.getInstance();
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

    // Separate method for saving an appointment
    private void saveAppointment() {
        // Retrieve user input
        String patientID = patientIDSearch.getText();
        String selectedTreatmentID = (String) treatmentPicker.getSelectedItem();
        String selectedDate = (String) datePicker.getSelectedItem();
        String selectedTime = (String) timePicker.getSelectedItem();

        // Check if the appointment ID already exists
        if (appointmentLogic.appointmentExists(patientID)) {
            // Appointment exists, display error message
            JOptionPane.showMessageDialog(null, "Appointment already exists, please update instead.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            // Appointment does not exist, proceed to schedule appointment
            appointmentLogic.scheduleAppointment(patientID, selectedTreatmentID, selectedDate, selectedTime);
            // Display success message
            JOptionPane.showMessageDialog(null, "Appointment Scheduled:\nPatient ID: " + patientID +
                            "\nTreatment ID: " + selectedTreatmentID + "\nDate: " + selectedDate + "\nTime: " + selectedTime,
                    "Appointment Information", JOptionPane.INFORMATION_MESSAGE);
            // Clear fields
            clearFields();
        }
    }



    private void updateAppointment() {
        // Get updated appointment details from text fields or combo boxes
        String updatedAppointmentID = appointmentID.getText();
        String updatedPatientID = patientIDSearch.getText();
        String updatedTreatmentID = (String) treatmentPicker.getSelectedItem();
        String updatedDate = (String) datePicker.getSelectedItem();
        String updatedTime = (String) timePicker.getSelectedItem();

        if (saveButtonClicked) {
            return;
        }

        if (!updatedAppointmentID.isEmpty() && !updatedPatientID.isEmpty() && !updatedTreatmentID.isEmpty() && !updatedDate.isEmpty() && !updatedTime.isEmpty()) {
            // Check if the appointment ID already exists
            if (appointmentLogic.appointmentExists(updatedAppointmentID)) {
                // Prompt the user for confirmation
                int choice = JOptionPane.showConfirmDialog(null,
                        "An appointment with ID " + updatedAppointmentID + " already exists. Do you want to override it?",
                        "Confirmation", JOptionPane.YES_NO_OPTION);

                if (choice == JOptionPane.YES_OPTION) {
                    // Update the appointment details
                    appointmentLogic.updateAppointment(updatedAppointmentID, updatedPatientID, updatedTreatmentID, updatedDate, updatedTime);
                    JOptionPane.showMessageDialog(null, "Appointment Updated Successfully!", "Update Result", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // User chose not to override
                    JOptionPane.showMessageDialog(null, "Update canceled by user.", "Update Canceled", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                // Appointment does not exist, treat it as a new appointment and save
                appointmentLogic.scheduleAppointment(updatedPatientID, updatedTreatmentID, updatedDate, updatedTime);
                JOptionPane.showMessageDialog(null, "Appointment Scheduled Successfully!", "Scheduling Result", JOptionPane.INFORMATION_MESSAGE);
            }
            // Clear fields
            clearFields();
        } else {
            // Display a message if any field is blank
            JOptionPane.showMessageDialog(null, "Please fill in all details!", "Incomplete Information", JOptionPane.WARNING_MESSAGE);
        }

    }

}