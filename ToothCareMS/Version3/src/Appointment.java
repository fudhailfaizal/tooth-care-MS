import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Appointment {
    // Lists to store treatments and appointments
    private ArrayList<String> treatmentList = new ArrayList<>();
    private ArrayList<String> appointmentList = new ArrayList<>();

    // Map for efficient patient lookup using ID as the key
    private Map<Integer, String> patientMap = new HashMap<>();

    // Adds patient information to the patient map
    public void addPatient(String patientInfo) {
        patientMap.put(patientMap.size() + 1, patientInfo); // ID-based addition
    }

    // Adds treatment information to the treatment list
    public void addTreatment(String treatmentInfo) {
        treatmentList.add(treatmentInfo);
    }

    // Creates a new appointment and adds it to the appointment list
    public void scheduleAppointment(String patientID, String treatmentID, String date, String time) {
        int newAppointmentID = appointmentList.size() + 1;
        String appointmentInfo = String.format("Appointment ID: %d, Patient ID: %s, Treatment ID: %s, Date: %s, Time: %s",
                newAppointmentID, patientID, treatmentID, date, time);
        appointmentList.add(appointmentInfo);
    }

    // Retrieves the list of appointments
    public ArrayList<String> getAppointmentList() {
        return appointmentList;
    }

    // Retrieves the patient list from the Patient class (assuming Patient class has a method to get patient list)
    public ArrayList<String> getPatientListFromPatientClass() {
        Patient patient = Patient.getInstance(); // Accessing patient list from Patient class
        return patient.getPatientList();
    }

    // Finds patient details based on patient ID in a provided list of patient information
    public String findPatientDetails(String patientID, ArrayList<String> patientList) {
        for (String patient : patientList) {
            String[] patientDetails = patient.split(", ");
            for (String detail : patientDetails) {
                if (detail.startsWith("Patient ID:") && detail.contains(patientID)) {
                    return patient; // Return patient information
                }
            }
        }
        return null; // Return null if patient is not found
    }


}
