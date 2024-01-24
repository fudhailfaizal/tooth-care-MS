import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Appointment {

    // Singleton implementation
    private static final Appointment instance = new Appointment();

    // Private constructor to prevent external instantiation
    private Appointment() {
    }

    // Method to access the singleton instance
    public static Appointment getInstance() {
        return instance;
    }

    private ArrayList<String> treatmentList = new ArrayList<>();
    private ArrayList<String> appointmentList = new ArrayList<>();
    private Map<Integer, String> patientMap = new HashMap<>();

    public void addPatient(String patientInfo) {
        patientMap.put(patientMap.size() + 1, patientInfo);
    }

    public void addTreatment(String treatmentInfo) {
        treatmentList.add(treatmentInfo);
    }

    public void scheduleAppointment(String patientID, String treatmentType, String date, String time) {
        int newAppointmentID = appointmentList.size() + 1;
        String patientInfo = findPatientDetails(patientID);

        if (patientInfo != null) {
            String appointmentInfo = String.format("Appointment ID: %d, %s, Treatment Type: %s, Date: %s, Time: %s",
                    newAppointmentID, patientInfo, treatmentType, date, time);
            appointmentList.add(appointmentInfo);
        } else {
            System.out.println("Patient not found for ID: " + patientID);
        }

        System.out.println("Appointment List: " + appointmentList);
    }

    public ArrayList<String> getAppointmentList() {
        return appointmentList;
    }

    public ArrayList<String> getPatientListFromPatientClass() {
        Patient patient = Patient.getInstance();
        return patient.getPatientList();
    }

    public String findPatientDetails(String patientID) {
        Patient patient = Patient.getInstance();
        ArrayList<String> patientList = patient.getPatientList();

        for (String patientInfo : patientList) {
            String[] patientDetails = patientInfo.split(", ");
            for (String detail : patientDetails) {
                if (detail.startsWith("Patient ID:") && detail.contains(patientID)) {
                    return patientInfo;
                }
            }
        }
        return null;
    }
    public static void generateInvoice(String appointmentID, String patientName, String treatment, int treatmentFee, int registrationFee) {
        int total = treatmentFee + registrationFee;

        // Display the invoice details
        String invoiceMessage = String.format("***Invoice***\nAppointment ID: %s\nPatient Name: %s\nTreatment: %s | Treatment Fee: %d\nRegistration Fee: %d\n\nTotal = %d",
                appointmentID, patientName, treatment, treatmentFee, registrationFee, total);

        // Show a dialog box with the invoice details
        JOptionPane.showMessageDialog(null, invoiceMessage, "Invoice", JOptionPane.INFORMATION_MESSAGE);
    }

    public boolean appointmentExists(String appointmentID) {
        for (String appointment : appointmentList) {
            String[] appointmentDetails = appointment.split(", ");
            for (String detail : appointmentDetails) {
                if (detail.startsWith("Appointment ID:") && detail.contains(appointmentID)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void updateAppointment(String appointmentID, String patientID, String treatmentID, String date, String time) {
        for (int i = 0; i < appointmentList.size(); i++) {
            String appointmentDetails = appointmentList.get(i);
            String[] details = appointmentDetails.split(", ");

            if (details[0].contains(appointmentID)) {
                appointmentList.set(i, String.format("Appointment ID: %s, Patient ID: %s, Treatment ID: %s, Date: %s, Time: %s",
                        appointmentID, patientID, treatmentID, date, time));
                break;
            }
        }
        System.out.println("Appointment List: " + appointmentList);
    }

    public String getAppointmentDetails(String appointmentID) {
        for (String appointment : appointmentList) {
            String[] appointmentDetails = appointment.split(", ");
            for (String detail : appointmentDetails) {
                if (detail.startsWith("Appointment ID:") && detail.contains(appointmentID)) {
                    return appointment;
                }
            }
        }
        return "Appointment ID not found";
    }

    public ArrayList<String> getAppointmentsFilteredByDate(String dayOfWeek) {
        return appointmentList.stream()
                .filter(appointment -> appointment.contains("Date: " + dayOfWeek))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void printAppointmentList() {
        System.out.println("Appointment List:");
        for (String appointmentInfo : appointmentList) {
            System.out.println(appointmentInfo);
        }
    }
}
