import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AppointmentsWindow extends JFrame {
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

    private Appointment appointmentLogic = new Appointment();
<<<<<<< HEAD
    private Patient patient = Patient.getInstance();
    private ArrayList<String> patientList = new ArrayList<>();


    public AppointmentsWindow(ArrayList<String> patientList) {
        if(patient.getPatientList()!=null) {
            this.patientList.addAll(patient.getPatientList());
=======
    private ArrayList<String> patientList = new ArrayList<>();

    public AppointmentsWindow(ArrayList<String> patientList) {
        if(patient.patientList!=null) {
            this.patientList.addAll(patient.patientList);
>>>>>>> 3a2ef4eca69749fe6f1c15b141051e5255aab7ad
        }

        setContentPane(appointmentsPanel);
        setTitle("Simple GUI App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 300);
        setLocationRelativeTo(null);
        setVisible(true);



        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String patientID = patientIDSearch.getText();
                System.out.println("hi");
                String selectedTreatmentID = (String) treatmentPicker.getSelectedItem();
                String selectedDate = (String) datePicker.getSelectedItem();
                String selectedTime = (String) timePicker.getSelectedItem();

                // Search for patient details using patientID
                String patientDetails = appointmentLogic.findPatientDetails(patientID, appointmentLogic.getPatientListFromPatientClass());

                System.out.println("hello!");

                if (patientDetails != null) {
                    // Patient found, proceed to schedule appointment
                    appointmentLogic.scheduleAppointment(patientID, selectedTreatmentID, selectedDate, selectedTime);
                    JOptionPane.showMessageDialog(null, "Appointment Scheduled:\nPatient ID: " + patientID +
                                    "\nTreatment ID: " + selectedTreatmentID + "\nDate: " + selectedDate + "\nTime: " + selectedTime,
                            "Appointment Information", JOptionPane.INFORMATION_MESSAGE);

                    // Clear fields
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(null, "Patient not found!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void clearFields() {
        appointmentID.setText("");
        // Clear combo boxes
        patientIDSearch.setText("");
        treatmentPicker.setSelectedIndex(0);
        datePicker.setSelectedIndex(0);
        timePicker.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Appointment appointmentLogic = new Appointment();
            ArrayList<String> patientsList = appointmentLogic.getPatientListFromPatientClass();

            AppointmentsWindow appointmentsWindow = new AppointmentsWindow(patientsList);
            appointmentsWindow.setTitle("Appointment Management");
            appointmentsWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            appointmentsWindow.setSize(400, 300);
            appointmentsWindow.setLocationRelativeTo(null);
            appointmentsWindow.setVisible(true);
        });
    }
}
