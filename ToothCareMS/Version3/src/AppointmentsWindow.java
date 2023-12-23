import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class AppointmentsWindow extends JFrame {
    private JComboBox<String> patientPicker;
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

    private Appointment appointmentLogic = new Appointment();
    private ArrayList<String> patientList;

    public AppointmentsWindow(ArrayList<String> patientList) {
        this.patientList = patientList;

        setContentPane(appointmentsPanel);
        setTitle("Simple GUI App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 300);
        setLocationRelativeTo(null);
        setVisible(true);

        populatePatientPicker();

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedPatientID = (String) patientPicker.getSelectedItem();
                String selectedTreatmentID = (String) treatmentPicker.getSelectedItem();
                String selectedDate = (String) datePicker.getSelectedItem();
                String selectedTime = (String) timePicker.getSelectedItem();

                appointmentLogic.scheduleAppointment(selectedPatientID, selectedTreatmentID, selectedDate, selectedTime);
                JOptionPane.showMessageDialog(null, "Appointment Scheduled:\nPatient ID: " + selectedPatientID +
                                "\nTreatment ID: " + selectedTreatmentID + "\nDate: " + selectedDate + "\nTime: " + selectedTime,
                        "Appointment Information", JOptionPane.INFORMATION_MESSAGE);

                // Clear fields
                clearFields();
            }
        });
    }

    private void clearFields() {
        appointmentID.setText("");
        // Clear combo boxes
        patientPicker.setSelectedIndex(0);
        treatmentPicker.setSelectedIndex(0);
        datePicker.setSelectedIndex(0);
        timePicker.setSelectedIndex(0);
    }
    private void populatePatientPicker() {
        for (String patient : appointmentLogic.getPatientListFromPatientClass()) {
            patientPicker.addItem(patient);
        }
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
