package workPkg;

import GUIApplication.Appointments;

import javax.swing.*;
import java.util.LinkedList;

public class Patient extends Person {
    int PatientID;
    int aptID;
    LinkedList<Appointments.PatientRecord> patientRecords = new LinkedList<>();

    public void make_appointment(int aptIDValue, String nameValue, String addrValue, String numValue, String timeValue, String treatValue) {

        // Create a new PatientRecord
        Appointments.PatientRecord newRecord = new Appointments.PatientRecord(aptIDValue, nameValue, addrValue, numValue, timeValue, treatValue);
        patientRecords.add(newRecord);

        // Display the information in a message dialog
        String message = "Appointment ID: " + newRecord.getAppointmentID() + "\n" +
                "Patient Name: " + newRecord.getName() + "\n" +
                "Address: " + newRecord.getAddress() + "\n" +
                "Telephone Number: " + newRecord.getTelephoneNumber() + "\n" +
                "Time: " + newRecord.getTime() + "\n" +
                "Treat: " + newRecord.getTreat();

        JOptionPane.showMessageDialog(null, message);
    }

    public void update_appointment(int appointmentIDToUpdate, String newName, String newAddr, String newNum, String newTime, String newTreat) {
        Appointments.PatientRecord recordToUpdate = searchByAppointmentID(appointmentIDToUpdate);
        if (recordToUpdate != null) {
            recordToUpdate.setName(newName);
            recordToUpdate.setAddress(newAddr);
            recordToUpdate.setTelephoneNumber(newNum);
            recordToUpdate.setTime(newTime);
            recordToUpdate.setTreat(newTreat);
        }
    }

    public Appointments.PatientRecord searchByAppointmentID(int appointmentID) {
        for (Appointments.PatientRecord record : patientRecords) {
            if (record.getAppointmentID() == appointmentID) {
                return record;
            }
        }
        return null;
    }

    public void view_appointment(int appointmentIDToSearch) {
        //view appointment method
        Appointments.PatientRecord foundRecord = searchByAppointmentID(appointmentIDToSearch);

        // Check if the record exists
        if (foundRecord != null) {
            // display in the message dialog
            String message = "Appointment ID: " + foundRecord.getAppointmentID() + "\n" +
                    "Patient Name: " + foundRecord.getName() + "\n" +
                    "Address: " + foundRecord.getAddress() + "\n" +
                    "Telephone Number: " + foundRecord.getTelephoneNumber() + "\n" +
                    "Time: " + foundRecord.getTime() + "\n" +
                    "Treat: " + foundRecord.getTreat();

            // Show the details in a message dialog
            JOptionPane.showMessageDialog(null, message); // Pass null instead of Appointments.this
        } else {
            // If the record is not found, show an error message
            JOptionPane.showMessageDialog(null, "Appointment ID not found!");
        }
    }
    public void delete_appointment(int appointmentIDToDelete, JTextField aptIDField, JTextField patientNameField, JTextField patientAddrField, JTextField patientNumField, JComboBox aptTimeBox, JComboBox treatmentBox) {
        Appointments.PatientRecord recordToDelete = searchByAppointmentID(appointmentIDToDelete);

        if (recordToDelete != null) {
            // Remove the record from the linked list
            patientRecords.remove(recordToDelete);
            JOptionPane.showMessageDialog(null, "Record deleted for Appointment ID: " + appointmentIDToDelete);

            // Clear the text fields and reset combo boxes after deletion
            aptIDField.setText("");
            patientNameField.setText("");
            patientAddrField.setText("");
            patientNumField.setText("");
            aptTimeBox.setSelectedIndex(0);
            treatmentBox.setSelectedIndex(0);
        } else {
            JOptionPane.showMessageDialog(null, "Appointment ID not found!");
        }
        System.out.println("Existing Appointment IDs:");
        for (Appointments.PatientRecord record : patientRecords) {
            System.out.println(record.getAppointmentID());
        }
    }

    public void pay_registration() {
        //pay registration method
    }

    public static void main(String[] args) {

    }

}
