package GUIApplication;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import workPkg.Patient;


public class Appointments extends JFrame {
    private JTextField aptID;
    private JTextField patientName;
    private JTextField patientAddr;
    private JTextField patientNum;
    private JButton mainMenuButton;
    private JButton saveDetailsButton;
    private JButton updateDetailsButton;
    private JComboBox aptTime;
    private JPanel AppointmentsPanel;
    private JComboBox treatment;
    private JButton searchDetailsButton;
    private JButton deleteDetailsButton;



    public static class PatientRecord {
        private int appointmentID;
        private String name;
        private String address;
        private String telephoneNumber;
        private String time;
        private String treat;

        public PatientRecord(int appointmentID, String name, String address, String telephoneNumber, String time, String treat) {
            this.setAppointmentID(appointmentID);
            this.setName(name);
            this.setAddress(address);
            this.setTelephoneNumber(telephoneNumber);
            this.setTime(time);
            this.setTreat(treat);
        }

        public int getAppointmentID() {
            return appointmentID;
        }

        public void setAppointmentID(int appointmentID) {
            this.appointmentID = appointmentID;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getTelephoneNumber() {
            return telephoneNumber;
        }

        public void setTelephoneNumber(String telephoneNumber) {
            this.telephoneNumber = telephoneNumber;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTreat() {
            return treat;
        }

        public void setTreat(String treat) {
            this.treat = treat;
        }
    }

    private Patient patient = new Patient();

    public Appointments() {
        setContentPane(AppointmentsPanel);
        setTitle("Simple GUI App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 300);
        setLocationRelativeTo(null);
        setVisible(true);

        saveDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Fetch details from text fields
                int aptIDValue = 0; // Default value for ID
                String aptIDText = aptID.getText();
                if (!aptIDText.isEmpty()) {
                    aptIDValue = Integer.parseInt(aptIDText);
                }

                String nameValue = patientName.getText();
                String addrValue = patientAddr.getText();
                String numValue = patientNum.getText();
                String timeValue = (String) aptTime.getSelectedItem();
                String treatValue = (String) treatment.getSelectedItem();

                // Call the method in the Patient class to make the appointment
                patient.make_appointment(aptIDValue, nameValue, addrValue, numValue, timeValue, treatValue);

                // Clear all the text fields after saving
                aptID.setText("");
                patientName.setText("");
                patientAddr.setText("");
                patientNum.setText("");

                // Reset combo boxes to their initial selection
                aptTime.setSelectedIndex(0);
                treatment.setSelectedIndex(0);
            }
        });

        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu menu = new Menu();
                menu.setVisible(true);
            }
        });

        updateDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the appointment ID from the user input
                int appointmentIDToUpdate = Integer.parseInt(aptID.getText());

                // Retrieve new details from text fields
                String newName = patientName.getText();
                String newAddr = patientAddr.getText();
                String newNum = patientNum.getText();
                String newTime = (String) aptTime.getSelectedItem();
                String newTreat = (String) treatment.getSelectedItem();


                // Update the appointment details using the Patient instance
                patient.update_appointment(appointmentIDToUpdate, newName, newAddr, newNum, newTime, newTreat);

                // update confirm
                JOptionPane.showMessageDialog(Appointments.this, "Details updated for Appointment ID: " + appointmentIDToUpdate);
            }
        });
        searchDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the appointment ID from the user input
                int appointmentIDToSearch = Integer.parseInt(aptID.getText());

                // Use the existing patient instance
                patient.view_appointment(appointmentIDToSearch);

            }
        });
        deleteDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int appointmentIDToDelete = Integer.parseInt(aptID.getText());

                // Call delete_appointment method using the Patient instance
                patient.delete_appointment(appointmentIDToDelete, aptID, patientName, patientAddr, patientNum, aptTime, treatment);
            }
        });
    }

    LinkedList<Appointments.PatientRecord> patientRecords = new LinkedList<>();

    public void addPatientRecord(int appointmentID, String name, String address, String telephoneNumber, String time, String treat) {
        PatientRecord newRecord = new PatientRecord(appointmentID, name, address, telephoneNumber, time, treat);
        patientRecords.add(newRecord);
    }

    public Appointments.PatientRecord searchByAppointmentID(int appointmentID) {
        for (Appointments.PatientRecord record : patientRecords) {
            if (record.getAppointmentID() == appointmentID) {
                return record;
            }
        }
        return null;
    }

    public void displayAllRecords() {
        if (patientRecords.isEmpty()) {
            System.out.println("No patient records available.");
            return;
        }

        for (Appointments.PatientRecord record : patientRecords) {
            System.out.println("Appointment ID: " + record.getAppointmentID());
            System.out.println("Patient Name: " + record.getName());
            System.out.println("Address: " + record.getAddress());
            System.out.println("Telephone Number: " + record.getTelephoneNumber());
            System.out.println("Time: " + record.getTime());
            System.out.println("Treat: " + record.getTreat());
            System.out.println();
        }
    }


    public static void main(String[] args) {
        new Appointments();
    }
}
