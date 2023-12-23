import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class Appointments extends JFrame {
    private JTextField appointmentID;
    private JComboBox<String> dateBox;
    private JComboBox<String> timeBox;
    private JComboBox<Treatment> treatmentPicker;
    private JComboBox<String> patientPicker;
    private JButton exitButton;
    private JButton saveButton;
    private JButton updateButton;
    private JButton searchButton;
    private JPanel appointmentsPanel;
    private JButton addPatientButton;

    private LinkedList<String> patientList; // Assuming patientList is a list of patient IDs as strings
    private LinkedList<Treatment> treatmentList; // Assuming treatmentList contains Treatment objects
    private LinkedList<Appointment> appointmentList;

    public Appointments(LinkedList<String> patientList, LinkedList<Treatment> treatmentList) {
        this.patientList = patientList;
        this.treatmentList = treatmentList;
        this.appointmentList = new LinkedList<>(); // Initialize the appointment list

        updatePatientList(patientList);

        // Initialize and set up the UI components...
        setContentPane(appointmentsPanel);
        setTitle("Simple GUI App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 300);
        setLocationRelativeTo(null);
        setVisible(true);

        // Populate patientPicker combo box with patient IDs
        DefaultComboBoxModel<String> patientModel = new DefaultComboBoxModel<>(patientList.toArray(new String[0]));
        patientPicker.setModel(patientModel);

        // Populate treatmentPicker combo box with treatment objects
        DefaultComboBoxModel<Treatment> treatmentModel = new DefaultComboBoxModel<>(treatmentList.toArray(new Treatment[0]));
        treatmentPicker.setModel(treatmentModel);

        // Save button action listener
        saveButton.addActionListener(e -> {
            String selectedPatientID = (String) patientPicker.getSelectedItem();
            Treatment selectedTreatment = (Treatment) treatmentPicker.getSelectedItem();
            String selectedDate = (String) dateBox.getSelectedItem();
            String selectedTime = (String) timeBox.getSelectedItem();
            int newAppointmentID = appointmentList.size() + 1; // Incremental appointment ID

            Appointment newAppointment = new Appointment(newAppointmentID, selectedPatientID,
                    selectedTreatment.treatmentID, selectedDate, selectedTime);

            appointmentList.add(newAppointment);
            System.out.println("New Appointment added: " + newAppointment);

            // Perform any other necessary operations...
        });

        // Other initialization and UI setup...
        setContentPane(appointmentsPanel);
        setTitle("Simple GUI App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 300);
        setLocationRelativeTo(null);
        setVisible(true);
        addPatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    // Method to add a new patient received as a string
    public void addNewPatient(String patientInfo) {
        patientList.add(patientInfo);
        updatePatientPicker(); // Update patient picker combo box
    }

    // Method to update patient picker combo box
    private void updatePatientPicker() {
        DefaultComboBoxModel<String> updatedPatientModel = new DefaultComboBoxModel<>(patientList.toArray(new String[0]));
        patientPicker.setModel(updatedPatientModel);
    }
    // Method to update patient picker combo box
    public void updatePatientList(LinkedList<String> updatedList) {
        DefaultComboBoxModel<String> updatedModel = new DefaultComboBoxModel<>(updatedList.toArray(new String[0]));
        patientPicker.setModel(updatedModel);
    }

    public static void main(String[] args) {
        LinkedList<String> patients = new LinkedList<>();
        LinkedList<Treatment> treatments = new LinkedList<>();

        // Testing with some sample data
        patients.add("Patient1");
        patients.add("Patient2");
        patients.add("Patient3");

        treatments.add(new Treatment(1, "Cleanings", 4500));
        treatments.add(new Treatment(2, "Whitening", 35000));
        treatments.add(new Treatment(3, "Filling", 1000));
        // Populate patients and treatments lists from their respective classes

        Appointments appointments = new Appointments(patients, treatments);
        appointments.setVisible(true);
    }
}

class Appointment {
    public int appointmentID;
    public String patientID;
    public int treatmentID; // Changed treatmentID type to int
    public String date;
    public String time;

    public Appointment(int appointmentID, String patientID, int treatmentID, String date, String time) {
        this.appointmentID = appointmentID;
        this.patientID = patientID;
        this.treatmentID = treatmentID;
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Appointment ID: " + appointmentID + ", PatientWindow ID: " + patientID +
                ", Treatment ID: " + treatmentID + ", Date: " + date + ", Time: " + time;
    }
}