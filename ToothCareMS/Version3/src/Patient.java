import java.util.ArrayList;

public class Patient {

    // Singleton implementation

    // Singleton instance
    static Patient obj = new Patient();

    // Private constructor to prevent external instantiation
    private Patient() {

    }

    // Method to access the singleton instance
    public static Patient getInstance() {
        return obj;
    }

    private int patientID = 0; // Initializing patient ID counter
    public ArrayList<String> patientList = new ArrayList<>(); // List to store patient information

    // Adds a new patient to the patient list
    public void addPatient(String name, String address, int phoneNumber) {
        patientID++;
        // Create patient information and add it to the list
        String patientInfo = String.format("Patient ID: %d, Name: %s, Address: %s, Phone Number: %s",
                patientID, name, address, phoneNumber);
        patientList.add(patientInfo);
    }

    // Finds patient details based on provided patient ID
    public String findPatientDetails(String patientID) {
        for (String patientInfo : patientList) {
            if (patientInfo.contains("Patient ID: " + patientID)) {
                return patientInfo; // Return patient information
            }
        }
        return null; // Return null if patient is not found
    }

    // Retrieves the list of patients
    public ArrayList<String> getPatientList() {
        return patientList;
    }
}
