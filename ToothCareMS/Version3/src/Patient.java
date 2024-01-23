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
    private ArrayList<String> patientList = new ArrayList<>(); // List to store patient information

    // Adds a new patient to the patient list
    public void addPatient(String name, String address, int phoneNumber) {
        patientID++;
        // Create patient information and add it to the list
        String patientInfo = String.format("Patient ID: %d, Name: %s, Address: %s, Phone Number: %s",
                patientID, name, address, phoneNumber);
        patientList.add(patientInfo);

        System.out.println("Patient List: " + patientList);
    }

    // Finds patient details based on provided patient ID
    public String findPatientDetails(int patientID) {
        for (String patientInfo : patientList) {
            System.out.println("Checking: " + patientInfo);
            if (patientInfo.contains("Patient ID: " + patientID)) {
                System.out.println("Found: " + patientInfo);
                return patientInfo;
            }
        }
        return null; // Return null if patient details are not found
    }

    // Clear existing patients (remove dummy patients)
    public void clearPatients() {
        patientList.clear();
    }

    // Retrieves the list of patients
    public ArrayList<String> getPatientList() {
        return patientList;
    }
}
